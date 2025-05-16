package com.test.testsecurity.service.serviceImpl;

import com.test.testsecurity.dto.request.PostRequestDto;
import com.test.testsecurity.dto.response.PostResponseDto;
import com.test.testsecurity.dto.response.ResponseDto;
import com.test.testsecurity.entity.Post;
import com.test.testsecurity.entity.User;
import com.test.testsecurity.repository.PostRepository;
import com.test.testsecurity.repository.UserRepository;
import com.test.testsecurity.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<PostResponseDto> createPost(String username, PostRequestDto dto) {
        PostResponseDto responseDto = null;

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("사용자 정보가 없습니다."));

        Post newPost = Post.builder()
            .title(dto.getTitle())
            .content(dto.getContent())
            .user(user)
            .build();

        Post saved = postRepository.save(newPost);

        responseDto = PostResponseDto.builder()
            .title(saved.getTitle())
            .content(saved.getContent())
            .username(saved.getUser().getUsername())
            .build();

        return ResponseDto.setSuccess("게시글이 등록되었습니다.", responseDto);

    }

    @Override
    public ResponseDto<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> responseDtos = null;

        List<Post> posts = postRepository.findAll();

        responseDtos = posts.stream()
            .map(post -> PostResponseDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .username(post.getUser().getUsername())
                .build())
            .collect(Collectors.toList());

        return ResponseDto.setSuccess("게시글 조회 성공하였습니다.", responseDtos);
    }

    @Override
    public ResponseDto<PostResponseDto> getPostById(Long id) {
        PostResponseDto responseDto = null;

        Post post = postRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("게시글 아이디를 찾을 수 없습니다." + id));

        responseDto = PostResponseDto.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .username(post.getUser().getUsername())
            .build();

        return ResponseDto.setSuccess("게시글 조회 성공하였습니다.", responseDto);
    }

    @Override
    public ResponseDto<PostResponseDto> updatePost(Long id, String username, PostRequestDto dto) {
        PostResponseDto responseDto = null;

        Post post = postRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("게시글 아이디를 찾을 수 없습니다." + id));

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("사용자 정보가 없습니다."));

        if (!(user.getUsername() == post.getUser().getUsername())) {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        Post updatedPost = postRepository.save(post);

        responseDto = PostResponseDto.builder()
            .title(updatedPost.getTitle())
            .content(updatedPost.getContent())
            .username(updatedPost.getUser().getUsername())
            .build();

        return ResponseDto.setSuccess("게시글 수정 성공하였습니다.", responseDto);
    }
}
