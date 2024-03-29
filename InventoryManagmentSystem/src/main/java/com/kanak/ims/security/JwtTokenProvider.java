package com.kanak.ims.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger LOGGER= LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    //generate Jwt Token
    public String generateToken(Authentication authentication){
        String username=authentication.getName();
        String role=authentication.getAuthorities().stream().findFirst().get().getAuthority().toUpperCase();
        Date currentDate=new Date();
        Date expireDate=new Date(currentDate.getTime()+jwtExpirationDate);

        String token= Jwts.builder()
                .setSubject(String.format("%s,%s",username,role))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256,key())
                .compact();
        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //get username from Jwt token
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username=claims.getSubject();
        return username;
    }

    //validating Jwt token
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        }
        catch (MalformedJwtException e){
            LOGGER.error("Invalid JWT Token : {}",e.getMessage());
        }catch (ExpiredJwtException e){
            LOGGER.error("JWT token is expired: {}",e.getMessage());
        }catch(UnsupportedJwtException e){
            LOGGER.error("JWT token is unsupported: {}",e.getMessage());
        }
        catch (IllegalArgumentException e){
            LOGGER.error("JWT claims string is empty: {}",e.getMessage());
        }
        return false;
    }
}
