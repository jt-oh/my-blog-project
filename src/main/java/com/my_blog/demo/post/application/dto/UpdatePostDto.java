package com.my_blog.demo.post.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class UpdatePostDto {
    private String title;
    private String Content;
}
