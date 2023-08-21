package com.payment.payment_example.modules.product.controller;

import com.payment.payment_example.modules.product.dto.ProductRequest;
import com.payment.payment_example.modules.product.model.Product;
import com.payment.payment_example.modules.product.service.ProductService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody ProductRequest productRequest) throws StripeException {
        return productService.registerProduct(productRequest);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

}
