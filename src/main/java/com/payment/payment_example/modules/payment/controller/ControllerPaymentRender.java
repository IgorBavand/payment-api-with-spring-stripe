package com.payment.payment_example.modules.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("payment")
public class ControllerPaymentRender {

    @GetMapping("/success")
    public String paymentSuccess(boolean success, Model model) {
        model.addAttribute("message", "Hello from Spring Boot!");
        return "success";
    }

    @GetMapping("/cancelled")
    public String paymentCancel(boolean canceled, Model model) {
        model.addAttribute("message", "Hello from Spring Boot!");
        return "cancelled";
    }

}
