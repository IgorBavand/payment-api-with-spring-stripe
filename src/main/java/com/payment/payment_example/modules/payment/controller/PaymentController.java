package com.payment.payment_example.modules.payment.controller;

import com.payment.payment_example.modules.payment.service.PaymentService;
import com.stripe.exception.StripeException;
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
    public String createCheckoutSession() throws StripeException {
        return service.createCheckoutSession();
    }

    @PostMapping(value = "/webhook-listener")
    public ResponseEntity<String> webhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) throws Exception {
        return service.webhookListener(payload, sigHeader);
    }
}
