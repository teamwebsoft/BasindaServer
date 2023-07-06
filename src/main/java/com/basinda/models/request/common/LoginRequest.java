package com.basinda.models.request.common;

import lombok.Data;

@Data
public class LoginRequest {
    private String mobileNumber;
    private String password;
}