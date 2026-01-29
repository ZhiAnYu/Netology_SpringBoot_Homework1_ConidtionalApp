package ru.netology.SpringBootDemo.config;

import org.springframework.context.annotation.Bean;
import ru.netology.SpringBootDemo.system.DevProfile;
import ru.netology.SpringBootDemo.system.ProductionProfile;
import ru.netology.SpringBootDemo.system.SystemProfile;

public class JavaConfig {
    @Bean
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
