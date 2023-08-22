package com.payment.payment_example.modules.payment.controller;

import com.payment.payment_example.modules.payment.service.PaymentService;
import com.payment.payment_example.modules.product.model.Product;
import com.stripe.exception.StripeException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService service;

    @GetMapping("/create-checkout-session")
    @ApiOperation(httpMethod = "GET", value = "get a checkout url")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer {token}", required = true, paramType = "header")
    })
    public String createCheckoutSession(String priceId, Long amount) throws StripeException {
        return service.createCheckoutSession(priceId, amount);
    }

    @PostMapping(value = "/webhook-listener")
    public ResponseEntity<String> webhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) throws Exception {
        return service.webhookListener(payload, sigHeader);
    }
}
