package com.my_blog.demo.post.dto;

import com.my_blog.demo.post.application.dto.CreatePostDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreatePostRestRequest {
    private String title;
    private String content;

    public CreatePostDto toCreatePostDto() {
        return CreatePostDto.builder()
            .title(this.title)
            .content(this.content)
            .build();
    }
}
