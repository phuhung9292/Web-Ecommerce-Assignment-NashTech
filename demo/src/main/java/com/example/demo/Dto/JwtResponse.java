package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private int id;
    private String token;
    private String type = "Bearer";
    private String email;
    private Collection<? extends GrantedAuthority>role;

    public JwtResponse(int id,String token, String email, Collection<? extends GrantedAuthority> role) {
        this.id=id;
        this.token=token;
        this.email=email;
        this.role=role;
    }
}
