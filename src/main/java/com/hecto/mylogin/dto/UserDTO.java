package com.hecto.mylogin.dto;

import lombok.Data;

// DB와 통신 즉 받아온 정보를 DB에 저장.
@Data
public class UserDTO {
    private String userName;
    private String userEmail;
    private String userRole;
}
