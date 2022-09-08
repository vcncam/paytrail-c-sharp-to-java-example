package com.example.demo.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private int unitPrice;
    private int units;
    private int vatPercentage;
    private String productCode;
    private String deliveryDate;
}
