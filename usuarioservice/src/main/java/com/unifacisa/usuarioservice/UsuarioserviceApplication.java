package com.unifacisa.usuarioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UsuarioserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuarioserviceApplication.class, args);
    }

}
