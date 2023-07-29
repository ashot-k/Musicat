package com.tutorial.ecommercebackend.controller;


import com.tutorial.ecommercebackend.entity.product.Images;
import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.repository.ImageRepository;
import com.tutorial.ecommercebackend.service.ProductService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    ProductService productService;
    ImageRepository images;

    @Autowired
    public AdminController(ProductService productService, ImageRepository images) {
        this.productService = productService;
        this.images = images;
    }

    @GetMapping()
    String showIndex() {
        return "/admin/admin-index";
    }

    @GetMapping("/list-products")
    String listProducts(Model model) {
        model.addAttribute("products", productService.findAllByOrderByNameAsc());
        return "/admin/list-products";
    }

    @GetMapping("/product-form")
    String showProductForm(@ModelAttribute("product") Product product,
                           @RequestParam(value = "productId", required = false) Long id, Model model) {
        if (id != null)
            model.addAttribute("product", productService.findById(id));
        return "/admin/product-form";
    }

    @PostMapping("/product-form")
    String saveProduct(@Valid @ModelAttribute("product") Product product,
                       @ModelAttribute("imageId") MultipartFile file,
                       BindingResult result) throws IOException {
        Product savedProduct;
        if (result.hasErrors()) {
            System.out.println(result);
            return "/admin/product-form";
        } else {
            savedProduct = productService.save(product);
            System.out.println(product.getName() + " added");
        }
        saveImage(savedProduct, file);

        return "redirect:/admin/list-products";
    }

    //save album image
    private void saveImage(Product product, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Images image = new Images();
        image.setImage(fileName);
        image.setProductId(product);
        Images savedImage = images.save(image);

        String uploadDir = "./src/main/resources/static/images/album-images/" + savedImage.getProductId().getId();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath))
            Files.createDirectories(uploadPath);
        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve("album_image.png");
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(filePath);
        } catch (IOException e) {
            System.out.println("failed saving file: " + e);
        }
    }

    @GetMapping("/delete")
    String deleteProduct(@RequestParam("productId") Long id) throws IOException {
        Optional<Product> p = productService.findById(id);
        List<Images> i = images.findByProductId(p.get());
        if (!i.isEmpty()) {
            images.deleteById(i.get(0).getId());
            FileUtils.deleteDirectory(new File("./src/main/resources/static/images/album-images/" + id));
        }
        productService.deleteById(id);
        System.out.println(p.get().getName() + " deleted");
        return "redirect:/admin/list-products";
    }
}
