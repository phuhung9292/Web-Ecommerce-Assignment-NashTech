package com.example.demo.Service.ProductConfig;

import com.example.demo.Entity.TblProductConfigurationEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductConfigService {
    List<TblProductConfigurationEntity> findAll();


}
