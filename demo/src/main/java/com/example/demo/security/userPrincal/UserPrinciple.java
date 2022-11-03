package com.example.demo.security.userPrincal;

import com.example.demo.Entity.TblRoleEntity;
import com.example.demo.Entity.TblUserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter

public class UserPrinciple implements UserDetails {
    private int id;
    private String email;
    private String fullName;
    private String address;
    private String phone;
    @JsonIgnore
    private String password;
    private boolean isActive;
    private Collection<? extends GrantedAuthority> role;

    public UserPrinciple() {
    }

    public UserPrinciple(int id, String email, String fullName, String address, String phone, String password, boolean isActive,  Collection<? extends GrantedAuthority> role) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
    }

    public static UserPrinciple build(TblUserEntity user){
//        TblRoleEntity role1 =
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getTblRoleByRoleId().getTypeRole());
        List<GrantedAuthority> authories = new ArrayList<>();
        authories.add(authority);

        return new UserPrinciple(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getAddress(),
                user.getPhone(),
                user.getPassword(),
                user.getIsActive(),
                authories
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
