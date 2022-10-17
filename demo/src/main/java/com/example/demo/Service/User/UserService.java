package com.example.demo.Service.User;

import com.example.demo.Entity.TblUserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<TblUserEntity> findAll();

    Optional<TblUserEntity> findById(Integer integer);

    <S extends TblUserEntity> S save(S entity);
//    void deleteById(Integer id);

}
