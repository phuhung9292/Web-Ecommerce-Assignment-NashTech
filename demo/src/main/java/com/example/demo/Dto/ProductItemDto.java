package com.example.demo.Dto;

import com.example.demo.Entity.TblVariationOptionEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class ProductItemDto {
    private int id;
    private String name;
    private List<imageDto> productImage;
    private List<sizeDto> size;
    private List<colorDto> color;
}
