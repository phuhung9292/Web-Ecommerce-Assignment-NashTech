package com.example.demo.Controller;

import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.TblUserEntity;
import com.example.demo.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private ModelMapper modelMapper;
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>>  getAllUser() throws Exception {
            return ResponseEntity.ok().body(userService.findAll().stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList()));
    }
    @PostMapping("/create")
    public ResponseEntity<TblUserEntity> createUser(@RequestBody TblUserEntity user){
        user.setIsActive(true);
        user.setRoleId(2);
        return ResponseEntity.ok().body(userService.save(user));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            TblUserEntity user= userService.findById(id).get();
            user.setIsActive(false);
            userService.save(user);
            map.put("status", 1);
            map.put("message", "Record is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id){
        TblUserEntity userEntity = userService.findById(id).get();
        UserDto userResponse = modelMapper.map(userEntity,UserDto.class);
        return ResponseEntity.ok().body(userResponse);
    }
    @GetMapping("/role/{id}")
    public ResponseEntity<List<UserDto>> getUserByRole(@PathVariable int id){
        List<TblUserEntity> listUser = userService.findAllByRoleId(true,id);
        return ResponseEntity.ok().body(listUser.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList()));
    }



}
