package com.payment.payment_example.modules.user.service;

import com.payment.payment_example.modules.user.dto.UserResponse;
import com.payment.payment_example.modules.user.enums.ERole;
import com.payment.payment_example.modules.user.model.User;
import com.payment.payment_example.modules.user.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app-config.stripe.secretKey}")
    private String stripeSecretKey;

    public ResponseEntity<?> registerUser(User newUser) throws StripeException {
        Optional<User> user = userRepository.findByUsername(newUser.getUsername());
        if (user.isPresent()) {
            return ResponseEntity.badRequest().body("Username already used.");
        }
        var stripeCustomer = registerCustomer(newUser);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.getRoles().add(ERole.ROLE_USER);
        newUser.setCustomerId(stripeCustomer.getId());

        return ResponseEntity.ok(UserResponse.of(userRepository.save(newUser)));
    }

    public String getUserAthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        } else {
            new ChangeSetPersister.NotFoundException();
        }
        return null;
    }

    private Customer registerCustomer(User user) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("email", user.getUsername());

        return Customer.create(params);
    }
}
