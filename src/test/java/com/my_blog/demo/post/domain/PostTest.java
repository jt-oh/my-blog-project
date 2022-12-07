package com.my_blog.demo.post.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.my_blog.demo.post.domain.value_objects.PostContent;
import com.my_blog.demo.post.domain.value_objects.PostId;
import com.my_blog.demo.post.domain.value_objects.PostTitle;

public class PostTest {
    private PostId postId = new PostId(990227L);
    private PostTitle postTitle = new PostTitle("Post Title");
    private PostContent postContent = new PostContent("post contents");
    private long authorId = 123456L;
    
    @Test
    public void postConstructionWithNonNullPostId() {
        Post post = Post.builder()
            .postId(postId)
            .title(postTitle)
            .content(postContent)
            .authorId(authorId)
            .build();

        assertTrue(post.getPostId().equals(postId));
        assertTrue(post.getTitle().equals(postTitle));
        assertTrue(post.getContent().equals(postContent));
        assertTrue(post.getAuthorId() == authorId);
    }

    @Test
    public void containsKeywordTest() {
        Post post = Post.builder()
            .postId(postId)
            .title(postTitle)
            .content(postContent)
            .authorId(authorId)
            .build();

        assertTrue(post.containsKeyword("Post"));
        assertTrue(post.containsKeyword("post"));
        assertTrue(post.containsKeyword("Title"));
        assertTrue(post.containsKeyword("contents"));
        
        assertFalse(post.containsKeyword("galaxy"));
        assertFalse(post.containsKeyword("apple"));
        assertFalse(post.containsKeyword("tilt"));
        assertFalse(post.containsKeyword("sot"));
    }
}
