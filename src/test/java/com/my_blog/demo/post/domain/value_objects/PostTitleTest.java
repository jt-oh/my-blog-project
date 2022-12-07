package com.my_blog.demo.post.domain.value_objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PostTitleTest {
    private String title = "Test Title";
    
    @Test
    public void postTitleConstructionWithStringTest() {
        PostTitle postTitle = new PostTitle(title);

        assertEquals(true, postTitle.getTitle().equals(title));
    }

    @Test
    public void postTitleConstructionWithPostTitleTest() {
        PostTitle postTitle = new PostTitle(title);

        PostTitle postTitleCreatedFromPostTitle = new PostTitle(postTitle);

        assertEquals(true, postTitleCreatedFromPostTitle.getTitle().equals(title));
    }

    @Test
    public void postTitleConstructionWithNullTest() {
        assertThrows(RuntimeException.class, () -> new PostTitle((PostTitle) null));
    }

    @Test
    public void containsTest() {
        PostTitle postTitle = new PostTitle(title);

        assertTrue(postTitle.contains(title));
        for (String word: title.split("")) {
            assertTrue(postTitle.contains(word));
        }

        assertFalse(postTitle.contains("Text"));
        assertFalse(postTitle.contains("Turtle"));
    }
}
