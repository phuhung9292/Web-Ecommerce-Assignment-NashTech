package com.example.demo.Repository;

import com.example.demo.Entity.TblShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShoppingCartRepository extends JpaRepository<TblShoppingCartEntity,Integer> {

}
