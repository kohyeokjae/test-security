package com.test.testsecurity.controller;

import com.test.testsecurity.dto.response.GetUserResponseDto;
import com.test.testsecurity.dto.response.ResponseDto;
import com.test.testsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<ResponseDto<GetUserResponseDto>> getUserInfo(
        @AuthenticationPrincipal String username
    ) {
        ResponseDto<GetUserResponseDto> responseDto = userService.getUserInfo(username);
        return ResponseEntity.ok(responseDto);
    }
}
