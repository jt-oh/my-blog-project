package com.my_blog.demo.post.domain.value_objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PostIdTest {
    

    @Test
    public void postIdConstructionWithLongTest() {
        long id = 990227L;

        PostId postId = new PostId(id);

        assertEquals(id, postId.getPostId());
    }

    @Test
    public void postIdConstrutionWithPostIdTest() {
        long id = 990227L;
        PostId postId = new PostId(id);

        PostId postIdCreatedFromPostId = new PostId(postId);

        assertEquals(id, postIdCreatedFromPostId.getPostId());
    }

    @Test
    public void postIdConstructionWithNullTest() {
        assertThrows(RuntimeException.class, () -> new PostId(null));
    }

    @Test
    public void equalsTest() {
        long id = 990227L;
        PostId postId = new PostId(id);

        assertTrue(postId.equals(postId));
        assertTrue(postId.equals(new PostId(id)));

        assertFalse(postId.equals(null));
        assertFalse(postId.equals(new PostId(0L)));
    }
}
