package com.payment.payment_example.modules.product.dto;

import com.payment.payment_example.modules.product.model.Product;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String productId;
    private String priceId;

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .productId(product.getProductId())
                .priceId(product.getPriceId())
                .build();
    }

    public static List<ProductResponse> of(List<Product> users) {
        if ( users == null ) {
            return null;
        }
        List<ProductResponse> list = new ArrayList<ProductResponse>( users.size() );
        users.forEach(user -> list.add(of(user)));
        return list;
    }
}
