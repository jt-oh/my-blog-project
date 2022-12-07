package com.my_blog.demo.post.domain.value_objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PostContentTest {
    private String contents = "test contents";
    
    @Test
    public void postContentConstructionWithStringTest() {
        PostContent postContent = new PostContent(contents);

        assertEquals(true, postContent.getContent().equals(contents));
    }

    @Test
    public void postContentConstructionWithPostTitleTest() {
        PostContent postContent = new PostContent(contents);

        PostContent postTitleCreatedFromPostTitle = new PostContent(postContent);

        assertEquals(true, postTitleCreatedFromPostTitle.getContent().equals(contents));
    }

    @Test
    public void postTitleConstructionWithNullTest() {
        assertThrows(RuntimeException.class, () -> new PostContent((PostContent) null));
    }

    @Test
    public void containsTest() {
        PostContent postContent = new PostContent(contents);

        assertTrue(postContent.contains(contents));
        for (String word: contents.split(" ")) {
            assertTrue(postContent.contains(word));
        }

        assertFalse(postContent.contains("Text"));
        assertFalse(postContent.contains("Turtle"));
    }
}
