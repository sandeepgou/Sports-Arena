package com.stackroute.security;

import com.stackroute.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import java.util.Map;
import java.util.function.Function;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);
   boolean validateToken(String authToken);
     String extractUserEmail(String token);
     <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

     Claims extractAllClaims(String token);

    Authentication getAuthentication(String token);
}
