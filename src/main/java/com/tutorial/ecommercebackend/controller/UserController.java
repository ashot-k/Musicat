package com.tutorial.ecommercebackend.controller;

import com.tutorial.ecommercebackend.entity.product.Genre;
import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.entity.shopping.Cart;
import com.tutorial.ecommercebackend.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.tutorial.ecommercebackend.service.ProductServiceImpl.totalPages;

@Controller
@SessionAttributes("cart")
public class UserController {
    ProductService productService;
    public static final String indexUrl = "http://localhost:8088/";
    private static final int pageSize = 3;
    private static final int pageNo = 0;


    List<Product> currentPageProducts;

    @Autowired
    public UserController(ProductService productService) {
        this.productService = productService;
        this.currentPageProducts = new ArrayList<>();
    }
    @ModelAttribute("cart")
    public Cart shoppingCart() {
        return new Cart();
    }

    @ModelAttribute("genreList")
    public List<String> populateGenreList() {
        return Genre.genreList;
    }

    @ModelAttribute("products")
    public Page<Product> populateProducts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo
//                                        ,@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        Page<Product> page = productService.findAllProductsPaged(pageNo, pageSize);
        currentPageProducts = page.toList();
        System.out.println("in model method currentpageproducts variable: " + currentPageProducts);
        return page;
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
    public String homePage() {
        System.out.println("Total pages: " + totalPages);
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

    @PostMapping("/add-to-cart")
    ResponseEntity addToCart(@RequestBody Object itemId,
                             @ModelAttribute Cart cart) {

       Product productToAdd = null;
        System.out.println(itemId);
        for (Product p :
                currentPageProducts) {
            if (p.getId()  == Long.parseLong((String) itemId)){
                productToAdd = p;
            }
        }
        cart.getCartItems().add(productToAdd);
        System.out.println(cart.getCartItems());
        //session.setAttribute("cart", );
        System.out.println("called add to cart in spring boot ");
        return ResponseEntity.ok(HttpStatus.OK);
    }




/*
    @GetMapping("/set-session")
    public String setSessionAttribute(HttpSession session) {
            session.setAttribute("username", "John");
        System.out.println(session.getAttribute("username"));
        return "redirect:/get-session";
    }

    @GetMapping("/get-session")
    public String getSessionAttribute(HttpSession session) {
        String username = (String) session.getAttribute("username");
        System.out.println("Username: " + username);
        return "index";
    }*/


}

