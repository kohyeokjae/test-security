package com.test.testsecurity.dto.response;

import com.test.testsecurity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {
    private String title;
    private String content;
    private String username;
}
