package com.example.demo.Service.VariationOption;

import com.example.demo.Entity.TblVariationOptionEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VariationOptionService {
    <S extends TblVariationOptionEntity> S save(S entity);

    List<TblVariationOptionEntity> findAll();

    TblVariationOptionEntity findById(int id);

    List<TblVariationOptionEntity> findAllByVariationId(int id);

    ResponseEntity<?> findSizeAndColorByProductId(int id);
}
