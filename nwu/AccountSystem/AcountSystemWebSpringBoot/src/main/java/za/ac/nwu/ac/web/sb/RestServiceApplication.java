package za.ac.nwu.ac.web.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.stereotype.Component;


//@SpringBootApplication(scanBasePackages={"za.ac.nwu.ac.logic.flow","za.ac.nwu.ac.translator","za.ac.nwu.ac.domain.persistence","za.ac.nwu.ac.web.sb.controller"})
@SpringBootApplication
public class RestServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(RestServiceApplication.class, args);

    }
}
