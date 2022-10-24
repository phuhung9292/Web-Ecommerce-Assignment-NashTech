package com.example.demo.Repository;

import com.example.demo.Entity.TblOrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderHistoryRepository extends JpaRepository<TblOrderHistoryEntity,Integer> {

}
