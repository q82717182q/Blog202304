package com.yao.util;
/*
 * @author Jack
 * @date 2023/3/31
 * */

import io.jsonwebtoken.*;

import java.util.*;

public class JwtUtils {

    private static final String SECRET_KEY = "JackGotAVerySecretKeyul6fupck6ecretKeyul6fupck6ecretKeyul6fupck6ecretKeyul6fupck6ecretKeyul6fupck6ecretKeyul6fupck6ecretKeyul6fupck6ecretKeyul6fupck6ecretKeyul6fupck6ecretKeyul6fupck6";

    private static final long EXPIRATION_TIME = 1 * 60 * 60 * 1000; // 1 hours


    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
