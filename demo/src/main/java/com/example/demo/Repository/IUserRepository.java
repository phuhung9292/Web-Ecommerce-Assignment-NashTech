package com.example.demo.Repository;

import com.example.demo.Entity.TblUserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<TblUserEntity,Integer> {
    List<TblUserEntity> findAllByIsActive(boolean check);
    List<TblUserEntity> findAllByIsActiveAndRoleId(boolean active,int id);

     Optional<TblUserEntity> findTblUserEntitiesByEmail(String email);


    Optional<TblUserEntity> findTblUserEntitiesByPhone(String phone);
}
