package com.payment.payment_example.modules.user.service;

import com.payment.payment_example.modules.user.enums.ERole;
import com.payment.payment_example.modules.user.model.User;
import com.payment.payment_example.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
}
