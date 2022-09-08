package com.example.demo.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Body {
    private String stamp;
    private String reference;
    private int amount;
    private String currency;
    private String language;
    private List<Item> items;
    private Customer customer;
    private RedirectUrls redirectUrls;
}
