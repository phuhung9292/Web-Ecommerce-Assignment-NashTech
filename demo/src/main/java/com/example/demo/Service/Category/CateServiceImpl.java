package com.example.demo.Service.Category;

import com.example.demo.Entity.TblCategoryEntity;
import com.example.demo.Entity.TblVariationEntity;
import com.example.demo.Repository.ICategoryRepository;
import com.example.demo.Service.Variation.VariationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service @AllArgsConstructor @Transactional
public class CateServiceImpl implements CategoryService{
    private ICategoryRepository categoryRepository;
    private VariationService variation;

    @Override
    public List<TblCategoryEntity> findAll() {
        return categoryRepository.findAllByIsActive(true);
    }

    @Override
    public  TblCategoryEntity  save(TblCategoryEntity entity)
    {
        entity.setIsActive(true);
        TblCategoryEntity category=categoryRepository.save(entity);
        TblVariationEntity v1 = new TblVariationEntity();
        TblVariationEntity v2 = new TblVariationEntity();
        v1.setCategoryId(category.getId());
        v2.setCategoryId(category.getId());
        v1.setName("size");
        v2.setName("color");
        variation.save(v1);
        variation.save(v2);
        return category;
    }

    @Override
    public TblCategoryEntity findById(int id) {
        return categoryRepository.findByIsActiveAndId(true,id);
    }

    @Override
    public ResponseEntity<?> updateCateByProductId(int id, TblCategoryEntity entity){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if(categoryRepository.findById(id)==null){
            map.put("message","Not Found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
        if(entity.getTypeCategory().isEmpty()){
            map.put("message","Is Empty!");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        TblCategoryEntity entity1 = categoryRepository.findById(id).get();
        entity1.setTypeCategory(entity.getTypeCategory());
        categoryRepository.save(entity1);
        map.put("message","Success");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
