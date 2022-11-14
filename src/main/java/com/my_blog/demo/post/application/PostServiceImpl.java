package com.my_blog.demo.post.application;

import java.util.concurrent.atomic.AtomicLong;

import com.my_blog.demo.post.application.dto.CreatePostDto;
import com.my_blog.demo.post.application.dto.UpdatePostDto;
import com.my_blog.demo.post.application.outbound_ports.PostDto;
import com.my_blog.demo.post.application.outbound_ports.PostPresentor;
import com.my_blog.demo.post.application.outbound_ports.PostPresentorDto;
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
    public PostPresentorDto createPost(CreatePostDto createPostDto) {

        PostDto postDto = PostDto.builder()
            .id(counter.incrementAndGet())
            .title(createPostDto.getTitle())
            .content(createPostDto.getContent())
            .authorId(1L)
            .build();

        return postPresentor.toPresentorDto(postDto);
    }


    public PostPresentorDto getPostById(long postId) {


        return postPresentor.toPresentorDto(new PostDto());
    }


    public PostPresentorDto updatePost(UpdatePostDto updatePostDto) {

        return postPresentor.toPresentorDto(new PostDto());
    }

    public long deletePostById(long postId) {

        return postId;
    }
}
