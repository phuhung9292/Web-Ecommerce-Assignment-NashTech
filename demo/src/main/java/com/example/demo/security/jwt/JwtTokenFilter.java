package com.example.demo.security.jwt;

import com.example.demo.security.userPrincal.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
@Autowired
private JwtProvider jwtProvider;
@Autowired
private UserDetailService userDetailService;
@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getJwt(request);
            if(token != null && jwtProvider.validateToken(token)){
                String userName = jwtProvider.getUserNameFromToken(token);
                UserDetails userDetails = userDetailService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (Exception e){
            logger.error("Can't set user authentication -> {}",e);
        }
        filterChain.doFilter(request,response);
    }
    private String getJwt(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader !=null && authHeader.startsWith("Bearer")){
            return authHeader.replace("Bearer ","");
        }
        return null;
    }
}
