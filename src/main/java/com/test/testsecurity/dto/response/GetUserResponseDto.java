package com.test.testsecurity.dto.response;

import com.test.testsecurity.entity.Notice;
import com.test.testsecurity.entity.Post;
import com.test.testsecurity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class GetUserResponseDto {
    private Long id;
    private String username;
    private String password;
    private String role;

    public GetUserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
