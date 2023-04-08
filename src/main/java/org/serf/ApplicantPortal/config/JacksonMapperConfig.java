package org.serf.ApplicantPortal.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonMapperConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
