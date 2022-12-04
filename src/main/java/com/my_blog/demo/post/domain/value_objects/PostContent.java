package com.my_blog.demo.post.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostContent {
    private String content;

    public PostContent(PostContent content) {
        this.content = content.getContent();
    }

    public boolean contains(String keyword) {
        return content.contains(keyword);
    }
}
