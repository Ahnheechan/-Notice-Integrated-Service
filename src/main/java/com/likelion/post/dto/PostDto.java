package com.likelion.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public class PostDto {
    private String title;
    private String content;
    private String username;

    @Builder
    public PostDto(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }
}
