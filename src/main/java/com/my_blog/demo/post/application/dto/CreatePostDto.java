package com.my_blog.demo.post.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Builder
public class CreatePostDto {
    private long authorId;
    private String title;
    private String content;
}