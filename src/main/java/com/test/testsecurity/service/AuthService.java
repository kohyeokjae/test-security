package com.test.testsecurity.service;

import com.test.testsecurity.dto.request.UserSignInRequestDto;
import com.test.testsecurity.dto.request.UserSignUpRequesetDto;
import com.test.testsecurity.dto.response.ResponseDto;
import com.test.testsecurity.dto.response.UserSignInResponseDto;
import com.test.testsecurity.dto.response.UserSignUpResponseDto;
import jakarta.validation.Valid;

public interface AuthService {
    ResponseDto<UserSignUpResponseDto> signup(@Valid UserSignUpRequesetDto dto);

    ResponseDto<UserSignInResponseDto> login(@Valid UserSignInRequestDto dto);
}
