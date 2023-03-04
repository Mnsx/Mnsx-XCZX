package top.mnsx.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Mnsx_x xx1527030652@gmail.com
 */
@SpringBootApplication(scanBasePackages = {"top.mnsx.exception"})
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class);
    }
}
