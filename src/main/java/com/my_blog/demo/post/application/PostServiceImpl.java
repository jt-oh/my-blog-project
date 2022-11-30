package com.my_blog.demo.post.application;

import java.util.*;

import com.my_blog.demo.post.application.dto.CreatePostDto;
import com.my_blog.demo.post.application.dto.GetPostsIndexRequest;
import com.my_blog.demo.post.application.dto.UpdatePostDto;
import com.my_blog.demo.post.application.outbound_ports.PostDto;
import com.my_blog.demo.post.application.outbound_ports.PostPresentor;
import com.my_blog.demo.post.application.outbound_ports.Posts;
import com.my_blog.demo.post.domain.Post;
import com.my_blog.demo.post.domain.value_objects.PostContent;
import com.my_blog.demo.post.domain.value_objects.PostTitle;

public class PostServiceImpl implements PostService {
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
        Post post = Post.builder()
            .title(new PostTitle(createPostDto.getTitle()))
            .content(new PostContent(createPostDto.getContent()))
            .authorId(1L)
            .build();

        post = postsPersistency.save(post);

        PostDto postDto = PostDto.builder()
            .id(post.getPostId().getPostId())
            .title(post.getTitle().getTitle())
            .content(post.getContent().getContent())
            .authorId(post.getAuthorId())
            .build();

        postPresentor.show(postDto);
    }


    public void getPostsIndex(GetPostsIndexRequest getPostsIndexRequest) {
        List<Post> posts = postsPersistency.findAll();

        List<PostDto> postDtos = new ArrayList<PostDto>();
        for (Post post: posts) {
            PostDto postDto = PostDto.builder()
                .id(post.getPostId().getPostId())
                .title(post.getTitle().getTitle())
                .content(post.getContent().getContent())
                .authorId(post.getAuthorId())
                .build();

            postDtos.add(postDto);
        }

        postPresentor.show(postDtos);
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
