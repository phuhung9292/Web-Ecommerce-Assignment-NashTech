package com.example.demo.Service.Category;

import com.example.demo.Entity.TblCategoryEntity;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
//    <S extends TblCategoryEntity> List<S> findAll(Example<S> example);

    List<TblCategoryEntity> findAll();

    <S extends TblCategoryEntity> S save(S entity);

    TblCategoryEntity findById(int id);

}
