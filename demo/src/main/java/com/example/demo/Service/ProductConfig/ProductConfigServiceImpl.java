package com.example.demo.Service.ProductConfig;

import com.example.demo.Entity.ManyToManyId.TblProductConfigurationId;
import com.example.demo.Entity.TblProductConfigurationEntity;
import com.example.demo.Entity.TblProductItemEntity;
import com.example.demo.Entity.TblVariationOptionEntity;
import com.example.demo.Repository.IProductConfigRepository;
import com.example.demo.Repository.IProductItemRepository;
import com.example.demo.Repository.IVariation_OptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service @AllArgsConstructor
public class ProductConfigServiceImpl implements ProductConfigService{
    private IProductConfigRepository repository;


    @Override
    public List<TblProductConfigurationEntity> findAll() {
        return repository.findAll();
    }



}
