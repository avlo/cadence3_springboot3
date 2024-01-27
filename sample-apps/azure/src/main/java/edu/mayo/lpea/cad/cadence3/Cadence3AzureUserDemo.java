package edu.mayo.lpea.cad.cadence3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Cadence3AzureUserDemo extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Cadence3AzureUserDemo.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(Cadence3AzureUserDemo.class, args);
  }
}
