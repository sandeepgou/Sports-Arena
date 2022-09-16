package com.stackroute.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {



    String userEmail=null;

   /* public JwtAuthenticationFilter(SecurityTokenGenerator securityTokenGenerator) {

    }*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //to implement accessing other service URLs
    /* String tokenHeader = request.getHeader("Authorization" );
        System.out.println("Token Header "+ tokenHeader);
        if(tokenHeader !=null) {
            String token = tokenHeader.substring(7);
            System.out.println(token);
            try {
                userEmail = securityTokenGenerator.extractUserEmail(token);
                System.out.println("User Email "  + userEmail);

                Authentication auth = securityTokenGenerator.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception ex) {
                System.out.println("Cannot set user authentication: {}");
                ex.printStackTrace();
            }
        }else{
            System.out.println("Token invalid");
        }
        filterChain.doFilter(request,response);*/
    }
}
