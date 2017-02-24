package edu.ldcollege.web.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "edu.ldcollege.web.ctrl.**,"
		+ "edu.ldcollege.service.**,"
		+ "edu.ldcollege.viewmodel.**,"
		+ "edu.ldcollege.aspectj.**,"
		+ "edu.ldcollege.domain.**,"
		+ "edu.ldcollege.utils.**")
@MapperScan("edu.ldcollege.mapping")
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass=true,exposeProxy=true)
public class ProWebConfiguration {

}
