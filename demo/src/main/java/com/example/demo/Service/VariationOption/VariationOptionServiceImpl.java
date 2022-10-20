package com.example.demo.Service.VariationOption;

import com.example.demo.Entity.TblVariationOptionEntity;
import com.example.demo.Repository.IVariation_OptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
