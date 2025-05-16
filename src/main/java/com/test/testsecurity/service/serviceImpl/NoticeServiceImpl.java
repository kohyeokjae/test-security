package com.test.testsecurity.service.serviceImpl;

import com.test.testsecurity.dto.request.NoticeRequestDto;
import com.test.testsecurity.dto.response.NoticeResponseDto;
import com.test.testsecurity.dto.response.PostResponseDto;
import com.test.testsecurity.dto.response.ResponseDto;
import com.test.testsecurity.entity.Notice;
import com.test.testsecurity.entity.Post;
import com.test.testsecurity.entity.User;
import com.test.testsecurity.repository.NoticeRepository;
import com.test.testsecurity.repository.UserRepository;
import com.test.testsecurity.service.NoticeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<NoticeResponseDto> createNotice(String username, NoticeRequestDto dto) {
        NoticeResponseDto responseDto = null;

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("사용자 정보가 없습니다."));

        Notice newNotice = Notice.builder()
            .title(dto.getTitle())
            .content(dto.getContent())
            .user(user)
            .build();

        Notice saved = noticeRepository.save(newNotice);

        responseDto = NoticeResponseDto.builder()
            .title(saved.getTitle())
            .content(saved.getContent())
            .username(saved.getUser().getUsername())
            .build();

        return ResponseDto.setSuccess("공지가 등록되었습니다.", responseDto);
    }
}
