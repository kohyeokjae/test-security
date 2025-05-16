package com.test.testsecurity.service;

import com.test.testsecurity.dto.request.PostRequestDto;
import com.test.testsecurity.dto.response.PostResponseDto;
import com.test.testsecurity.dto.response.ResponseDto;
import com.test.testsecurity.entity.User;
import jakarta.validation.Valid;

import java.util.List;

public interface PostService {
    ResponseDto<PostResponseDto> createPost(String username, PostRequestDto dto);

    ResponseDto<List<PostResponseDto>> getAllPosts();

    ResponseDto<PostResponseDto> getPostById(Long id);

    ResponseDto<PostResponseDto> updatePost(Long id, String username, @Valid PostRequestDto dto);
}
