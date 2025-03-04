package com.company.jmixpmflowbase.test_support.initializer;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

public class TestContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "main.datasource.url=jdbc:tc:postgresql:15.1:///postgres-test-db",
                "main.datasource.username=test",
                "main.datasource.password=pass",
                "jmix.data.dbmsType=postgres",
                "main.liquibase.contexts=!production-data"
        ).applyTo(applicationContext.getEnvironment());
    }
}
