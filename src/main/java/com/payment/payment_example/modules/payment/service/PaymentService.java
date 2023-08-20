package com.payment.payment_example.modules.payment.service;

import com.payment.payment_example.modules.user.service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private static final String YOUR_DOMAIN = "http://localhost:8090";

    private final UserService userService;

    @Value("${app-config.stripe.secretKey}")
    private String stripeSecretKey;

    @Value("${app-config.webhook.secretKey}")
    private String webhookSecretKey;

    public String createCheckoutSession(String priceId, Long amount) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(YOUR_DOMAIN + "/payment/success?success=true")
                        .setCancelUrl(YOUR_DOMAIN + "/payment/canceled?canceled=true")
                        .setCustomerEmail(userService.getUserAthenticated())
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(amount)
                                        .setPrice(priceId)
                                        .build())
                        .build();
        Session session = Session.create(params);

        return session.getUrl();
    }

    public ResponseEntity<String> webhookListener(String payload, String sigHeader) throws Exception {
        Event event = null;
        try {
            event = Webhook.constructEvent(payload, sigHeader, webhookSecretKey);
        } catch (SignatureVerificationException e) {
            System.out.println("Failed signature verification");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;

        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }

        switch (event.getType()) {
            case "payment_intent.succeeded":
                // ...
                break;
            case "payment_method.attached":
                // ...
                break;
            case "payment_intent.created":
                // ...
                break;
            case "customer.created":
                // ...
                break;
            case "charge.succeeded":
                // ...
                break;
            case "checkout.session.completed":
                // ...
                break;
            // ... handle other event types
            default:
                // Unexpected event type
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
