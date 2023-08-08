package com.tutorial.ecommercebackend.controller;

import com.tutorial.ecommercebackend.entity.product.Product;
import com.tutorial.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.tutorial.ecommercebackend.entity.product.Genre.genreList;
import static com.tutorial.ecommercebackend.service.ProductServiceImpl.totalPages;


@Controller
public class UserController {
    ProductService productService;
    // private static final String pageSize = "6";

    @Autowired
    public UserController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                        @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize) {

        model.addAttribute("products", productService.findAllProducts(pageNo, pageSize));
        model.addAttribute("genreList", genreList);
        model.addAttribute("pageList", productService.findAllProductsPages(pageNo,pageSize));
        System.out.println(totalPages);
        return "index";
    }

    @GetMapping("/item/{productId}")
    public String getProduct(Model model, @PathVariable String productId) {
        Product product = productService.findProductById(Long.parseLong(productId)).get();
        model.addAttribute("product", product);
        model.addAttribute("image", productService.findImagesByProduct(product).get(0).getImage());
        model.addAttribute("tracks", productService.findTracksByProductId(product));
        List<Product> relatedProducts = productService.findAllProductsByArtist(product.getArtist());
        relatedProducts.remove(product);
        model.addAttribute("relatedProducts", relatedProducts);
        System.out.println(productId);

        return "product-page";
    }

    @GetMapping("/search")
    String search(Model model, String keyword) {
        if (keyword != null) {
            model.addAttribute("keyword", keyword);
            model.addAttribute("genreList", genreList);
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
