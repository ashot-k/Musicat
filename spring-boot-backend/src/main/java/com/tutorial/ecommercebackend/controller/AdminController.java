package com.tutorial.ecommercebackend.controller;


import com.tutorial.ecommercebackend.entity.product.Genre;
import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.entity.product.Track;
import com.tutorial.ecommercebackend.service.ProductService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    ProductService productService;
    List<String> trackNames;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("genreList")
    public List<String> populateGenreList() {
        List<String> genreList = Genre.genreList;
        return genreList;
    }
    @GetMapping()
    String showIndex() {
        return "/admin/admin-index";
    }

    @GetMapping("/list-products")
    String listProducts(Model model) {
        model.addAttribute("products", productService.findAllProductsByOrderByNameAsc());
        return "/admin/list-products";
    }

    @PostMapping("/add-tracks")
    @ResponseBody
    String addTracks(@RequestBody Object tracks) {
        System.out.println("called addTracks");
        this.trackNames = (List<String>) tracks;
        System.out.println(this.trackNames.toString());
        return trackNames.toString();
    }


    @GetMapping("/add-product")
    String showNewProductForm(@ModelAttribute("product") Product product, Model model) {
        return "/admin/add-product-form";
    }


    @PostMapping("/add-product")
    String createProduct(@ModelAttribute("product") Product product, BindingResult result,
                          @ModelAttribute("imageId") MultipartFile file) throws IOException {
        Product savedProduct;
            if (trackNames != null)
                productService.saveTracks(product, trackNames);
            savedProduct = productService.saveProduct(product);
            System.out.println(product.getName() + " added");
         /*
          if(!productService.saveTracks(savedProduct, trackNames)){
              return"/admin/add-product-form";
          }
          */
        return "redirect:/admin/list-products";
    }


    @GetMapping("/edit-product")
    String editProductForm(@RequestParam(value = "productId", required = false) Long id, Model model) {

        Optional<Product> selectedProduct = productService.findProductById(id);
        model.addAttribute("product", selectedProduct);


        return "/admin/edit-product-form";
    }

    @PostMapping("/edit-product")
    String saveEditedProduct(@Valid @ModelAttribute("product") Product product,
                             @ModelAttribute("imageId") MultipartFile file,
                             BindingResult result) throws IOException {
        Product savedProduct;
        if (result.hasErrors()) {
            System.out.println(result);
            return "/admin/edit-product-form";
        } else {
            savedProduct = productService.saveProduct(product);
            if (trackNames != null)
                productService.saveTracks(product, trackNames);
        }
        return "redirect:/admin/list-products";
    }

    //save album image
    @GetMapping("/delete")
    String deleteProduct(@RequestParam("productId") Long id) throws IOException {
        Optional<Product> p = productService.findProductById(id);
        Product product = p.get();

        List<Track> t = product.getTracks();
        List<Long> trackIds = new ArrayList<>();
        for (Track tr : t)
            trackIds.add(tr.getId());
        if (!trackIds.isEmpty())
            productService.removeTracksByIdIn(trackIds);
        productService.deleteProductById(id);
        System.out.println(product.getName() + " deleted");
        return "redirect:/admin/list-products";
    }
}
