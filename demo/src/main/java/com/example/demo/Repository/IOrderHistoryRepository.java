package com.example.demo.Repository;

import com.example.demo.Entity.TblOrderHistoryEntity;
import com.example.demo.Entity.TblProductItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderHistoryRepository extends JpaRepository<TblOrderHistoryEntity,Integer> {
    TblOrderHistoryEntity findTblOrderHistoryEntitiesByOrderIdAndProductItemId(int orderId, int productItemId);
}
