package com.my_blog.demo.post;

import java.util.List;

import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my_blog.demo.post.application.PostService;
import com.my_blog.demo.post.application.PostServiceImpl;
import com.my_blog.demo.post.application.dto.CreatePostDto;
import com.my_blog.demo.post.application.dto.GetPostsIndexRequest;
import com.my_blog.demo.post.application.outbound_ports.PostRepository;
import com.my_blog.demo.post.dto.CreatePostRestRequest;
import com.my_blog.demo.post.dto.PostRestDto;
import com.my_blog.demo.post.persistency.MemRepository;

@RestController
@RequestMapping(path="/api/v1/posts")
public class PostRestController {

    @PostMapping
    public PostRestDto createPost(@RequestBody CreatePostRestRequest createPostRestRequest) {
        PostRepository postRepository = new MemRepository();

        PostRestPresentor postRestPresentor = new PostRestPresentor();

        PostService postService = new PostServiceImpl(
            postRepository,
            postRestPresentor
        );

        CreatePostDto createPostDto = createPostRestRequest.toCreatePostDto();

        postService.createPost(createPostDto);

        return postRestPresentor.getPostResponse();
    }

    @GetMapping("/{postId}")
    public PostRestDto getPost(@PathVariable long postId) {
        PostRepository postRepository = new MemRepository();

        PostRestPresentor postRestPresentor = new PostRestPresentor();

        PostService postService = new PostServiceImpl(
            postRepository,
            postRestPresentor
        );

        postService.getPostById(postId);

        return postRestPresentor.getPostResponse();
    }

    @GetMapping
    public List<PostRestDto> getPostIndex(
        @RequestParam("page_size") Optional<Integer> pageSize,
        @RequestParam("page_index") Optional<Integer> pageIndex,
        @RequestParam("search_by") Optional<String> searchBy
    ) {
        PostRepository postRepository = new MemRepository();

        PostRestPresentor postRestPresentor = new PostRestPresentor();

        PostService postService = new PostServiceImpl(
            postRepository,
            postRestPresentor
        );

        GetPostsIndexRequest getPostsIndexRequest = GetPostsIndexRequest.builder()
            .pageSize(pageSize.orElse(30))
            .pageIndex(pageIndex.orElse(1))
            .searchBy(searchBy.orElse(""))
            .build();

        postService.getPostsIndex(getPostsIndexRequest);
        
        return postRestPresentor.getPostsResponse();
    }
}
