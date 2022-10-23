package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailOnCartDto {
    private int productItemId;
    private String image;
    private int quantity;
    private double price;
    private boolean isActive;
}
