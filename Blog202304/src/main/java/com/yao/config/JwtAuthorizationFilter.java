package com.yao.config;
/*
 * @author Jack
 * @date 2023/3/31
 * */

import com.yao.util.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.web.filter.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
@Configuration
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    String HEADER_STRING = "Authorization";
    String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(HEADER_STRING);
//        if ("/login".equals(request.getRequestURI()) || "/public/test".equals(request.getRequestURI())) {
//            //donothing
//        }else {
//            if (header == null || !header.startsWith(TOKEN_PREFIX)) {
//                throw new RuntimeException("Token is invalid.");
//            }
//            String token = header.replace(TOKEN_PREFIX, "");
//            if (!JwtUtils.validateToken(token)){
//                throw new RuntimeException("Token is invalid.");
//            }
//        }
        chain.doFilter(request, response);
    }
}
