package com.example.demo.security.jwt;

import com.example.demo.security.userPrincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "pham.hung@123";
    private int jwtExpiration = 86400;
    public String createToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            logger.error("Invalid JWT signature -> Message: {}",e);
        }catch (MalformedJwtException e){
            logger.error("Invalid format signature -> Message: {}",e);

        }catch (ExpiredJwtException e){
            logger.error("Expired JWT signature -> Message: {}",e);

        }catch (UnsupportedJwtException e){
            logger.error("Unsupport JWT signature -> Message: {}",e);
        }catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty -> Message: {}",e);

        }
        return false;
    }
    public String getUserNameFromToken(String token){
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }
}
