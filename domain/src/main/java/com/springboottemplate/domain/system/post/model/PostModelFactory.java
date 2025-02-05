package com.springboottemplate.domain.system.post.model;

import com.springboottemplate.common.exception.ApiException;
import com.springboottemplate.common.exception.error.ErrorCode.Business;
import com.springboottemplate.domain.system.post.db.SysPostEntity;
import com.springboottemplate.domain.system.post.db.SysPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Sleepyhead
 */
@Component
@RequiredArgsConstructor
public class PostModelFactory {

    private final SysPostService postService;

    public PostModel loadById(Long postId) {
        SysPostEntity byId = postService.getById(postId);
        if (byId == null) {
            throw new ApiException(Business.COMMON_OBJECT_NOT_FOUND, postId, "职位");
        }
        return new PostModel(byId, postService);
    }

    public PostModel create() {
        return new PostModel(postService);
    }

}
