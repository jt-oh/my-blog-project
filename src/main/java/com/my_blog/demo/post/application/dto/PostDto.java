package com.my_blog.demo.post.application.dto;

import com.my_blog.demo.post.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostDto {
    private long id;
    private long authorId;
    private String title;
    private String content;

    public PostDto(Post postDomainObject) {
        id = postDomainObject.getPostId().getPostId();
        authorId = postDomainObject.getAuthorId();
        title = postDomainObject.getTitle().getTitle();
        content = postDomainObject.getContent().getContent();
    }
}
