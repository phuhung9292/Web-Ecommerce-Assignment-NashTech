package com.example.demo.Repository;

import com.example.demo.Entity.TblVariationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVariationRepository extends JpaRepository<TblVariationEntity, Integer> {
}
