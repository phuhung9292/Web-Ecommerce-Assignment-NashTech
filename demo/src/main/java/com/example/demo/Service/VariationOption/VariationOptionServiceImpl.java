package com.example.demo.Service.VariationOption;

import com.example.demo.Dto.colorDto;
import com.example.demo.Dto.sizeDto;
import com.example.demo.Entity.TblVariationOptionEntity;
import com.example.demo.Repository.IVariation_OptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service @AllArgsConstructor
public class VariationOptionServiceImpl implements VariationOptionService{
    private IVariation_OptionRepository variation_optionRepository;

    @Override
    public  TblVariationOptionEntity  save(TblVariationOptionEntity entity) {
        return variation_optionRepository.save(entity);
    }

    @Override
    public List<TblVariationOptionEntity> findAll() {
        return variation_optionRepository.findAll();
    }

    @Override
    public TblVariationOptionEntity findById(int id) {
        return variation_optionRepository.findById(id).get();
    }

    @Override
    public List<TblVariationOptionEntity> findAllByVariationId(int id){return variation_optionRepository.findAllByVariationId(id);}

    @Override
    public ResponseEntity<?> findSizeAndColorByProductId(int id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        List<sizeDto> listSize = new ArrayList<>();
        List<colorDto> listColor = new ArrayList<>();
        try{
            List<TblVariationOptionEntity> optionSize = variation_optionRepository.findAllSizeByCateId(id);
            List<TblVariationOptionEntity> optionColor = variation_optionRepository.findAllColorByCateId(id);
            map.put("Size",optionSize);
            map.put("Color",optionColor);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception ex){
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

}
