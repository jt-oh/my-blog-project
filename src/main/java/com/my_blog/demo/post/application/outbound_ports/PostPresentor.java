package com.my_blog.demo.post.application.outbound_ports;

public interface PostPresentor<T> {
    public T toPresentorDto(PostDto postDto);
}
