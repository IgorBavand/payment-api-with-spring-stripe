package com.payment.payment_example.modules.payment.model;

import com.payment.payment_example.modules.payment.enums.ESalesStatus;
import com.payment.payment_example.modules.product.model.Product;
import com.payment.payment_example.modules.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @Column(name = "status")
    private ESalesStatus status;

    @Column(name = "checkout_date")
    private LocalDateTime checkoutDate;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
}
