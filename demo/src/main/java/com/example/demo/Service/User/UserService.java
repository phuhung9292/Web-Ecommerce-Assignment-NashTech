package com.example.demo.Service.User;

import com.example.demo.Dto.SignInForm;
import com.example.demo.Entity.TblUserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<TblUserEntity> findAll();

    Optional<TblUserEntity> findById(Integer integer);

    <S extends TblUserEntity> ResponseEntity<?> save(S entity);
    List<TblUserEntity> findAllByRoleId(boolean check,int id);

    ResponseEntity<?> login(SignInForm signInForm);

    ResponseEntity<?> updateUser(int id);
}
