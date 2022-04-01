package exam.service.impl;

import com.google.gson.Gson;
import exam.model.DTO.CustomerDTO;
import exam.model.entity.Customer;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    Gson gson;
    ModelMapper mapper;
    CustomerRepository customerRepository;
    Validator validator;
    TownRepository townRepository;

    public CustomerServiceImpl(Gson gson,
                               ModelMapper mapper,
                               CustomerRepository customerRepository,
                               Validator validator,
                               TownRepository townRepository) {
        this.gson = gson;
        this.mapper = mapper;
        this.customerRepository = customerRepository;
        this.validator = validator;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "customers.json");
        return Files.readString(path);
    }

    @Override
    public String importCustomers() throws IOException {
        List<String> result = new ArrayList<>();
        String json = readCustomersFileContent();
        CustomerDTO[] customerDTOS = gson.fromJson(json, CustomerDTO[].class);
        for (CustomerDTO customerDTO : customerDTOS) {
            Set<ConstraintViolation<CustomerDTO>> validate = validator.validate(customerDTO);
            if (validate.isEmpty()) {
                if (!customerRepository.existsByEmail(customerDTO.getEmail())) {
                    Customer customer = mapper.map(customerDTO, Customer.class);
                    customer.setTown(townRepository.getByName(customerDTO.getTown().getName()));
                    String[] datePart = customerDTO.getRegisteredOn().split("/");
                    int year = Integer.parseInt(datePart[2]);
                    int month = Integer.parseInt(datePart[1]);
                    int day = Integer.parseInt(datePart[0]);
                    customer.setRegisteredOn(LocalDate.of(year,month,day));
                    String format = String.format("Successfully imported Customer %s - %s - %s",
                            customerDTO.getFirstName(),
                            customerDTO.getLastName(),
                            customerDTO.getEmail());
                    result.add(format);
                    customerRepository.save(customer);
                } else {
                    result.add("Invalid Customer");
                }
            } else {
                result.add("Invalid Customer");
            }
        }
        return String.join("\n", result);
    }
}