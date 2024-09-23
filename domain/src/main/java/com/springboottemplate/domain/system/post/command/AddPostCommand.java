package com.springboottemplate.domain.system.post.command;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

/**
 * @author valarchie
 */
@Data
public class AddPostCommand {

    @NotBlank(message = "岗位编码不能为空")
    @Size(max = 64, message = "岗位编码长度不能超过64个字符")
    protected String postCode;

    /**
     * 岗位名称
     */
    @NotBlank(message = "岗位名称不能为空")
    @Size(max = 64, message = "岗位名称长度不能超过64个字符")
    protected String postName;

    /**
     * 岗位排序
     */
    @NotNull(message = "显示顺序不能为空")
    protected Integer postSort;

    protected String remark;

    @PositiveOrZero
    protected String status;

}
