package com.my_blog.demo.post.application.outbound_ports;

import java.util.List;

import com.my_blog.demo.post.application.dto.PostDto;

public interface PostPresentor {
    public void show(PostDto postDto);
    public void show(List<PostDto> postsDto);
}
