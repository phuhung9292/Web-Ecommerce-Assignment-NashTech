package com.example.demo.Service.User;

import com.example.demo.Dto.JwtResponse;
import com.example.demo.Dto.SignInForm;
import com.example.demo.Entity.TblRoleEntity;
import com.example.demo.Entity.TblShoppingCartEntity;
import com.example.demo.Entity.TblUserEntity;
import com.example.demo.Repository.IRoleRepository;
import com.example.demo.Repository.IShoppingCartRepository;
import com.example.demo.Repository.IUserRepository;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.userPrincal.UserPrinciple;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service @AllArgsConstructor @Slf4j @Transactional
public class UserServiceImpl implements UserService {
    private IUserRepository userRepository;
    private IRoleRepository roleRepository;
    private ModelMapper modelMapper;
    private IShoppingCartRepository shoppingCartRepository;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    JwtProvider jwtProvider;
    @Override
    public List<TblUserEntity> findAll() {

        return userRepository.findAllByIsActive(true);
    }

    @Override
    public Optional<TblUserEntity> findById(Integer integer) {
        return userRepository.findById(integer);
    }

    @Override
    public ResponseEntity<?> save(TblUserEntity entity) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        boolean checkEmail= checkEmail(entity.getEmail());
        boolean checkPhone = checkPhone(entity.getPhone());
        try {
            if(checkEmail==true && checkPhone == true) {
                entity.setIsActive(true);
                entity.setRoleId(2);
                entity.setPassword(passwordEncoder.encode(entity.getPassword()));
                TblUserEntity user = userRepository.save(entity);
                TblShoppingCartEntity cartEntity = new TblShoppingCartEntity();
                cartEntity.setUserid(user.getId());
                shoppingCartRepository.save(cartEntity);
                map.put("status", 1);
                map.put("message", "create successfully!");
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
        } catch (Exception ex) {
            map.clear();

            map.put("status", 0);
            map.put("message", "Create Fail");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
            if(checkEmail==false){
            map.put("error","Email is already exist");
            } else if (checkPhone==false) {
            map.put("error","Phone is already exist");
            }
            map.put("message", "Create Fail");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @Override
    public List<TblUserEntity> findAllByRoleId(boolean check, int id) {
        return userRepository.findAllByIsActiveAndRoleId(check,id);
    }
    public boolean checkEmail(String email){
        if(userRepository.findTblUserEntitiesByEmail(email).isPresent()){
            return false;
        }
        return true;
    }
    public boolean checkPhone(String Phone){
        if(userRepository.findTblUserEntitiesByPhone(Phone).isPresent()){
            return false;
        }
        return true;
    }

    @Override
    public ResponseEntity<?> login(SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getEmail(),signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
//        userPrinciple.setRole(roleRepository.findById(authentication.));
//        int roleid = userRepository.findById(userPrinciple.getId()).get().getRoleId();
//        String roleName = roleRepository.findById(roleid).get().getTypeRole();
        return ResponseEntity.ok().body(new JwtResponse(userPrinciple.getId(),token,userPrinciple.getEmail(),userPrinciple.getAuthorities()));
    }

}
