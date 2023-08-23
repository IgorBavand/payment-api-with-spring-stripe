package com.payment.payment_example.modules.payment.repository;

import com.payment.payment_example.modules.payment.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {
}
