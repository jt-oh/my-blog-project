package com.my_blog.demo.post.dto;

import com.my_blog.demo.post.application.dto.UpdatePostDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdatePostRestRequest {
    private String title;
    private String content;

    public UpdatePostDto toUpdatePostDto() {
        return UpdatePostDto.builder()
            .title(this.title)
            .content(this.content)
            .build();
    }
}
