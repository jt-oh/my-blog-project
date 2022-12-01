package com.my_blog.demo.post.application;

import java.util.*;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.my_blog.demo.post.application.dto.CreatePostDto;
import com.my_blog.demo.post.application.dto.GetPostsIndexRequest;
import com.my_blog.demo.post.application.dto.PostDto;
import com.my_blog.demo.post.application.dto.UpdatePostDto;
import com.my_blog.demo.post.application.outbound_ports.PostPresentor;
import com.my_blog.demo.post.application.outbound_ports.PostRepository;
import com.my_blog.demo.post.domain.Post;
import com.my_blog.demo.post.domain.value_objects.PostContent;
import com.my_blog.demo.post.domain.value_objects.PostId;
import com.my_blog.demo.post.domain.value_objects.PostTitle;

public class PostServiceImpl implements PostService {
    /**
     * Outbound Port for Post Persistency
     */
    private PostRepository postsPersistency;

    /**
     * Outbound Port for Post Presentor
     */
    private PostPresentor postPresentor;

    /**
     * 
     * @param postsPersistency
     * @param postPresentor
     */

    public PostServiceImpl(PostRepository postsPersistency, PostPresentor postPresentor) {
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
        PostId postIdVO = new PostId(postId);

        Post post = postsPersistency.find(postIdVO)
            .orElseThrow(() -> new ResourceNotFoundException());

        PostDto postDto = PostDto.builder()
            .id(post.getPostId().getPostId())
            .title(post.getTitle().getTitle())
            .content(post.getContent().getContent())
            .authorId(post.getAuthorId())
            .build();

        postPresentor.show(postDto);
    }


    public void updatePost(long postId, UpdatePostDto updatePostDto) {
        PostId postIdVO = new PostId(postId);

        Post existPost = postsPersistency.find(postIdVO)
            .orElseThrow(() -> new ResourceNotFoundException());

        PostTitle newPostTitle = new PostTitle(updatePostDto.getTitle());
        PostContent newPostContent = new PostContent(updatePostDto.getContent());

        existPost.setTitle(newPostTitle);
        existPost.setContent(newPostContent);

        Post updatedPost = postsPersistency.save(existPost);

        PostDto postDto = PostDto.builder()
            .id(updatedPost.getPostId().getPostId())
            .title(updatedPost.getTitle().getTitle())
            .content(updatedPost.getContent().getContent())
            .authorId(updatedPost.getAuthorId())
            .build();

        postPresentor.show(postDto);
    }


    public long deletePostById(long postId) {

        return postId;
    }
}
