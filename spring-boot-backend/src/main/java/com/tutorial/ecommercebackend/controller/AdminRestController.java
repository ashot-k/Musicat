package com.tutorial.ecommercebackend.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.entity.product.Track;
import com.tutorial.ecommercebackend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//@PreAuthorize("hasRole('ADMIN')")
public class AdminRestController {
    ProductService productService;
    List<String> trackNames;

    @Autowired
    public AdminRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create-product")
    public ResponseEntity<String> createProduct(@Valid @RequestBody Product newProduct, BindingResult result) {

        if(result.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            for (FieldError error :
                    result.getFieldErrors()) {
                errorMsg.append(error.getDefaultMessage()).append("\n");
            }
            return new ResponseEntity<>(errorMsg.toString(), HttpStatus.BAD_REQUEST);
        }
        else {
            productService.saveProduct(newProduct);
            return new ResponseEntity<>(newProduct.getName() + ": has been saved successfully.\n", HttpStatus.CREATED);
        }
    }
}
