package exam.service.impl;

import com.google.gson.Gson;
import exam.model.DTO.LaptopDTO;
import exam.model.entity.Laptop;
import exam.model.entity.Shop;
import exam.model.entity.WarrantyType;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {
    Gson gson;
    LaptopRepository laptopRepository;
    ShopRepository shopRepository;
    ModelMapper mapper;
    Validator validator;

    public LaptopServiceImpl(Gson gson,
                             LaptopRepository laptopRepository,
                             ShopRepository shopRepository,
                             ModelMapper mapper,
                             Validator validator) {
        this.gson = gson;
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "laptops.json");
        return Files.readString(path);
    }

    @Override
    public String importLaptops() throws IOException {
        List<String> result = new ArrayList<>();
        String json = readLaptopsFileContent();
        LaptopDTO[] laptopDTOS = gson.fromJson(json, LaptopDTO[].class);
        for (LaptopDTO laptopDTO : laptopDTOS) {
            Set<ConstraintViolation<LaptopDTO>> validate = validator.validate(laptopDTO);
            if (validate.isEmpty() && !(laptopDTO.getWarrantyType().equals("INVALID WARRANTY TYPE"))) {
                if (!laptopRepository.existsByMacAddress(laptopDTO.getMacAddress())) {
                    Laptop laptop = mapper.map(laptopDTO, Laptop.class);
                    WarrantyType warrantyType = switch (laptopDTO.getWarrantyType()) {
                        case "BASIC" -> WarrantyType.BASIC;
                        case "LIFETIME" -> WarrantyType.LIFETIME;
                        case "PREMIUM" -> WarrantyType.PREMIUM;
                        default -> throw new IllegalArgumentException();
                    };
                    laptop.setWarrantyType(warrantyType);
                    laptop.setShop(shopRepository.getByName(laptopDTO.getShop().getName()));
                    String format = String.format("Successfully imported laptop %s - %.2f - %d - %d",
                            laptopDTO.getMacAddress(),
                            laptop.getCpuSpeed(),
                            laptop.getRam(),
                            laptop.getStorage());
                    result.add(format);
                    laptopRepository.save(laptop);
                } else  {
                    result.add("Invalid Laptop");
                }
            } else {
                result.add("Invalid Laptop");
            }
        }
        return String.join("\n", result);
    }

    @Override
    public String exportBestLaptops() {
        List<String> result = laptopRepository
                .getBestLaptops()
                .stream().map(String::valueOf).toList();
        return String.join("\n", result);
    }
}
