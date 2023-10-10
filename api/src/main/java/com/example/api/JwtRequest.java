package com.example.api;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
