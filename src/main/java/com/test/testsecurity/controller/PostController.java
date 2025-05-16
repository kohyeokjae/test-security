package com.test.testsecurity.controller;

import com.test.testsecurity.dto.request.PostRequestDto;
import com.test.testsecurity.dto.response.PostResponseDto;
import com.test.testsecurity.dto.response.ResponseDto;
import com.test.testsecurity.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<ResponseDto<PostResponseDto>> createPost(@AuthenticationPrincipal String username, @RequestBody PostRequestDto dto) {
        ResponseDto<PostResponseDto> responseDto = postService.createPost(username, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<PostResponseDto>>> getAllPosts() {
        ResponseDto<List<PostResponseDto>> posts = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PostResponseDto>> getPostById(@PathVariable Long id) {
        ResponseDto<PostResponseDto> post = postService.getPostById(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PostResponseDto>> updatePost(
        @PathVariable Long id,
        @AuthenticationPrincipal String username,
        @Valid @RequestBody PostRequestDto dto
    ) {
        ResponseDto<PostResponseDto> response = postService.updatePost(id, username, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
