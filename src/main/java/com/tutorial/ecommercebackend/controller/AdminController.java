package com.tutorial.ecommercebackend.controller;


import com.tutorial.ecommercebackend.entity.product.Images;
import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.entity.product.Track;
import com.tutorial.ecommercebackend.repository.ImageRepository;
import com.tutorial.ecommercebackend.repository.TrackRepository;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    public static List<String> genreList = Arrays.asList("Pop", "Rock", "Metal", "Country", "EDM", "Rap");
    ProductService productService;
    List<String> trackNames;
    List<Track> tracks;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
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
        return "sus";
    }


    @GetMapping("/add-product")
    String showNewProductForm(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("genreList", genreList);
        return "/admin/add-product-form";
    }


    @PostMapping("/add-product")
    String saveNewProduct(@Valid @ModelAttribute("product") Product product,
                          @ModelAttribute("imageId") MultipartFile file,
                          BindingResult result) throws IOException {


        System.out.println(product.getId());
        System.out.println("called saveProducts");


        Product savedProduct;
        if (result.hasErrors()) {
            System.out.println(result);
            return "/admin/add-product-form";
        } else {

            savedProduct = productService.saveProduct(product);
            System.out.println(product.getName() + " added");
            handleTracks(savedProduct);
        }
        productService.saveImage(savedProduct, file);

        return "redirect:/admin/list-products";
    }


    @GetMapping("/edit-product")
    String showEditProductForm(@ModelAttribute("product") Product product,
                               @RequestParam(value = "productId", required = false) Long id, Model model) {
        Optional<Product> selectedProduct = productService.findProductById(id);
        System.out.println(productService.findImagesByProduct(selectedProduct.get()).get(0).getImage());
        Images image = productService.findImagesByProduct(selectedProduct.get()).get(0);

        model.addAttribute("product", selectedProduct);
        model.addAttribute("image", image.getImage());
        model.addAttribute("genreList", genreList);
        return "/admin/edit-product-form";
    }

    @PostMapping("/edit-product")
    String saveEditedProduct(@Valid @ModelAttribute("product") Product product,
                             @ModelAttribute("imageId") MultipartFile file,
                             BindingResult result) throws IOException {
        Product savedProduct;

        System.out.println(product.getId());
        System.out.println("called saveEditedProduct");

        if (result.hasErrors()) {
            System.out.println(result);
            return "/admin/edit-product-form";
        } else {
            if (trackNames != null)
                handleTracks(product);
            savedProduct = productService.saveProduct(product);
            if (!file.isEmpty())
                productService.saveImage(savedProduct, file);
        }


        System.out.println(product.getName() + " edited");
        return "redirect:/admin/list-products";
    }


    private void handleTracks(Product p) {
        if (!this.trackNames.isEmpty()) {
            tracks = new ArrayList<>();
            for (String str : trackNames)
                tracks.add(new Track(str, p));
            productService.deleteAllTracks(p);
            List<Track> savedTracks = productService.saveAllTracks(tracks);
            p.setTracks(savedTracks);
            this.tracks.clear();
            this.trackNames.clear();
        }
    }

    //save album image


    @GetMapping("/delete")
    String deleteProduct(@RequestParam("productId") Long id) throws IOException {
        Optional<Product> p = productService.findProductById(id);
        Product product = p.get();

        List<Images> i = productService.findImagesByProduct(product);
        if (!i.isEmpty()) {
            productService.deleteImageById(i.get(0).getId());
            FileUtils.deleteDirectory(new File("./src/main/resources/static/images/album-images/" + id));
        }

        List<Track> t = productService.findTracksByProductId(product);
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
