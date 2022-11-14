package com.my_blog.demo.post.dto;

import com.my_blog.demo.post.application.outbound_ports.PostPresentorDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostRestDto implements PostPresentorDto {
    private long id;
    private long authorId;
    private String title;
    private String content;
}
