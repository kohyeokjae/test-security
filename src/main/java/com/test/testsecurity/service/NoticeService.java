package com.test.testsecurity.service;

import com.test.testsecurity.dto.request.NoticeRequestDto;
import com.test.testsecurity.dto.response.NoticeResponseDto;
import com.test.testsecurity.dto.response.ResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface NoticeService {
    ResponseDto<NoticeResponseDto> createNotice(String username, NoticeRequestDto dto);
}
