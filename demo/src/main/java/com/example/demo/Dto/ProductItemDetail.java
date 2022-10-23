package com.example.demo.Dto;

import com.example.demo.Entity.TblVariationOptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemDetail {
    private int id;
    private int quantity;
    private String productImage;
    private double price;
    private boolean isActice;
}
