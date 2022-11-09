package com.example.demo.Service.ProductItem;

import com.example.demo.Dto.*;
import com.example.demo.Entity.ManyToManyId.TblProductConfigurationId;
import com.example.demo.Entity.TblProductConfigurationEntity;
import com.example.demo.Entity.TblProductEntity;
import com.example.demo.Entity.TblProductItemEntity;
import com.example.demo.Entity.TblVariationOptionEntity;
import com.example.demo.Repository.IProductConfigRepository;
import com.example.demo.Repository.IProductItemRepository;
import com.example.demo.Repository.IProductRepository;
import com.example.demo.Repository.IVariation_OptionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductServiceItemImpl implements ProductItemService{

    private IProductItemRepository repository;
    private IProductRepository productRepository;
    private IVariation_OptionRepository r2;

    private IProductConfigRepository productConfigRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TblProductItemEntity> findAll() {
//        List<TblProductItemEntity> list = repository.findAll();
//        ProductItemDto dto = new ProductItemDto();


        return repository.findAll();
    }

    @Override
    public ResponseEntity<?> save(TblProductItemEntity entity, int idVariation1, int idVariation2, int productId) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if(repository.findProducItemByProductIdAndSizeAndColor(productId,idVariation1,idVariation2)==null) {
           Date date = Calendar.getInstance().getTime();
           entity.setIsActice(true);
           entity.setCreateDate(date);
           entity.setProductId(productId);
           TblProductItemEntity entity1 = repository.save(entity);
           TblVariationOptionEntity variation1 = r2.findById(idVariation1).get();
           TblVariationOptionEntity variation2 = r2.findById(idVariation2).get();
           TblProductConfigurationEntity e3 = new TblProductConfigurationEntity();
           TblProductConfigurationEntity e4 = new TblProductConfigurationEntity();

           e3.setTblProductItemByProductItemId(entity1);
           e3.setTblVariationOptionByVariationOptionId(variation1);
           e4.setTblProductItemByProductItemId(entity1);
           e4.setTblVariationOptionByVariationOptionId(variation2);

           Collection<TblProductConfigurationEntity> config1 = new ArrayList<>();
           config1.add(e3);
           config1.add(e4);
           e3.setTblProductItemByProductItemId(entity1);
           e4.setTblProductItemByProductItemId(entity1);
           int i = idVariation1;
           for (TblProductConfigurationEntity c : config1) {

               TblProductConfigurationId id = new TblProductConfigurationId(entity1.getId(), i);
               i = idVariation2;
               c.setId(id);
               System.out.println(c.getId());
           }

           entity1.setTblProductConfigurationsById(config1);
           productConfigRepository.save(e3);
           productConfigRepository.save(e4);
//        entity1.setId(1);
            map.put("status", 1);
            map.put("message", "update successfully!");
           return new ResponseEntity<>(map, HttpStatus.OK);
       }else {
            map.clear();
            map.put("status", 0);
            map.put("message", "this product already Exist");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
       }
    }
    @Override
    public ResponseEntity<?> updateProductItem(TblProductItemEntity entity){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Date date = Calendar.getInstance().getTime();
        Optional<TblProductItemEntity> check = repository.findById(entity.getId());
        if(!check.isPresent()){
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }else
        {
            TblProductItemEntity product= repository.findById(entity.getId()).get();
            entity.setCreateDate(product.getCreateDate());
            entity.setUpdateDate(date);
            entity.setProductId(product.getProductId());
            entity.setIsActice(true);
            map.put("status", 1);
            map.put("message", "update successfully!");
//            ProductItemDto dto = new ;
            map.put("data",repository.save(entity));
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @Override
    public TblProductItemEntity findById(Integer integer) {
        return repository.findById(integer).get();
    }


    @Override
    public ResponseEntity<?> findByProductId(Integer integer) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<TblProductItemEntity> product= repository.findAllByProductId(integer);
        if(product.isEmpty()){
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }else
         {

            map.put("status", 1);
            map.put("message", " successfully!");
            List<sizeDto> listSize = new ArrayList<>();
            List<colorDto> listColor = new ArrayList<>();
            List<imageDto> listImage = new ArrayList<>();

             TblProductEntity productEntity= productRepository.findById(integer).get();
            ProductItemDto productDto = modelMapper.map(productEntity,ProductItemDto.class) ;
//            productDto.setProductImage(pr);
            for (TblProductItemEntity en: product) {
                Collection<TblProductConfigurationEntity> listconfig = en.getTblProductConfigurationsById();
                for (TblProductConfigurationEntity productconfig: listconfig) {
                    if(productconfig.getTblVariationOptionByVariationOptionId()
                            .getTblVariationByVariationId().getName().equals("Size")){
                        sizeDto size = new sizeDto(productconfig.getTblVariationOptionByVariationOptionId().getId()
                                ,productconfig.getTblVariationOptionByVariationOptionId().getValue());
                        if(!listSize.contains(size)) {
                            listSize.add(size);
                        }
                    }
                    else {
                        colorDto color = new colorDto(productconfig.getTblVariationOptionByVariationOptionId().getId()
                                ,productconfig.getTblVariationOptionByVariationOptionId().getValue());
                        if(!listColor.contains(color)){
                            listColor.add(color);
                        }
                    }

                }
                imageDto image = new imageDto(en.getId(),en.getProductImage());
                listImage.add(image);

            }
            productDto.setSize(listSize);
            productDto.setColor(listColor);
            productDto.setProductImage(listImage);
//            ProductDto dto = modelMapper.map(product,ProductDto.class);

            map.put("data",productDto);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @Override
    public ProductItemDetail findByProductIdAndSizeIdAndColor(Integer productId, Integer varation1, Integer variation2) {
        return modelMapper.map(repository.findProducItemByProductIdAndSizeAndColor(productId,varation1,variation2),ProductItemDetail.class);
    }

    @Override
    public List<TblProductItemEntity> adminGetProductItemById(int id){
        return repository.findAllByProductId(id);
    }



}
