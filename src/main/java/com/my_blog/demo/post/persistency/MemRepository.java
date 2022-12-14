package com.my_blog.demo.post.persistency;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import com.my_blog.demo.post.application.outbound_ports.PostRepository;
import com.my_blog.demo.post.domain.Post;
import com.my_blog.demo.post.domain.value_objects.PostId;

public class MemRepository implements PostRepository {
    public static List<Post> memStore = new ArrayList<Post> ();
    private static AtomicLong counter = new AtomicLong();
    private static PostComparatorById postComparatorById = new PostComparatorById();

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

        if (postInStore == null) {
            return Optional.empty();
        }

        return Optional.of(new Post(postInStore));
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

        posts.sort(postComparatorById);

        return posts;
    }
}

class PostComparatorById implements Comparator<Post> {
    @Override
    public int compare(Post o1, Post o2) {        
        long post1Id = o1.getPostId().getPostId();
        long post2Id = o2.getPostId().getPostId();

        if (post1Id > post2Id) {
            return 1;
        }

        if (post1Id < post2Id) {
            return -1;
        }

        return 0;
    }
}
