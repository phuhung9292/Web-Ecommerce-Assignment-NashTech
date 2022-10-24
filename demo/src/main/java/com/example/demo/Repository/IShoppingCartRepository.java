package com.example.demo.Repository;

import com.example.demo.Entity.TblShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShoppingCartRepository extends JpaRepository<TblShoppingCartEntity,Integer> {
    TblShoppingCartEntity findTblShoppingCartEntityByUserid(Integer userid);
}
