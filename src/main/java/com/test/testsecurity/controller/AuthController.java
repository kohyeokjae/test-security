package com.test.testsecurity.controller;

import com.test.testsecurity.dto.request.UserSignInRequestDto;
import com.test.testsecurity.dto.request.UserSignUpRequesetDto;
import com.test.testsecurity.dto.response.ResponseDto;
import com.test.testsecurity.dto.response.UserSignInResponseDto;
import com.test.testsecurity.dto.response.UserSignUpResponseDto;
import com.test.testsecurity.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    private static final String USER_SIGN_UP = "/signup";
    private static final String USER_SIGN_IN = "/login";

    // 회원 가입
    @PostMapping(USER_SIGN_UP)
    public ResponseEntity<ResponseDto<UserSignUpResponseDto>> signup(
        @Valid @RequestBody UserSignUpRequesetDto dto) {
        ResponseDto<UserSignUpResponseDto> responseDto = authService.signup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 로그인
    @PostMapping(USER_SIGN_IN)
    ResponseEntity<ResponseDto<UserSignInResponseDto>> login(
        @Valid @RequestBody UserSignInRequestDto dto
    ) {
        ResponseDto<UserSignInResponseDto> responseDto = authService.login(dto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
