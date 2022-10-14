package com.example.demo.Repository;

import com.example.demo.Entity.TblRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<TblRoleEntity,Integer> {

}
