package com.example.demo.Repository;

import com.example.demo.Entity.TblProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<TblProductEntity,Integer> {
    List<TblProductEntity> findAllByNameContains(String s);

    List<TblProductEntity> findTblProductEntitiesByCategoryId(int id);
}
