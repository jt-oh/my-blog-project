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
            .authorId(createPostDto.getAuthorId())
            .build();

        post = postsPersistency.save(post);

        PostDto postDto = new PostDto(post);

        postPresentor.show(postDto);
    }


    public void getPostsIndex(GetPostsIndexRequest getPostsIndexRequest) {
        // ToDo: minimize memory usage by delegating post filters to persistency.
        List<Post> posts = postsPersistency.findAll();

        String keyword = getPostsIndexRequest.getSearchBy();
        int pageSize = getPostsIndexRequest.getPageSize();
        int skip = (getPostsIndexRequest.getPageIndex() - 1) * pageSize;

        List<PostDto> postDtos = posts.stream()
            .filter(post -> post.containsKeyword(keyword))
            .skip(skip)
            .limit(pageSize)
            .map(post -> new PostDto(post))
            .toList();

        postPresentor.show(postDtos);
    }


    public void getPostById(long postId) {
        PostId postIdVO = new PostId(postId);

        Post post = postsPersistency.find(postIdVO)
            .orElseThrow(() -> new ResourceNotFoundException());

        PostDto postDto = new PostDto(post);

        postPresentor.show(postDto);
    }


    public void updatePost(long postId, UpdatePostDto updatePostDto) {
        PostId postIdVO = new PostId(postId);

        // make independent core business logic from User Interface(Rest API) by removing ResourceNotFoundException()
        Post existPost = postsPersistency.find(postIdVO)
            .orElseThrow(() -> new ResourceNotFoundException());

        PostTitle newPostTitle = new PostTitle(updatePostDto.getTitle());
        PostContent newPostContent = new PostContent(updatePostDto.getContent());

        existPost.setTitle(newPostTitle);
        existPost.setContent(newPostContent);

        Post updatedPost = postsPersistency.save(existPost);

        PostDto postDto = new PostDto(updatedPost);

        postPresentor.show(postDto);
    }


    public long deletePostById(long postId) {
        PostId postIdVO = new PostId(postId);

        postsPersistency.find(postIdVO)
            .orElseThrow(() -> new ResourceNotFoundException());
        
        return postsPersistency.deleteById(postIdVO).getPostId();
    }
}
