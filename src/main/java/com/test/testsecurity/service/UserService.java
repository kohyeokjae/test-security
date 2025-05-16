package com.test.testsecurity.service;

import com.test.testsecurity.dto.response.GetUserResponseDto;
import com.test.testsecurity.dto.response.ResponseDto;

public interface UserService {
    ResponseDto<GetUserResponseDto> getUserInfo(String username);
}
