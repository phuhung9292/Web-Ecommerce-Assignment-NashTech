package com.example.demo.Controller;

import com.example.demo.Entity.TblProductConfigurationEntity;
import com.example.demo.Service.ProductConfig.ProductConfigService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productconfig")
@AllArgsConstructor
public class ProductConfigController {
    private ProductConfigService service;

    @GetMapping()
    public ResponseEntity<List<TblProductConfigurationEntity>> getList(){
        return ResponseEntity.ok().body(service.findAll());
    }
}
