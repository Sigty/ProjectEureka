package by.dulik.eureka.users;

import by.dulik.eureka.data.config.DatabaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@Import(DatabaseConfig.class)
@EnableDiscoveryClient
//@SpringBootApplication
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class UsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

}
