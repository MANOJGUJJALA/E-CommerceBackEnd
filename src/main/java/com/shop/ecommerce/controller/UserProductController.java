package com.shop.ecommerce.controller;


import com.shop.ecommerce.modal.Product;
import com.shop.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api")
public class UserProductController {

    private ProductService productService;

    public UserProductController(ProductService productService) {
        this.productService=productService;
    }


    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestHeader("Authorization") String jwt,@RequestParam String category,
                                                                      @RequestParam List<String> color, @RequestParam List<String> size, @RequestParam Integer minPrice,
                                                                      @RequestParam Integer maxPrice, @RequestParam Integer minDiscount, @RequestParam String sort,
                                                                      @RequestParam String stock, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){

        System.out.println("Category "+category+" Clor "+color+" Sort "+sort+" Stock "+stock

        +" size "+size+" maxprice "+maxPrice+" minPri "+minPrice+" jwt"+jwt);

        Page<Product> res= productService.getAllProduct(category, color, size, minPrice, maxPrice, minDiscount, sort,stock,pageNumber,pageSize);

        System.out.println("complete products"+res);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

    }
}
