package com.my_blog.demo.post;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my_blog.demo.post.application.PostService;
import com.my_blog.demo.post.application.PostServiceImpl;
import com.my_blog.demo.post.application.dto.CreatePostDto;
import com.my_blog.demo.post.database.MySqlPostsRepository;
import com.my_blog.demo.post.dto.CreatePostRestRequest;
import com.my_blog.demo.post.dto.PostRestDto;

@RestController
public class PostRestController {

    @PostMapping(path="/posts")
    public PostRestDto createPost(@RequestBody CreatePostRestRequest createPostRestRequest) {
        MySqlPostsRepository mySqlPostsRepository = new MySqlPostsRepository();

        PostRestPresentor postRestPresentor = new PostRestPresentor();

        PostService postService = new PostServiceImpl(
            mySqlPostsRepository,
            postRestPresentor
        );

        CreatePostDto createPostDto = createPostRestRequest.toCreatePostDto();

        postService.createPost(createPostDto);

        return postRestPresentor.getPostResponse();
    }
    }
}
