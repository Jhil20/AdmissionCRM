package org.example.leadmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LeadManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeadManagementApplication.class, args);
    }

}
