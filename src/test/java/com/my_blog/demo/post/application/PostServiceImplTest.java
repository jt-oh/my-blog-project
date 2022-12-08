package com.my_blog.demo.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.my_blog.demo.post.application.dto.CreatePostDto;
import com.my_blog.demo.post.application.dto.PostDto;
import com.my_blog.demo.post.application.outbound_ports.PostPresentor;
import com.my_blog.demo.post.application.outbound_ports.PostRepository;
import com.my_blog.demo.post.domain.Post;
import com.my_blog.demo.post.domain.value_objects.PostContent;
import com.my_blog.demo.post.domain.value_objects.PostId;
import com.my_blog.demo.post.domain.value_objects.PostTitle;
import com.my_blog.demo.post.persistency.MemRepository;

public class PostServiceImplTest {
    private PostService postService;
    private PostRepository testPostRepository;
    private TestPostPresentor testPostPresentor;

    public PostServiceImplTest() {
        this.testPostRepository = new MemRepository();
        this.testPostPresentor = new TestPostPresentor();

        postService = new PostServiceImpl(testPostRepository, testPostPresentor);
    }

    @Test
    public void getPostByIndexTest() {
        int findIndex = 6;
        Post targetPost = null;

        for (int i = 1; i <= 10; i++) {
            PostId postId = new PostId((long) i);
            PostTitle postTitle = new PostTitle("Test Title " + String.valueOf(i));
            PostContent postContent = new PostContent("post contents " + String.valueOf(i));
            long authorId = 990227L;
            
            Post post = Post.builder()
                .postId(postId)
                .title(postTitle)
                .content(postContent)
                .authorId(authorId)
                .build();

            MemRepository.memStore.add(post);

            if (i == findIndex) {
                targetPost = post;
            }
        }

        postService.getPostById(findIndex);

        PostDto retrievedPost = testPostPresentor.getPostResponse();

        assertEquals(targetPost.getPostId().getPostId(), retrievedPost.getId());
        assertTrue(retrievedPost.getTitle().equals(targetPost.getTitle().getTitle()));
        assertTrue(retrievedPost.getContent().equals(targetPost.getContent().getContent()));
        assertEquals(targetPost.getAuthorId(), retrievedPost.getAuthorId());
    }

    @Test
    public void createPostTest() {
        String[] titles = {"First Title", "Second Title", "Th title", "F title", "5 Title"};
        String[] contents = {"1 content", "2nd Content", "thr content", "4 Content", "fi content"};
        long authorId = 990227L;

        List<PostDto> createPostResponses = new ArrayList<PostDto>();
        for (int i = 0; i < 5; i++) {
            CreatePostDto createPostRequest = CreatePostDto.builder()
                .title(titles[i])
                .content(contents[i])
                .authorId(authorId)
                .build();

            postService.createPost(createPostRequest);

            createPostResponses.add(testPostPresentor.getPostResponse());
        }

        List<Post> createdPosts = MemRepository.memStore;

        createPostResponses.forEach(postDto -> {
            Post createdPost = createdPosts.stream()
                .filter(post -> post.getPostId().getPostId() == postDto.getId())
                .findFirst()
                .orElse(null);

            assertTrue(createdPost.getTitle().getTitle().equals(postDto.getTitle()));
            assertTrue(createdPost.getContent().getContent().equals(postDto.getContent()));
            assertEquals(authorId, createdPost.getAuthorId());
        });
    }

    private class TestPostPresentor implements PostPresentor{
        private PostDto postDto;

        @Override
        public void show(PostDto postDto) {
            this.postDto = postDto;
            
        }

        @Override
        public void show(List<PostDto> postsDto) {
            // TODO Auto-generated method stub
            
        }
        
        public PostDto getPostResponse() {
            return postDto;
        }
    }
}