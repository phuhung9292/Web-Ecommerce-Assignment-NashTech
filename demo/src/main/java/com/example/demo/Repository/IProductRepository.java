package com.example.demo.Repository;

import com.example.demo.Entity.TblProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRepository extends JpaRepository<TblProductEntity,Integer> {
    List<TblProductEntity> findAllByNameContains(String s);

    List<TblProductEntity> findTblProductEntitiesByCategoryId(int id);


    @Query(value = "Select * from tbl_Product where id IN(select product_id from tbl_Product_Item where id IN (select TOP 3 productItemId as price from tbl_order_history group by productItemId order by sum(quantity) DESC))",nativeQuery = true)
    List<TblProductEntity> findTopSellProduct();
}
