package com.my_blog.demo.post.application.outbound_ports;

import java.util.List;
import java.util.Optional;

import com.my_blog.demo.post.domain.Post;
import com.my_blog.demo.post.domain.value_objects.PostId;

public interface Posts {
    public Post save(Post post);
    public Optional<Post> find(PostId postId);
    public List<Post> findAll();
    public PostId deleteById(PostId postId);
}
