package com.example.demo.Repository;

import com.example.demo.Entity.TblVariationOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IVariation_OptionRepository extends JpaRepository<TblVariationOptionEntity,Integer> {
    @Query(value = "select vo.* from tbl_variation_option vo join tbl_variation v on v.id=vo.variation_id where v.name='size' and  v.category_id=(select p.category_id from tbl_Product p where p.id = ?1)  order by vo.variation_id ",nativeQuery = true)
    List<TblVariationOptionEntity> findAllSizeByCateId(int productId);

    @Query(value = "select vo.* from tbl_variation_option vo join tbl_variation v on v.id=vo.variation_id where v.name='color' and  v.category_id=(select p.category_id from tbl_Product p where p.id = ?1)  order by vo.variation_id  ",nativeQuery = true)
    List<TblVariationOptionEntity> findAllColorByCateId(int productId);
    List<TblVariationOptionEntity> findAllByVariationId(int id);


}
