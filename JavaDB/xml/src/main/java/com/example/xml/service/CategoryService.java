package com.example.xml.service;

import com.example.xml.dto.CategoriesDTO;
import com.example.xml.dto.Q3DTORoot;
import com.example.xml.dto.Qd3DTO;
import com.example.xml.entities.Category;
import com.example.xml.entities.Product;
import com.example.xml.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private static final String CATEGORIES_XML_PATH = "/data/categories.xml";
    CategoryRepository categoryRepository;
    ModelMapper mapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public void seedCategories() throws JAXBException {
        if (categoryRepository.count() == 0) {
            System.out.println("seeding categories");
            JAXBContext context = JAXBContext.newInstance(CategoriesDTO.class);
            InputStream inputStream = getClass().getResourceAsStream(CATEGORIES_XML_PATH);
            BufferedReader bfrd = new BufferedReader(new InputStreamReader(inputStream));
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CategoriesDTO categoriesDTO = (CategoriesDTO) unmarshaller.unmarshal(bfrd);
            List<Category> collect = categoriesDTO
                    .getCategories()
                    .stream()
                    .map(u -> mapper.map(u, Category.class))
                    .toList();
            categoryRepository.saveAll(collect);
        }
    }

    public Set<Category> getRandomCategories() {
        Random random = new Random();
        int n = random.nextInt(6) + 1;
        Set<Category> categories = new HashSet<>();
        Integer minId = categoryRepository.getByMinId();
        Integer maxId = categoryRepository.getByMaxId();
        for (int i = 0; i < n; i++) {
            Long randomId = random.nextLong(maxId) + minId;
            categories.add(categoryRepository.getById(randomId));
        }
        return categories;
    }

    @Transactional
    public void q3() throws JAXBException {
        Q3DTORoot qd3DTO = new Q3DTORoot();
        List<Qd3DTO> categories = categoryRepository.findAll()
                .stream()
                .map(this::mapToQ3)
                .toList();
        qd3DTO.setCategories(categories);
        JAXBContext context = JAXBContext.newInstance(Q3DTORoot.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(qd3DTO, stringWriter);
        System.out.println(stringWriter);

    }

    @Transactional
    Qd3DTO mapToQ3(Category c) {
        Qd3DTO qd3DTO = new Qd3DTO();
        qd3DTO.setName(c.getName());
        qd3DTO.setProductsCount(c.getProducts().size());
        qd3DTO.setAveragePrice(c.getProducts()
                .stream()
                .mapToDouble(product -> product.getPrice().doubleValue())
                .average()
                .orElseThrow(IllegalArgumentException::new));

        qd3DTO.setTotalRevenue(c.getProducts()
                .stream()
                .mapToDouble(product -> product.getPrice().doubleValue())
                .sum());

        return qd3DTO;
    }
}
