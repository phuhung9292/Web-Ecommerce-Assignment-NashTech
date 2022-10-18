package com.example.demo.Repository;

import com.example.demo.Entity.TblCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<TblCategoryEntity,Integer> {
    List<TblCategoryEntity> findAllByIsActive(boolean check);
    TblCategoryEntity findByIsActiveAndId(boolean check, int id);
}
