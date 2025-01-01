package org.nio.ue.hello_controller.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public ObjectMapper objectMapper(){

        ObjectMapper objectMapper=new ObjectMapper();

        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX,true);

        return objectMapper;
    }

}
