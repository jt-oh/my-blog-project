package com.my_blog.demo.post.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostId {
    private long postId;

    public PostId(PostId postId) {
        if (postId != null) {
            this.postId = postId.getPostId();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (! (obj instanceof PostId)) {
            return false;
        }

        return this.postId == ((PostId) obj).getPostId();
    }
}
