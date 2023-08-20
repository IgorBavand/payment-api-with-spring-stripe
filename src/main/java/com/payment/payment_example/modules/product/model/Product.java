package com.payment.payment_example.modules.product.model;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "price_id")
    private String priceId;
}
