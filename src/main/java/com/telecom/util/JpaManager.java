package com.telecom.util;

import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

public class JpaManager {
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalEntityManagerFactoryBean emfb
                = new LocalEntityManagerFactoryBean();
        emfb.setPersistenceUnitName("spitterPU");
        return emfb;
    }

}
