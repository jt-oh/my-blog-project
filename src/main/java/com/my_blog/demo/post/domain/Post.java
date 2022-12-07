package com.my_blog.demo.post.domain;

import com.my_blog.demo.post.domain.value_objects.PostContent;
import com.my_blog.demo.post.domain.value_objects.PostId;
import com.my_blog.demo.post.domain.value_objects.PostTitle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Post {
    private PostId postId;
    private PostTitle title;
    private PostContent content;
    private long authorId;

    public Post(Post post) {
        PostId postId = post.getPostId();
        if (postId != null) {
            this.postId = new PostId(postId);
        }

        title = new PostTitle(post.getTitle());
        content = new PostContent(post.getContent());
        authorId = post.getAuthorId();
    }

    public boolean containsKeyword(String keyword) {
        return title.contains(keyword) || content.contains(keyword);
    }
}
