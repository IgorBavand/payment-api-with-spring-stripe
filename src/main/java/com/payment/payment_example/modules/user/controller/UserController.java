package com.payment.payment_example.modules.user.controller;

import com.payment.payment_example.modules.product.model.Product;
import com.payment.payment_example.modules.user.model.User;
import com.payment.payment_example.modules.user.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService service;

    @PostMapping("/signup")
    @ApiOperation(httpMethod = "POST", value = "register new user", response = User[].class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer {token}", required = true, paramType = "header")
    })
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        return ResponseEntity.ok().body(service.registerUser(newUser));
    }

}
