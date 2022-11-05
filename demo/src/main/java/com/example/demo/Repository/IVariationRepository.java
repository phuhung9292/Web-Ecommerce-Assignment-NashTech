package com.example.demo.Repository;

import com.example.demo.Entity.TblVariationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IVariationRepository extends JpaRepository<TblVariationEntity, Integer> {
    List<TblVariationEntity> findAllByCategoryId(int id);
}
