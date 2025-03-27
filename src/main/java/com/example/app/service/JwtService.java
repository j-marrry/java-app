package com.example.app.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "f77eafbe6d7f690168f63fd7b51174b38abd0c3aa18a5dc28c84dd5592e3b26d763e1937fc33f9b79daee1d3ef4e84c998759b2ef761df874f676d6b8260804b64b2f25545d40f5e22556ce51e6416ecfb3d57762fd412e0f917973b4db1a53b327858a3d977f1d278e698ac239b8d24880087c09cc08e4f562716469f44802a5efe168a5a73fb202c3496e1626f461c63ca722ea2b6c9eb10c4dfffdbc72cd581c7e426519a7b9c8000dfd6e87db81b87cb897b8e86a6b481c43e922dba3562402afed3b538ed1209b0e5960d89ab73944f4603ab4c0d5e4dccd03360f91ba9985881aa034bb2276b5894eefb59885d755918d8c6c3fb469f26cbcf4871b2af";

    private Key getKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token){
        return Jwts.parser().setSigningKey(getKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        Date expiration = Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return username.equals(userDetails.getUsername()) && expiration.after(new Date());
    }
}
