package com.group.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SpringdocConfig {

    @Bean
    OpenAPI customOpenAPI() {
        Info info = new Info();
        info.title("Spring Boot Demo Application");
        info.description("This is a documentation of API developed for Spring Boot Demo");
        info.version("1.0");

        List<Server> servers = new ArrayList<>();

        Server server = new Server();
        server.description("Localhost");
        server.url("http://localhost:8080");
        servers.add(server);

        return new OpenAPI().components(new Components()).info(info).servers(servers);
    }

}
