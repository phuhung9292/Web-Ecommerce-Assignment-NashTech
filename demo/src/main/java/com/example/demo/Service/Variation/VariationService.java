package com.example.demo.Service.Variation;

import com.example.demo.Entity.TblVariationEntity;

import java.util.List;
import java.util.Optional;

public interface VariationService {
    <S extends TblVariationEntity> S save(S entity);

    Optional<TblVariationEntity> findById(Integer integer);

    List<TblVariationEntity> findAll();

    List<TblVariationEntity> findByCateId(int id);
}
