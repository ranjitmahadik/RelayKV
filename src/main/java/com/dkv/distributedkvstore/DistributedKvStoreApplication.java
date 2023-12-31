package com.dkv.distributedkvstore;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistributedKvStoreApplication {

    public static void loadAndSetEnvVariables() {
        Dotenv dotenv = Dotenv.load();
        String postgresUsername = dotenv.get("POSTGRES_USER");
        String postgresPassword = dotenv.get("POSTGRES_PASSWORD");
        System.setProperty("POSTGRES_USER", postgresUsername);
        System.setProperty("POSTGRES_PASSWORD", postgresPassword);
    }

    public static void main(String[] args) {
        loadAndSetEnvVariables();
        SpringApplication.run(DistributedKvStoreApplication.class, args);
    }

}
