package com.example.demo.Repository;

import com.example.demo.Entity.TblUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<TblUserEntity,Integer> {
    List<TblUserEntity> findAllByIsActive(boolean check);
    List<TblUserEntity> findAllByIsActiveAndRoleId(boolean active,int id);
}
