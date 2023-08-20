package com.payment.payment_example.modules.user.service;

import com.payment.payment_example.modules.user.enums.ERole;
import com.payment.payment_example.modules.user.model.User;
import com.payment.payment_example.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> registerUser(User newUser) {
        Optional<User> user = userRepository.findByUsername(newUser.getUsername());
        if (user.isPresent()) {
            return ResponseEntity.badRequest().body("Username already used.");
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.getRoles().add(ERole.ROLE_USER);
        userRepository.save(newUser);
        return ResponseEntity.ok("User registred.");
    }
}
