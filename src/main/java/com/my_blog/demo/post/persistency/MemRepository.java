package com.my_blog.demo.post.persistency;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import com.my_blog.demo.post.application.outbound_ports.Posts;
import com.my_blog.demo.post.domain.Post;
import com.my_blog.demo.post.domain.value_objects.PostId;

public class MemRepository implements Posts {
    public static List<Post> memStore = new ArrayList<Post> ();
    private static AtomicLong counter = new AtomicLong();


    public Post save(Post post) {
        Post newPost = new Post(post);
        
        if (post.getPostId() != null) {
            memStore.removeIf(
                existPost -> existPost.getPostId().equals(post.getPostId())
            );
        }
        else {
            PostId newPostId = new PostId(counter.incrementAndGet());
            newPost.setPostId(newPostId);
        }
        
        memStore.add(newPost);

        return new Post(newPost);
    }


    public Optional<Post> find(PostId postId) {
        Post postInStore = memStore.stream()
            .filter(post -> post.getPostId().equals(postId))
            .findFirst()
            .orElse(null);

        return Optional.ofNullable(new Post(postInStore));
    }


    public PostId deleteById(PostId postId) {
        memStore.removeIf(
            existPost -> existPost.getPostId().equals(postId)
        );

        return new PostId(postId);
    }


    public List<Post> findAll() {
        List<Post> posts = new ArrayList<Post>();

        for (Post post: memStore) {
            posts.add(new Post(post));
        }

        return posts;
    }
}
