package org.legend8883.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.legend8883")
@PropertySource("classpath:application.properties")
public class AppConfig {
}
