package com.shop.model.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class Item {

    @NonNull
    private Long id;
    @NonNull
    private String description;
    @NonNull
    private Double defaultPrice;
    //The price should have been stored as Long, expressing the cents of the currency to calculate the decimals as later as possible
    //It has been kept this way for simplicity

}
