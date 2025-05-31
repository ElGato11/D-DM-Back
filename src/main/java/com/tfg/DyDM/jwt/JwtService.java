package com.tfg.DyDM.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public class JwtService {
    public String getToken(UserDetails user) {
        return "1234";
    }
}
