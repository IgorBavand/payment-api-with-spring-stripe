package com.payment.payment_example.modules.product.service;

import com.payment.payment_example.modules.product.dto.ProductRequest;
import com.payment.payment_example.modules.product.repository.ProductRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
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

    public ResponseEntity<?> registerProduct(ProductRequest productRequest) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        ProductCreateParams paramsProduct =
                ProductCreateParams.builder()
                        .setName(productRequest.getName())
                        .setDescription(productRequest.getDescription())
                        .build();

        Product product = Product.create(paramsProduct);

        PriceCreateParams paramsPrice =
                PriceCreateParams.builder()
                        .setProduct(product.getId())
                        .setUnitAmount(100L)
                        .setCurrency("BRL")
                        .build();

        Price price = Price.create(paramsPrice);

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

    public List<com.payment.payment_example.modules.product.model.Product> getAll() {
        return productRepository.findAll();
    }
}
