package com.example.demo.Repository;

import com.example.demo.Entity.TblVariationOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IVariation_OptionRepository extends JpaRepository<TblVariationOptionEntity,Integer> {
//    @Query(value = "select vo.* from tbl_Product_Configuration proConfig join tbl_variation_option vo on proConfig.variation_option_id = vo.id where product_item_id = 25",nativeQuery = true)
//    TblVariationOptionEntity findSizeAndColor
    List<TblVariationOptionEntity> findAllByVariationId(int id);
}
