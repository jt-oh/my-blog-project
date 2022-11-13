package com.my_blog.demo.post.application;

import com.my_blog.demo.post.application.dto.CreatePostDto;
import com.my_blog.demo.post.application.dto.UpdatePostDto;

public interface PostService<T> {
    public T createPost(CreatePostDto createPostDto);
    public T getPostById(long postId);
    public T updatePost(UpdatePostDto updatePostDto);
    public long deletePostById(long postId);
}