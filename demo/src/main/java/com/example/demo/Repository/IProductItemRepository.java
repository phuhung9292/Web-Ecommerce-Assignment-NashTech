package com.example.demo.Repository;

import com.example.demo.Entity.TblProductItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductItemRepository extends JpaRepository<TblProductItemEntity, Integer> {

}
