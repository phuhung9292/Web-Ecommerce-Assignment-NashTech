package com.example.demo.Repository;

import com.example.demo.Entity.TblOrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderStatusRepository extends JpaRepository<TblOrderStatusEntity,Integer> {
    TblOrderStatusEntity findById(int id);
}
