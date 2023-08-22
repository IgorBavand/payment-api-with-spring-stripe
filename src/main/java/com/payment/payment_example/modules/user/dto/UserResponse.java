package com.payment.payment_example.modules.user.dto;

import com.payment.payment_example.modules.user.model.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
    private Long id;
    private String username;
    private String name;
    private String customerId;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .customerId(user.getCustomerId())
                .build();
    }
}
