package com.test.testsecurity.service.serviceImpl;

import com.test.testsecurity.dto.response.GetUserResponseDto;
import com.test.testsecurity.dto.response.ResponseDto;
import com.test.testsecurity.entity.User;
import com.test.testsecurity.repository.UserRepository;
import com.test.testsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseDto<GetUserResponseDto> getUserInfo(String username) {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return ResponseDto.setFailed("사용자 정보가 존재하지 않습니다.");
        }

        GetUserResponseDto data = new GetUserResponseDto(user);

        return ResponseDto.setSuccess("사용자 정보 조회에 성공하였습니다.", data);
    }
}
