package com.my_blog.demo.post.application;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import com.my_blog.demo.post.application.dto.CreatePostDto;
import com.my_blog.demo.post.application.dto.GetPostsIndexRequest;
import com.my_blog.demo.post.application.dto.UpdatePostDto;
import com.my_blog.demo.post.application.outbound_ports.PostDto;
import com.my_blog.demo.post.application.outbound_ports.PostPresentor;
import com.my_blog.demo.post.application.outbound_ports.Posts;

public class PostServiceImpl implements PostService {

    private static AtomicLong counter = new AtomicLong();

    /**
     * Outbound Port for Post Persistency
     */
    private Posts postsPersistency;

    /**
     * Outbound Port for Post Presentor
     */
    private PostPresentor postPresentor;

    /**
     * 
     * @param postsPersistency
     * @param postPresentor
     */

    public PostServiceImpl(Posts postsPersistency, PostPresentor postPresentor) {
        this.postsPersistency = postsPersistency;
        this.postPresentor = postPresentor;
    }

    /**
     * 
     */
    public void createPost(CreatePostDto createPostDto) {

        PostDto postDto = PostDto.builder()
            .id(counter.incrementAndGet())
            .title(createPostDto.getTitle())
            .content(createPostDto.getContent())
            .authorId(1L)
            .build();

        postPresentor.show(postDto);
    }


    public void getPostsIndex(GetPostsIndexRequest getPostsIndexRequest) {
        List<PostDto> posts = new ArrayList<PostDto>();

        for (int i = 0; i < 5; i++) {
            PostDto post = PostDto.builder()
                .id(i)
                .title(String.valueOf(i) + " Title")
                .content(String.valueOf(i) + " Content")
                .authorId(1L)
                .build();

            posts.add(post);
        }

        postPresentor.show(posts);
    }


    public void getPostById(long postId) {


        postPresentor.show(new PostDto());
    }


    public void updatePost(UpdatePostDto updatePostDto) {

        postPresentor.show(new PostDto());
    }

    
    public long deletePostById(long postId) {

        return postId;
    }
}
