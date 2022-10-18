package com.example.demo.Service.Category;

import com.example.demo.Entity.TblCategoryEntity;
import com.example.demo.Repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor @Transactional
public class CateServiceImpl implements CategoryService{
    private ICategoryRepository categoryRepository;

    @Override
    public List<TblCategoryEntity> findAll() {
        return categoryRepository.findAllByIsActive(true);
    }

    @Override
    public <S extends TblCategoryEntity> S save(S entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public TblCategoryEntity findById(int id) {
        return categoryRepository.findByIsActiveAndId(true,id);
    }



}
