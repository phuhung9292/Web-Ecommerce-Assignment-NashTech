package com.example.demo.Service.User;

import com.example.demo.Entity.TblUserEntity;
import com.example.demo.Repository.IUserRepository;
import com.example.demo.Service.User.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor @Slf4j @Transactional
public class UserServiceImpl implements UserService {
    private IUserRepository userRepository;

    @Override
    public List<TblUserEntity> findAll() {

        return userRepository.findAllByIsActive(true);
    }

    @Override
    public Optional<TblUserEntity> findById(Integer integer) {
        return userRepository.findById(integer);
    }

    @Override
    public <S extends TblUserEntity> S save(S entity) {
//        entity.setRoleId(1);
        return userRepository.save(entity);
    }

    @Override
    public List<TblUserEntity> findAllByRoleId(boolean check, int id) {
        return userRepository.findAllByIsActiveAndRoleId(check,id);
    }

}
