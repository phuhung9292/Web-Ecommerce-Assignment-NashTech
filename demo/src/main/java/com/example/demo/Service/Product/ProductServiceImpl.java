package com.example.demo.Service.Product;

import com.example.demo.Dto.ProductDto;
import com.example.demo.Entity.TblProductEntity;
import com.example.demo.Repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private IProductRepository repository;
    private ModelMapper modelMapper;
    @Override
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok().body(repository.findAll().stream().map(product ->modelMapper.map(product,ProductDto.class)).collect(Collectors.toList()));
    }

    @Override
    public  ResponseEntity<TblProductEntity>  save(TblProductEntity entity) {
        entity.setIsActive(true);
        return ResponseEntity.ok().body(repository.save(entity));
    }
    @Override
    public ResponseEntity<List<ProductDto>> findByName(String s){
        return ResponseEntity.ok().body(repository.findAllByNameContains(s).stream().map(product ->modelMapper.map(product,ProductDto.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<ProductDto>> findProductsByCategory(int id)
    {
        return ResponseEntity.ok().body(repository.findTblProductEntitiesByCategoryId(id).stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList()));
    }
    @Override
    public ResponseEntity<?> updateProduct(TblProductEntity entity){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            entity.setIsActive(true);
            TblProductEntity product= repository.save(entity);
            map.put("status", 1);
            map.put("message", "update successfully!");
            ProductDto dto = modelMapper.map(product,ProductDto.class);
            map.put("data",dto);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public TblProductEntity getById(int id){
        return repository.findById(id).get();
    }


}
