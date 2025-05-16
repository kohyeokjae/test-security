package com.test.testsecurity.service.serviceImpl;

import com.test.testsecurity.dto.request.UserSignInRequestDto;
import com.test.testsecurity.dto.request.UserSignUpRequesetDto;
import com.test.testsecurity.dto.response.ResponseDto;
import com.test.testsecurity.dto.response.UserSignInResponseDto;
import com.test.testsecurity.dto.response.UserSignUpResponseDto;
import com.test.testsecurity.entity.User;
import com.test.testsecurity.provider.JwtProvider;
import com.test.testsecurity.repository.UserRepository;
import com.test.testsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.channels.Pipe;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseDto<UserSignUpResponseDto> signup(UserSignUpRequesetDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();
        String role = dto.getRole();

        UserSignUpResponseDto data = null;
        User user = null;

        if (!password.equals(confirmPassword)) {
            return ResponseDto.setFailed("비밀번호가 일치하지 않습니다.");
        }

        if (userRepository.existsByUsername(username)) {
            return ResponseDto.setFailed("사용자이름이 이미 존재합니다.");
        }

        String encodePassword = bCryptPasswordEncoder.encode(password);


        user = User.builder()
            .username(username)
            .password(encodePassword)
            .role(role)
            .build();

        userRepository.save(user);

        data = new UserSignUpResponseDto(user);

        return ResponseDto.setSuccess("회원가입에 성공하였습니다.", data);
    }

    @Override
    public ResponseDto<UserSignInResponseDto> login(UserSignInRequestDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        UserSignInResponseDto data = null;
        User user = null;

        int exprTime = jwtProvider.getExpiration();

        user = userRepository.findByUsername(username)
            .orElseThrow(null);

        if (user == null) {
            return ResponseDto.setFailed("사용자 이름이 없습니다.");
        }

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return ResponseDto.setFailed("비밀번호가 일치하지 않습니다.");
        }

        String role = user.getRole();

        String token = jwtProvider.generateJwtToken(username, Collections.singleton(role));

        data = new UserSignInResponseDto(token, user.getUsername(), exprTime);

        return ResponseDto.setSuccess("로그인을 성공하였습니다.", data);
    }
}
