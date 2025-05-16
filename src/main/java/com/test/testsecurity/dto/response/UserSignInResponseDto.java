package com.test.testsecurity.dto.response;

import com.test.testsecurity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignInResponseDto {
    private String token; // JWT 토큰
    private String username;
    private int exprTime; // expire + time: (토큰) 만료 시간
}
