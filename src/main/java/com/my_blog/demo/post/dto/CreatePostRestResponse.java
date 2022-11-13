package com.my_blog.demo.post.dto;

import lombok.Getter;

@Getter
public class CreatePostRestResponse {
    private long id;
    private long author_id;
    private String title;
    private String content;

    public CreatePostRestResponse(PostRestDto postRestDto) {
        this.id = postRestDto.getId();
        this.author_id = postRestDto.getAuthorId();
        this.title = postRestDto.getTitle();
        this.content = postRestDto.getContent();
    }
}