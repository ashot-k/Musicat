package com.tutorial.ecommercebackend.controller;

import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.repository.ProductRepository;
import com.tutorial.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {
    ProductService productService;

    @Autowired
    public UserController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "index";
    }

    @GetMapping("/item/{productId}")
    public String getProduct(Model model, @PathVariable String productId) {
        Product product = productService.findProductById(Long.parseLong(productId)).get();
        model.addAttribute("product", product);
        model.addAttribute("image", productService.findImagesByProduct(product).get(0).getImage());
        System.out.println(productId);

        return "product-page";
    }

    @GetMapping("/search")
    String search(Model model, String keyword) {
        if (keyword != null) {
            model.addAttribute("keyword", keyword);
            keyword = keyword.trim();
            System.out.println(keyword);
            List<Product> specProducts = productService.findAllProducts(keyword);
            if (specProducts.isEmpty()) {
                model.addAttribute("noResults", "nothing found");
            }
            model.addAttribute("products", specProducts);
        }
        return "index";
    }
}
