package com.example.xml.service;

import com.example.xml.dto.*;
import com.example.xml.entities.Product;
import com.example.xml.entities.User;
import com.example.xml.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

@Service
public class UserService {
    private static final String USERS_XML_PATH = "/data/users.xml";
    UserRepository userRepository;
    ModelMapper mapper;


    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.mapper = modelMapper;
    }

    public void seedUsers() throws JAXBException {
        if (userRepository.count() == 0) {
            JAXBContext context = JAXBContext.newInstance(UsersDTO.class);
            System.out.println("seeding users");
            InputStream inputStream = getClass().getResourceAsStream(USERS_XML_PATH);
            BufferedReader bfrd = new BufferedReader(new InputStreamReader(inputStream));
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UsersDTO usersDTO = (UsersDTO) unmarshaller.unmarshal(bfrd);
            List<User> collect = usersDTO
                    .getUsers()
                    .stream()
                    .map(u -> mapper.map(u, User.class))
                    .toList();
            userRepository.saveAll(collect);
        }
    }

    public User randomUser() {
        Integer min = userRepository.getMinID();
        Integer max = userRepository.getMaxID();
        Random random = new Random();
        return userRepository.getById(random.nextLong(max) + min);
    }

    @Transactional
    public void q2() throws JAXBException {
        List<User> users = userRepository.q2();
        for (User user : users) {
            user.setProductsSold(
                    user.getProductsSold()
                            .stream()
                            .filter(product -> product.getBuyer() != null)
                            .collect(Collectors.toSet()));
        }
        users = users.stream()
                .filter(user -> !user.getProductsSold().isEmpty()).collect(Collectors.toList());
        Q2UsersDTO usersDTO = this.mapQ2(users);
        JAXBContext context = JAXBContext.newInstance(Q2UsersDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(usersDTO, stringWriter);
        System.out.println(stringWriter);
    }

    private Q2UsersDTO mapQ2(List<User> users) {
        Q2UsersDTO usersDTO = new Q2UsersDTO();
        List<Q2UserDTO> userDTO = new ArrayList<>();
        for (User u : users) {
            Q2UserDTO localUser = new Q2UserDTO();
            localUser.setFirstName(localUser.getFirstName());
            localUser.setLastName(localUser.getLastName());
            List<Q2ProductsDTO> productsDTOS = new ArrayList<>();
            for (Product p : u.getProductsSold()) {
                Q2ProductsDTO q2ProductsDTO = new Q2ProductsDTO();
                q2ProductsDTO.setPrice(p.getPrice());
                q2ProductsDTO.setName(p.getName());
                q2ProductsDTO.setBuyerFirstName(p.getBuyer().getFirstName());
                q2ProductsDTO.setBuyerLastName(p.getBuyer().getLastName());
                productsDTOS.add(q2ProductsDTO);
            }
            localUser.setProducts(productsDTOS);
            userDTO.add(localUser);
        }
        usersDTO.setUsers(userDTO);
        return usersDTO;
    }

    @Transactional
    public void q4() throws JAXBException {
        Q4Users users = new Q4Users();
        List<User> usersList = userRepository.q4();
        users.setCount(usersList.size());
        List<Q4User> dtoUsers = new ArrayList<>();
        for (User user : usersList) {
            Q4User q4User = new Q4User();
            q4User.setAge(user.getAge());
            q4User.setFirstName(user.getFirstName());
            q4User.setLastName(user.getLastName());

            List<Q4SoldProducts> q4SoldProductsList = new ArrayList<>();
            Q4SoldProducts q4SoldProducts = new Q4SoldProducts();
            List<Q4Products> q4Products = new ArrayList<>();
            for (Product p : user.getProductsSold()) {
                Q4Products products = new Q4Products();
                products = products.setName(p.getName());
                products.setPrice(p.getPrice());
                q4Products.add(products);
            }
            q4SoldProducts.setProducts(q4Products);
            q4SoldProducts.setCount(q4Products.size());
            q4SoldProductsList.add(q4SoldProducts);
            q4User.setSoldProducts(q4SoldProductsList);
            dtoUsers.add(q4User);
        }

        users.setUsers(dtoUsers);

        JAXBContext context = JAXBContext.newInstance(Q4Users.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(users, sw);
        System.out.println(sw);
    }
}
