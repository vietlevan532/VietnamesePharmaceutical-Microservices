package com.zezanziet.pharmaceutical.vn.ms.user_service.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String birthOfDate;
    private String position;
    private String gender;
    private String email;
    private String phoneNumber;
    private String password;
    private String certificateNumber;
}
