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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.SpotifyApi;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.tutorial.ecommercebackend.security.SecurityUtils.checkDupleUsername;

@Controller
@SessionAttributes("cart")
public class UserController {
    /*
    TODO  FIX REMAINING TIME FOR CARTS
    */
    ProductService productService;
    CartService cartService;
    LocalUserService userService;
    public static final String indexUrl = "http://localhost:8088/";
    private static final int pageSize = 6;
    private static final int pageNo = 0;
    List<Product> currentPageProducts;

    @Autowired
    public UserController(ProductService productService, CartService cartService, LocalUserService userService) {
        this.productService = productService;
        this.cartService = cartService;
        this.userService = userService;
        this.currentPageProducts = new ArrayList<>();
    }

    @ModelAttribute("cartCounter")
    public Integer cartCounter() {
        return 0;
    }

    @ModelAttribute("cart")
    public Cart shoppingCart(HttpSession session, Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            Cart userCart = cartService.findByUsername(auth.getPrincipal().toString());
            userCart.acccessed();
            return userCart;
        } else {
            Cart anonCart = (Cart) session.getAttribute("cart");
            if (anonCart == null)
                anonCart = new Cart();
            return anonCart;
        }
    }
    @ModelAttribute("genreList")
    public List<String> populateGenreList() {
        return Genre.genreList;
    }
    @ModelAttribute("username")
    public String localUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;
        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                username = userDetails.getUsername();
                return username;
            }
        }
        return null;
    }

    String client_id = "2589aeb7464143cdaca99be59934136c";
    String client_secret = "c6d6314534e24a81902e7784a53c0c27";
    String redirectUri = "http://localhost:8080/";
    @GetMapping("/spotify-login")
    ResponseEntity<HttpStatus> spotifyLogin(){
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(client_id)
                .setClientSecret(client_secret)
                .setRedirectUri(URI.create(redirectUri))
                .build();
     return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/after-login")
    String afterLogin(HttpSession session, Model m) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Cart userCart = cartService.findByUsername(username);
        Cart anonCart = (Cart) session.getAttribute("cart");
        if (userCart == null) {
            userCart = cartService.saveCart(new Cart(userService.findbyUsername(username)));
        }
        if (anonCart != null) {
            if (anonCart.getId() != null) {
                List<CartItem> anonCartItems = anonCart.getCartItems();

                cartService.deleteById(anonCart.getId());
                cartService.addItems(userCart, anonCartItems);
            }
        }
        session.setAttribute("cart", userCart);
        m.addAttribute("cart", userCart);
        return "redirect:/";
    }
    @GetMapping("/")
    public String homePage(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                           Model model) {
        Page<Product> page = productService.findAllProductsPaged(pageNo, pageSize);
        model.addAttribute("products", page);
        currentPageProducts = page.toList();
        return "index";
    }

    @GetMapping("/search")
    String search(Model model, String keyword) {
        model.addAttribute("keyword", keyword);
        keyword = keyword.trim();
        Page<Product> specProductsPage = productService.findProductsByKeywordPaged(keyword, pageNo, pageSize);
        if (specProductsPage.isEmpty()) {
            model.addAttribute("noResults", "nothing found");
        }
        model.addAttribute("products", specProductsPage);
        currentPageProducts = specProductsPage.toList();
        return "index";
    }

    @GetMapping("/login")
    String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    String register(@ModelAttribute("user") LocalUser user) {
        return "registration-form";
    }

    @PostMapping("/register")
    String registerUser(@Valid @ModelAttribute("user") LocalUser user,
                        BindingResult result, Model model, HttpServletRequest request, HttpSession session) {
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

        SecurityUtils.autoLogin(request, savedUser.getUsername(), purePassword);
        afterLogin(session, model);
        return "redirect:/";
    }

    @GetMapping("/item/{productId}")
    public String getProduct(Model model, @PathVariable String productId) {
        Product product = productService.findProductById(Long.parseLong(productId)).get();
        model.addAttribute("product", product);
        model.addAttribute("image", productService.findImagesByProduct(product).get(0).getImage());
        model.addAttribute("tracks", product.getTracks());

        List<Product> relatedProducts = productService.findAllProductsByArtist(product.getArtist());
        relatedProducts.remove(product);
        model.addAttribute("relatedProducts", relatedProducts);

        currentPageProducts = new ArrayList<>();
        currentPageProducts.addAll(relatedProducts);
        currentPageProducts.add(product);
        return "product-page";
    }

    @GetMapping("/cart")
    String showCart(@ModelAttribute("user") LocalUser user) {
        return "cart";
    }

    CartItem prevAdded;

    @GetMapping("/add-new")
    @ResponseBody
    CartItem prev() {
        return prevAdded;
    }

    @PostMapping("/add-to-cart")
    ResponseEntity<HttpStatus> addToCart(@RequestBody Object itemId, @ModelAttribute Cart cart) {
        Product productToAdd = null;
        for (Product p : currentPageProducts) {
            if (p.getId() == Long.parseLong((String) itemId)) {
                productToAdd = p;
            }
        }
        prevAdded = cartService.addItem(cart, new CartItem(productToAdd));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-quantity/{cartItemId}")
    ResponseEntity<HttpStatus> updateQuantity(Model model,
                                              @PathVariable String cartItemId, @RequestBody int quantity) {
        Cart c = (Cart) model.getAttribute("cart");
        CartItem cartItem = c.findCartItem(Integer.parseInt(cartItemId));
        if (cartItem != null)
            cartItem.setQuantity(quantity);
        cartService.saveCart(c);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/remove-item/{cartItemId}")
    ResponseEntity<HttpStatus> removeCartItem(Model model,
                                              @PathVariable String cartItemId) {
        Cart c = (Cart) model.getAttribute("cart");
        c.getCartItems().remove(c.findCartItem(Integer.parseInt(cartItemId)));
        cartService.saveCart(c);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

