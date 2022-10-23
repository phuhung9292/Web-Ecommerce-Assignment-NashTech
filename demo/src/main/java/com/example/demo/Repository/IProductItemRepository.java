package com.example.demo.Repository;

import com.example.demo.Entity.TblProductItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductItemRepository extends JpaRepository<TblProductItemEntity, Integer> {
   List<TblProductItemEntity> findAllByProductId(Integer id);
   TblProductItemEntity findTblProductItemEntityById(Integer id);
   @Query(value = "SELECT * from tbl_Product_Item p where p.id = (Select proConfig.product_item_id from tbl_Product_Item proItem join tbl_Product pro on proItem.product_id=pro.id join tbl_Product_Configuration proConfig on proItem.id=proConfig.product_item_id where pro.id= ?1 AND variation_option_id in (?2,?3) group by product_item_id Having count(variation_option_id)=2)",nativeQuery = true)
   TblProductItemEntity findProducItemByProductIdAndSizeAndColor(Integer productId,Integer variation1,Integer variation2);
}
