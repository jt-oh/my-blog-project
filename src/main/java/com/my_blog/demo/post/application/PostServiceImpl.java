package com.my_blog.demo.post.application;

import java.util.concurrent.atomic.AtomicLong;

import com.my_blog.demo.post.application.dto.CreatePostDto;
import com.my_blog.demo.post.application.dto.UpdatePostDto;
import com.my_blog.demo.post.application.outbound_ports.PostDto;
import com.my_blog.demo.post.application.outbound_ports.PostPresentor;
import com.my_blog.demo.post.application.outbound_ports.Posts;

public class PostServiceImpl<T> implements PostService<T> {

    private static AtomicLong counter = new AtomicLong();

    /**
     * Outbound Port for Post Persistency
     */
    private Posts postsPersistency;

    /**
     * Outbound Port for Post Presentor
     */
    private PostPresentor<T> postPresentor;

    /**
     * 
     * @param postsPersistency
     * @param postPresentor
     */

    public PostServiceImpl(Posts postsPersistency, PostPresentor<T> postPresentor) {
        this.postsPersistency = postsPersistency;
        this.postPresentor = postPresentor;
    }

    /**
     * 
     */
    public T createPost(CreatePostDto createPostDto) {

        PostDto postDto = PostDto.builder()
            .id(counter.incrementAndGet())
            .title(createPostDto.getTitle())
            .content(createPostDto.getContent())
            .authorId(1L)
            .build();

        return postPresentor.toPresentorDto(postDto);
    }


    public T getPostById(long postId) {


        return postPresentor.toPresentorDto(new PostDto());
    }


    public T updatePost(UpdatePostDto updatePostDto) {

        return postPresentor.toPresentorDto(new PostDto());
    }

    public long deletePostById(long postId) {

        return postId;
    }
}
