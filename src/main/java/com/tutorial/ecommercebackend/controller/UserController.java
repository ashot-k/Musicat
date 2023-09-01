package com.tutorial.ecommercebackend.controller;

import com.tutorial.ecommercebackend.entity.product.Genre;
import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.entity.shopping.Cart;
import com.tutorial.ecommercebackend.entity.shopping.CartItem;
import com.tutorial.ecommercebackend.entity.user.LocalUser;
import com.tutorial.ecommercebackend.entity.user.Role;
import com.tutorial.ecommercebackend.security.SecurityUtils;
import com.tutorial.ecommercebackend.service.CartService;
import com.tutorial.ecommercebackend.service.LocalUserService;
import com.tutorial.ecommercebackend.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.tutorial.ecommercebackend.security.SecurityUtils.checkDupleUsername;
import static com.tutorial.ecommercebackend.service.ProductServiceImpl.totalPages;

@Controller
@SessionAttributes("cart")
public class UserController {
    ProductService productService;
    CartService cartService;

    LocalUserService userService;
    public static final String indexUrl = "http://localhost:8088/";
    private static final int pageSize = 3;
    private static final int pageNo = 0;
    List<Product> currentPageProducts;

    @Autowired
    public UserController(ProductService productService, CartService cartService,  LocalUserService userService) {
        this.productService = productService;
        this.cartService = cartService;
        this.currentPageProducts = new ArrayList<>();
        this.userService =  userService;
    }

    @GetMapping("/login")
    String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    String login() {
        return "index";
    }

    @GetMapping("/register")
    String register(@ModelAttribute("user") LocalUser user) {
        return "registration-form";
    }

    @PostMapping("/register")
    String registerUser(@Valid @ModelAttribute("user") LocalUser user,
                        BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            checkDupleUsername(userService, user.getUsername(), model);
            System.out.println("errors in user registration: " + result);
            return "registration-form";
        } else if (checkDupleUsername(userService, user.getUsername(), model)) {
            return "registration-form";
        }

        String purePassword = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);
        LocalUser savedUser = userService.saveUser(user);
        Cart cart = new Cart(savedUser);
        cartService.saveCart(cart);

        SecurityUtils.autoLogin(request, savedUser.getUsername(), purePassword);
        return "redirect:/";
    }

    @ModelAttribute("cartCounter")
    public Integer cardCounter() {
        return 0;
    }

    @ModelAttribute("cart")
    public Cart shoppingCartAnon() {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
            return null;
        Cart c;
        c = new Cart();
        System.out.println(c);
        return c;
    }
    @ModelAttribute("cart")
    public Cart shoppingCartForCustomer() {
        if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
            return null;
        System.out.println("authenticated cart method");
       return cartService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
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
    @PreAuthorize("isAnonymous()")
    ResponseEntity addToCart(@RequestBody Object itemId,
                             @ModelAttribute Cart cart) {
        System.out.println("called addToCart spring");
        if (cart.getCartItems() == null) System.out.println("true");

        Product productToAdd = null;

        for (Product p : currentPageProducts) {
            if (p.getId() == Long.parseLong((String) itemId)) {
                productToAdd = p;
            }
        }

        System.out.println("Item id to be added: " + productToAdd.getName());
        cart.getCartItems().add(new CartItem(productToAdd));
        if (!cart.getCartItems().isEmpty()) {
            for (CartItem cartItem : cart.getCartItems()) {
                System.out.print("[" + cartItem.getProduct().getName() + "] ");
            }
            System.out.println();
        }

        System.out.println(cart.getCartItems().size());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

