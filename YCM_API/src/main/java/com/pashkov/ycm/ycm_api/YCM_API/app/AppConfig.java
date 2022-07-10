package com.pashkov.ycm.ycm_api.YCM_API.app;

import com.pashkov.ycm.ycm_api.YCM_API.app.converter.AddressConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Roman Pashkov created on 10.07.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.config
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(getAddressConverter());
    }

    @Bean
    public AddressConverter getAddressConverter(){
        return new AddressConverter();
    }
}
