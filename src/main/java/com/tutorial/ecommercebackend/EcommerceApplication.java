package com.tutorial.ecommercebackend;

import com.tutorial.ecommercebackend.entity.product.Inventory;
import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ProductService productService){

        return runner ->{
         //   createProduct(productService);
        };
    }

    private void createProduct(ProductService productService) {
        Product p = new Product("Lateralus", "Tool","good", 55.0, new Inventory(15), 2001, "Alternative Metal");

        productService.save(p);

        System.out.println(p.getId());
    }
}
