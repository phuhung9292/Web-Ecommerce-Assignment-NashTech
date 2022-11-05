package com.example.demo.Service.Variation;

import com.example.demo.Entity.TblVariationEntity;
import com.example.demo.Repository.IVariationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class VariationServiceImpl implements VariationService {
    private IVariationRepository variationRepository;

    @Override
    public <S extends TblVariationEntity> S save(S entity) {
        return variationRepository.save(entity);
    }

    @Override
    public Optional<TblVariationEntity> findById(Integer integer) {
        return variationRepository.findById(integer);
    }

    @Override
    public List<TblVariationEntity> findAll() {
        return variationRepository.findAll();
    }

    @Override
    public List<TblVariationEntity> findByCateId(int id) {return  variationRepository.findAllByCategoryId(id);}
}
