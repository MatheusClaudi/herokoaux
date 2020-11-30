package com.ufcg.psoft.service;

import org.springframework.stereotype.Service;

import com.ufcg.psoft.config.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Service
public class JwtTokenServiceImpl {
	
	public String getUserEmailFromJWTToken(String token) throws Exception {
	
        byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

        Jws<Claims> parsedToken = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token.replace("Bearer ", ""));

        String username = parsedToken
                .getBody()
                .getSubject();

        return username;
	}

}
