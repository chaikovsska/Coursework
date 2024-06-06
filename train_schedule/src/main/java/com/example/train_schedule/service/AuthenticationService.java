package com.example.train_schedule.service;

import com.example.train_schedule.entities.User;
import com.example.train_schedule.repository.AdminRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Calendar;
import java.util.Date;

@ApplicationScoped
public class AuthenticationService {
    private final String secretKey = "hF3rKjy84Mqd75fDHnr6m4f0f9iPu7Hwq4mNzB2YrKJh";
    private final String bearerConst = "Bearer";
    @Inject
    private AdminRepository adminRepository;
    public String generateAuthToken(User user) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 15);
        String token = Jwts.builder()
                .setSubject(user.getUserName())
                .setExpiration(cal.getTime())
                .claim("roles", "ADMIN")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return token;
    }
    public boolean verifyAuthToken(String authToken) {
        if (authToken == null || !authToken.startsWith(bearerConst)) {
            return false;
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(authToken.substring(bearerConst.length()))
                    .getBody();

            String userName = claims.getSubject();
            String userRole = (String) claims.get("roles");
            Date expDate = claims.getExpiration();
            if (expDate.before(new Date())) {
                return false;
            }

            User user = adminRepository.findUserByUsername(userName);
            return user != null && userRole.equals("ADMIN");
        } catch (Exception e) {
            return false;
        }
    }
}
