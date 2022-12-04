package com.my_blog.demo.post.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostTitle {
    private String title;

    public PostTitle(PostTitle title) {
        this.title = title.getTitle();
    }

    public boolean contains(String keyword) {
        return title.contains(keyword);
    }
}
