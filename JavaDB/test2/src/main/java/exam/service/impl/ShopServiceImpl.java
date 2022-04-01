package exam.service.impl;

import exam.model.DTO.ShopDTO;
import exam.model.DTO.ShopsDTO;
import exam.model.DTO.TownDTO;
import exam.model.DTO.TownsDTO;
import exam.model.entity.Shop;
import exam.model.entity.Town;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ShopServiceImpl implements ShopService {
    ModelMapper mapper;
    ShopRepository shopRepository;
    Validator validator;
    TownRepository townRepository;

    public ShopServiceImpl(ModelMapper mapper,
                           ShopRepository shopRepository,
                           Validator validator,
                           TownRepository townRepository) {
        this.mapper = mapper;
        this.shopRepository = shopRepository;
        this.validator = validator;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "xml", "shops.xml");
        return Files.readString(path);
    }

    @Override
    public String importShops() throws JAXBException, IOException {
        List<String> result = new ArrayList<>();
        JAXBContext context = JAXBContext.newInstance(ShopsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader xml = new StringReader(readShopsFileContent());
        ShopsDTO shopsDTO = (ShopsDTO) unmarshaller.unmarshal(xml);
        for (ShopDTO shopDTO : shopsDTO.getShops()) {
            Set<ConstraintViolation<ShopDTO>> validate = validator.validate(shopDTO);
            if (validate.isEmpty()) {
                if (!shopRepository.existsByName(shopDTO.getName())) {
                    Shop shop = mapper.map(shopDTO, Shop.class);
                    Town town = townRepository.getByName(shopDTO.getTownName().getName());
                    shop.setTown(town);
                    String format = String.format("Successfully imported Shop %s - %d", shopDTO.getName(), shopDTO.getIncome().intValue());
                    result.add(format);
                    shopRepository.save(shop);
                } else {
                    result.add("Invalid Shop");
                }
            } else {
                result.add("Invalid Shop");
            }
        }
        return String.join("\n", result);
    }
}
