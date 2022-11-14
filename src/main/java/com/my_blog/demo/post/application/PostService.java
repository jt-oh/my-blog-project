package com.my_blog.demo.post.application;

import com.my_blog.demo.post.application.dto.CreatePostDto;
import com.my_blog.demo.post.application.dto.UpdatePostDto;
import com.my_blog.demo.post.application.outbound_ports.PostPresentorDto;

public interface PostService {
    public PostPresentorDto createPost(CreatePostDto createPostDto);
    public PostPresentorDto getPostById(long postId);
    public PostPresentorDto updatePost(UpdatePostDto updatePostDto);
    public long deletePostById(long postId);
}
