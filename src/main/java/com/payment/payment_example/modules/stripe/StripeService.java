package com.payment.payment_example.modules.stripe;

import com.payment.payment_example.modules.product.dto.ProductRequest;
import com.payment.payment_example.modules.user.model.User;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${app-config.stripe.secretKey}")
    private String stripeSecretKey;

    public Customer registerCustomer(User user) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("email", user.getUsername());

        return Customer.create(params);
    }

    public Product registerProduct(ProductRequest product) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        ProductCreateParams paramsProduct =
                ProductCreateParams.builder()
                        .setName(product.getName())
                        .setDescription(product.getDescription())
                        .build();

        return Product.create(paramsProduct);
    }

    public Price registerPrice(String productId) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        PriceCreateParams paramsPrice =
                PriceCreateParams.builder()
                        .setProduct(productId)
                        .setUnitAmount(100L)
                        .setCurrency("BRL")
                        .build();

        return Price.create(paramsPrice);
    }
}
