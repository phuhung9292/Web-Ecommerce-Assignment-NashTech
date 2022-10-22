package com.example.demo.Repository;

import com.example.demo.Entity.TblProductConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductConfigRepository extends JpaRepository<TblProductConfigurationEntity,Integer> {

}
