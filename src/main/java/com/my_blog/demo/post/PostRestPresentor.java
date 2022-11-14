package com.my_blog.demo.post;

import com.my_blog.demo.post.application.outbound_ports.PostDto;
import com.my_blog.demo.post.application.outbound_ports.PostPresentor;
import com.my_blog.demo.post.dto.PostRestDto;

public class PostRestPresentor implements PostPresentor {
    public PostRestDto toPresentorDto(PostDto postDto) {
        return PostRestDto.builder()
            .id(postDto.getId())
            .authorId(postDto.getAuthorId())
            .title(postDto.getTitle())
            .content(postDto.getContent())
            .build();
    }
}
