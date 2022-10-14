package com.example.demo.Repository;

import com.example.demo.Entity.TblUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<TblUserEntity,Integer> {
}
