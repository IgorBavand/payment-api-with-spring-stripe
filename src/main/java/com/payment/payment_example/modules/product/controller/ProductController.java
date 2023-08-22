package com.payment.payment_example.modules.product.controller;

import com.payment.payment_example.modules.product.dto.ProductRequest;
import com.payment.payment_example.modules.product.model.Product;
import com.payment.payment_example.modules.product.service.ProductService;
import com.stripe.exception.StripeException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(httpMethod = "POST", value = "register new product", response = Product[].class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer {token}", required = true, paramType = "header")
    })
    public ResponseEntity<?> register(@RequestBody ProductRequest productRequest) throws StripeException {
        return productService.registerProduct(productRequest);
    }

    @GetMapping
    @ApiOperation(httpMethod = "GET", value = "get all products", response = Product[].class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer {token}", required = true, paramType = "header")
    })
    public List<Product> getAll() {
        return productService.getAll();
    }

}
