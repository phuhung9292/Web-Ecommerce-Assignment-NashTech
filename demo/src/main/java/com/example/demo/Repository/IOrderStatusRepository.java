package com.example.demo.Repository;

import com.example.demo.Entity.TblOrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IOrderStatusRepository extends JpaRepository<TblOrderStatusEntity,Integer> {
    TblOrderStatusEntity findById(int id);

    List<TblOrderStatusEntity> findAll();
}
