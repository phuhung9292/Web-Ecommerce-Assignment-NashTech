package com.example.demo.Service.ProductConfig;

import com.example.demo.Entity.TblProductConfigurationEntity;
import com.example.demo.Repository.IProductConfigRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @AllArgsConstructor
public class ProductConfigServiceImpl implements ProductConfigService{
    private IProductConfigRepository repository;

    @Override
    public List<TblProductConfigurationEntity> findAll() {
        return repository.findAll();
    }
}
