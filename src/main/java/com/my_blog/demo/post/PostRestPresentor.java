package com.my_blog.demo.post;

import java.util.*;

import com.my_blog.demo.post.application.dto.PostDto;
import com.my_blog.demo.post.application.outbound_ports.PostPresentor;
import com.my_blog.demo.post.dto.PostRestDto;

public class PostRestPresentor implements PostPresentor {
    private PostRestDto postResponse;
    private List<PostRestDto> postsResponse;


    public void show(PostDto post) {
        postResponse = PostRestDto.builder()
            .id(post.getId())
            .authorId(post.getAuthorId())
            .title(post.getTitle())
            .content(post.getContent())
            .build();
    }

    public void show(List<PostDto> posts) {
        postsResponse = new ArrayList<PostRestDto>();

        for (PostDto post : posts) {
            postResponse = PostRestDto.builder()
                .id(post.getId())
                .authorId(post.getAuthorId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

            postsResponse.add(postResponse);
        }
    }

    public PostRestDto getPostResponse() {
        return postResponse;
    }

    public List<PostRestDto> getPostsResponse() {
        return postsResponse;
    }
}
