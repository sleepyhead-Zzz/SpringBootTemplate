package com.springboottemplate.admin.controller.system;


import com.springboottemplate.admin.customize.aop.accessLog.AccessLog;
import com.springboottemplate.common.core.base.BaseController;
import com.springboottemplate.common.core.dto.ResponseDTO;
import com.springboottemplate.common.core.page.PageDTO;
import com.springboottemplate.common.enums.common.BusinessTypeEnum;
import com.springboottemplate.domain.common.command.BulkOperationCommand;
import com.springboottemplate.domain.system.post.PostApplicationService;
import com.springboottemplate.domain.system.post.command.AddPostCommand;
import com.springboottemplate.domain.system.post.command.UpdatePostCommand;
import com.springboottemplate.domain.system.post.dto.PostDTO;
import com.springboottemplate.domain.system.post.query.PostQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 岗位信息操作处理
 *
 * @author ruoyi
 */
@Tag(name = "职位API", description = "职位相关的增删查改")
@RestController
@RequestMapping("/system/post")
@Validated
@RequiredArgsConstructor
public class SysPostController extends BaseController {

    private final PostApplicationService postApplicationService;

    /**
     * 获取岗位列表
     */
    @Operation(summary = "职位列表")
    @PreAuthorize("@permission.has('system:post:list')")
    @PostMapping("/list")
    public ResponseDTO<PageDTO<PostDTO>> getPagedPost(@RequestBody PostQuery query) {
        PageDTO<PostDTO> pageDTO = postApplicationService.page(query);
        return ResponseDTO.ok(pageDTO);
    }

    /**
     * 导出查询到的所有岗位信息到excel文件
     *
     * @param response http响应
     * @param query 查询参数
     * @author Kevin Zhang
     * @date 2023-10-02
     */
    @Operation(summary = "职位列表导出")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('system:post:export')")
    @GetMapping("/excel")
    public void exportPost(HttpServletResponse response, PostQuery query) {
        List<PostDTO> all = postApplicationService.getPostListAll(query);
//        CustomExcelUtil.writeToResponse(all, PostDTO.class, response);
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @Operation(summary = "职位详情")
    @PreAuthorize("@permission.has('system:post:query')")
    @GetMapping(value = "/{postId}")
    public ResponseDTO<PostDTO> getPostInfo(@PathVariable Long postId) {
        PostDTO post = postApplicationService.getPostInfo(postId);
        return ResponseDTO.ok(post);
    }

    /**
     * 新增岗位
     */
    @Operation(summary = "添加职位")
    @PreAuthorize("@permission.has('system:post:add')")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> addPost(@RequestBody AddPostCommand addCommand) {
        postApplicationService.addPost(addCommand);
        return ResponseDTO.ok();
    }

    /**
     * 修改岗位
     */
    @Operation(summary = "修改职位")
    @PreAuthorize("@permission.has('system:post:edit')")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("{postId}")
    public ResponseDTO<Void> editPost(@PathVariable Long postId, @RequestBody UpdatePostCommand updateCommand) {
        updateCommand.setPostId(postId);
        postApplicationService.updatePost(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 删除岗位
     */
    @Operation(summary = "删除职位")
    @PreAuthorize("@permission.has('system:post:remove')")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping
    public ResponseDTO<Void> removePost(@RequestParam @NotNull @NotEmpty List<Long> ids) {
        postApplicationService.deletePost(new BulkOperationCommand<>(ids));
        return ResponseDTO.ok();
    }

}
