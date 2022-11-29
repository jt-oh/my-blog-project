package com.my_blog.demo.post.application;

import com.my_blog.demo.post.application.dto.CreatePostDto;
import com.my_blog.demo.post.application.dto.GetPostsIndexRequest;
import com.my_blog.demo.post.application.dto.UpdatePostDto;

public interface PostService {
    public void createPost(CreatePostDto createPostDto);
    public void getPostsIndex(GetPostsIndexRequest getPostsIndexRequest);
    public void getPostById(long postId);
    public void updatePost(UpdatePostDto updatePostDto);
    public long deletePostById(long postId);
}
