package com.orange.tbk.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@MapperScan("com.orange.tbk.admin.mapper")
//@ComponentScan(value={"com.*","com.orange.tbk.admin.dao"})
@ComponentScan(basePackages = {"com.orange.tbk.admin","com.orange.tbk.adminweb"})
@EntityScan("com/orange/tbk/api/bean")
//@EnableJpaRepositories(basePackages = "com.orange.tbk.admin.dao")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
