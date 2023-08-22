package com.payment.payment_example.modules.product.service;

import com.payment.payment_example.modules.product.dto.ProductRequest;
import com.payment.payment_example.modules.product.dto.ProductResponse;
import com.payment.payment_example.modules.product.repository.ProductRepository;
import com.payment.payment_example.modules.stripe.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Value("${app-config.stripe.secretKey}")
    private String stripeSecretKey;

    private final ProductRepository productRepository;
    private final StripeService stripeService;

    public ResponseEntity<?> registerProduct(ProductRequest productRequest) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        var product = stripeService.registerProduct(productRequest);
        var price = stripeService.registerPrice(product.getId());

        var productSave = com.payment.payment_example.modules.product.model.Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .productId(product.getId())
                .priceId(price.getId())
                .build();

        productRepository.save(productSave);

        return ResponseEntity.ok().body(productSave);
    }

    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok().body(ProductResponse.of(productRepository.findAll()));
    }
}
