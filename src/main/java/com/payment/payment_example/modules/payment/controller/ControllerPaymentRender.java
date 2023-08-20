package com.payment.payment_example.modules.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("payment")
public class ControllerPaymentRender {

    @GetMapping("/success")
    public String paymentSuccess(boolean success) {
        return "success";
    }

    @GetMapping("/cancelled")
    public String paymentCancel(boolean canceled) {
        return "cancelled";
    }

}
