package com.my_blog.demo.post.persistency;

import java.util.*;

import com.my_blog.demo.post.application.outbound_ports.Posts;
import com.my_blog.demo.post.domain.Post;
import com.my_blog.demo.post.domain.value_objects.PostId;

public class MySqlPostsRepository implements Posts {
    public Post save(Post post) {
        return new Post();
    }


    public Optional<Post> find(PostId postId) {
        return Optional.empty();
    }


    public List<Post> findAll() {
        return new ArrayList<Post>();
    }


    public PostId deleteById(PostId postId) {
        return new PostId(0);
    }
}
