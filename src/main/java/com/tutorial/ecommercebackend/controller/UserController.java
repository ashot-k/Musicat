package com.tutorial.ecommercebackend.controller;

import com.tutorial.ecommercebackend.entity.product.Genre;
import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.tutorial.ecommercebackend.service.ProductServiceImpl.totalPages;

@Controller
public class UserController {
    ProductService productService;
    private static final int pageSize = 5;
    private static final int pageNo = 0;

    @Autowired
    public UserController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("genreList")
    public List<String> populateGenreList() {
        return Genre.genreList;
    }

    @ModelAttribute("products")
    public Page<Product> populateProducts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo
//                                          ,@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return productService.findAllProductsPaged(pageNo, pageSize);
    }

    @ModelAttribute("username")
    public String localUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                username = userDetails.getUsername();
            }
        }
        return username;
    }

    @GetMapping("/")
    public String index() {
        System.out.println(totalPages);
        return "index";
    }

    @GetMapping("/item/{productId}")
    public String getProduct(Model model, @PathVariable String productId) {
        Product product = productService.findProductById(Long.parseLong(productId)).get();
        model.addAttribute("product", product);
        model.addAttribute("image", productService.findImagesByProduct(product).get(0).getImage());
        model.addAttribute("tracks", productService.findTracksByProduct(product));

        List<Product> relatedProducts = productService.findAllProductsByArtist(product.getArtist());
        relatedProducts.remove(product);
        model.addAttribute("relatedProducts", relatedProducts);

        return "product-page";
    }

    @GetMapping("/search")
    String search(Model model, String keyword) {
        if (keyword != null) {
            model.addAttribute("keyword", keyword);
            keyword = keyword.trim();
            Page<Product> specProducts = productService.findProductsByKeywordPaged(keyword, pageNo, pageSize);
            if (specProducts.isEmpty()) {
                model.addAttribute("noResults", "nothing found");
            }
            model.addAttribute("products", specProducts);
        }
        return "index";
    }
}

