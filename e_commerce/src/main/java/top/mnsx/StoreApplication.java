package top.mnsx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("top.mnsx.store.dao")
public class StoreApplication {

    @Bean
    public MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(15, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}
