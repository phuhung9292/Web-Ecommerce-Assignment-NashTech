package com.example.demo.Service.ProductItem;

import com.example.demo.Entity.TblProductItemEntity;
import com.example.demo.Repository.IProductItemRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductServiceItemImpl implements ProductItemService{

    private IProductItemRepository repository;
    private ModelMapper modelMapper;

    @Override
    public List<TblProductItemEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<TblProductItemEntity>  save(TblProductItemEntity entity) {
        Date date = Calendar.getInstance().getTime();
        entity.setCreateDate(date);
        entity.setIsActice(true);
        return ResponseEntity.ok().body(repository.save(entity));
    }
    @Override
    public ResponseEntity<?> updateProductItem(TblProductItemEntity entity){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Date date = Calendar.getInstance().getTime();
        TblProductItemEntity product= repository.findById(entity.getId()).get();

        try {
            entity.setCreateDate(product.getCreateDate());
            entity.setUpdateDate(date);
            entity.setProductId(product.getProductId());
            entity.setIsActice(true);
            map.put("status", 1);
            map.put("message", "update successfully!");
//            ProductDto dto = modelMapper.map(product,ProductDto.class);
            map.put("data",repository.save(entity));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}
