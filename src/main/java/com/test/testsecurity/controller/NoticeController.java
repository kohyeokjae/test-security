package com.test.testsecurity.controller;

import com.test.testsecurity.dto.request.NoticeRequestDto;
import com.test.testsecurity.dto.response.NoticeResponseDto;
import com.test.testsecurity.dto.response.ResponseDto;
import com.test.testsecurity.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseDto<NoticeResponseDto>> createNotice(
        @AuthenticationPrincipal String username,
        @RequestBody NoticeRequestDto dto) {
        ResponseDto<NoticeResponseDto> responseDto = noticeService.createNotice(username, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
