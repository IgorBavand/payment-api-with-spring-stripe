package com.payment.payment_example.modules.payment.service;

import com.payment.payment_example.modules.payment.model.Sale;
import com.payment.payment_example.modules.payment.repository.SalesRepository;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesProcessingService {

    private final SalesRepository salesRepository;

    public void createSale(Event event) {
        ;
       // salesRepository.save(sale);
    }

}
