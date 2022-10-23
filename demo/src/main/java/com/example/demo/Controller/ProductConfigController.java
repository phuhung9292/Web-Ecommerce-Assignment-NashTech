package com.example.demo.Controller;

import com.example.demo.Entity.TblProductConfigurationEntity;
import com.example.demo.Entity.TblProductItemEntity;
import com.example.demo.Service.ProductConfig.ProductConfigService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//    @PostMapping()
//    public ResponseEntity<String> create(){
//        return service.save();
//    }
}
