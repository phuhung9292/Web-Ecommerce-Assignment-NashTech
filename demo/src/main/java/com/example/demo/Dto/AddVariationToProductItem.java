package com.example.demo.Dto;

import com.example.demo.Entity.TblProductConfigurationEntity;
import com.example.demo.Entity.TblProductItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddVariationToProductItem {
    private TblProductItemEntity productItem;
    private TblProductConfigurationEntity entity;
}
