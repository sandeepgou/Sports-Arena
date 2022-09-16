package com.stackroute.security;

import com.stackroute.entity.User;
import com.stackroute.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    private String SECRET_KEY="secret";

    @Autowired
private CustomUserDetailsService customUserDetailsService;

    @Override
    public Map<String, String> generateToken(User user) {
        System.out.println("inside generateToken");
        String jwtToken = Jwts.builder().setSubject(user.getUserEmail()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        Map<String,String> map = new HashMap<>();

        map.put("token",jwtToken);
        map.put("message","Authenticated User");
        return map;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    @Override
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(extractUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String authToken) {
        Jwts.parser().setSigningKey(this.SECRET_KEY).parseClaimsJws(authToken).getBody();
        return true;
    }
    @Override
    public String extractUserEmail(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }


}
