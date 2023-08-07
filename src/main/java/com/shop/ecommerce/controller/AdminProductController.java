package com.shop.ecommerce.controller;

import com.shop.ecommerce.exception.ProductException;
import com.shop.ecommerce.modal.Product;
import com.shop.ecommerce.request.CreateProductRequest;
import com.shop.ecommerce.response.ApiResponse;
import com.shop.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }


//    @PostMapping("/data")
//    public int dat(){
//        System.out.println("-----coming");
//        return 8;
//    }



    @PostMapping("/")
    public ResponseEntity<Product> createProductHandler(@RequestBody CreateProductRequest req) throws ProductException {

        Product createdProduct = productService.createProduct(req);

        return new ResponseEntity<Product>(createdProduct, HttpStatus.ACCEPTED);

    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] reqs) throws ProductException{
        System.out.println("Data to store :--> ");
//        for(CreateProductRequest product:reqs) {
//            productService.createProduct(product);
//        }

        ApiResponse res=new ApiResponse("products created successfully",true);
        return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
    }

}

