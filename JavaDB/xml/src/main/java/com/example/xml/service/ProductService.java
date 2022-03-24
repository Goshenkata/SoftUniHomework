package com.example.xml.service;

import com.example.xml.dto.ProductDTO;
import com.example.xml.dto.ProductsDTO;
import com.example.xml.dto.Q1ProductsDTO;
import com.example.xml.dto.Qd1ProductDTO;
import com.example.xml.entities.Product;
import com.example.xml.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final String PRODUCTS_XML_PATH = "/data/products.xml";
    ProductRepository productRepository;
    ModelMapper mapper;
    UserService userService;
    private CategoryService categoryService;

    public ProductService(ProductRepository productRepository,
                          ModelMapper mapper,
                          UserService userService,
                          CategoryService categoryService) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Transactional
    public void seedProducts() throws JAXBException {
        if (productRepository.count() == 0) {
            System.out.println("seeding products");

            JAXBContext context = JAXBContext.newInstance(ProductsDTO.class);
            InputStream inputStream = getClass().getResourceAsStream(PRODUCTS_XML_PATH);
            BufferedReader bfrd = new BufferedReader(new InputStreamReader(inputStream));
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ProductsDTO productsDTO = (ProductsDTO) unmarshaller.unmarshal(bfrd);
            Set<Product> collect = productsDTO
                    .getProducts()
                    .stream()
                    .map(this::mapToProduct)
                    .collect(Collectors.toSet());
            productRepository.saveAll(collect);
        }
    }

    @Transactional
    Product mapToProduct(ProductDTO p) {
        Product map = mapper.map(p, Product.class);
        Random random = new Random();
        if (random.nextBoolean()) {
            map.setBuyer(userService.randomUser());
        } else map.setBuyer(null);
        map.setSeller(userService.randomUser());
        map.setCategories(categoryService.getRandomCategories());
        return map;
    }

    public void q1() throws JAXBException {
        Set<Product> products = productRepository.getAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        Q1ProductsDTO productsDTO = new Q1ProductsDTO();
        productsDTO.setProductDTOs(new ArrayList<>());
        for (Product product : products) {
            Qd1ProductDTO productDTO = mapper.map(product, Qd1ProductDTO.class);
            productDTO.setSeller(product.getSeller().getFirstName() + " " + product.getSeller().getLastName());
            productsDTO.getProductDTOs().add(productDTO);
        }
        JAXBContext context = JAXBContext.newInstance(Q1ProductsDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(productsDTO, stringWriter);
        System.out.println(stringWriter);
    }
}
