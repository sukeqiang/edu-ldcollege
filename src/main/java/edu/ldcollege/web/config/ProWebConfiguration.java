package edu.ldcollege.web.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "edu.ldcollege.web.ctrl.**,edu.ldcollege.service.**")
@MapperScan("edu.ldcollege.mapping")
@EnableTransactionManagement
public class ProWebConfiguration {

}
