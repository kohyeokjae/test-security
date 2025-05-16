package com.test.testsecurity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = "posts, notices")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        this.posts.add(post);
        post.setUser(this); // Comment 객체에 현재의 Post를 설정
    }

    public void removeComment(Post post) {
        this.posts.remove(post);
        post.setUser(null); // Comment 객체의 연관 관계 해제
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Notice> notices = new ArrayList<>();

    public void addNotice(Notice notice) {
        this.notices.add(notice);
        notice.setUser(this); // Comment 객체에 현재의 Post를 설정
    }

    public void removeNotice(Notice notice) {
        this.notices.remove(notice);
        notice.setUser(null); // Comment 객체의 연관 관계 해제
    }
}
