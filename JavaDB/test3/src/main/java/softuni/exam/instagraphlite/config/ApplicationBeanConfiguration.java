package softuni.exam.instagraphlite.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorContext;
import javax.validation.ValidatorFactory;


//ToDo
@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    Validator validator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

}
