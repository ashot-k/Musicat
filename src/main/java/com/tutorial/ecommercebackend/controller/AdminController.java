package com.tutorial.ecommercebackend.controller;


import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/admin")
public class AdminController {

    ProductRepository products;

    @Autowired
    public AdminController(ProductRepository products) {
        this.products = products;
    }


    @GetMapping("")
    String index() {
        return "/admin/index";
    }

    @GetMapping("/list-products")
    String listProducts(Model model) {
        model.addAttribute("products", products.findAll());
        model.addAttribute("product", new Product());
        return "/admin/list-products";
    }
    /*
    @PostMapping("/addProduct")
    String addProduct(@ModelAttribute("product") Product newProduct){
       WebClient webClient = WebClient.create("http://localhost:3000");


         newProduct = webClient.post()
                .uri("/products")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(empl), Product.class)
                .retrieve()
                .bodyToMono(Employee.class);
    }

     */

}
