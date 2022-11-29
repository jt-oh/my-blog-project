package com.my_blog.demo.post;

import com.my_blog.demo.post.application.outbound_ports.PostDto;
import com.my_blog.demo.post.application.outbound_ports.PostPresentor;
import com.my_blog.demo.post.dto.PostRestDto;

public class PostRestPresentor implements PostPresentor {
    private PostRestDto postResponse;
    public void show(PostDto post) {
        postResponse = PostRestDto.builder()
            .id(post.getId())
            .authorId(post.getAuthorId())
            .title(post.getTitle())
            .content(post.getContent())
            .build();
    }

    public PostRestDto getPostResponse() {
        return postResponse;
    }
}
