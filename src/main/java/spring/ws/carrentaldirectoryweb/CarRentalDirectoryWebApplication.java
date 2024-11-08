package spring.ws.carrentaldirectoryweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CarRentalDirectoryWebApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(CarRentalDirectoryWebApplication.class, args);

    }

}
