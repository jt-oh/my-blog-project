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
import com.my_blog.demo.post.dto.CreatePostRestResponse;
import com.my_blog.demo.post.dto.PostRestDto;

@RestController
public class PostRestController {

    @PostMapping(path="/posts")
    public CreatePostRestResponse createPost(@RequestBody CreatePostRestRequest createPostRestRequest) {
        MySqlPostsRepository mySqlPostsRepository = new MySqlPostsRepository();

        PostRestPresentor postRestPresentor = new PostRestPresentor();

        PostService<PostRestDto> postService = new PostServiceImpl<PostRestDto>(
            mySqlPostsRepository,
            postRestPresentor
        );

        CreatePostDto createPostDto = createPostRestRequest.toCreatePostDto();

        return new CreatePostRestResponse(postService.createPost(createPostDto));
    }
}
