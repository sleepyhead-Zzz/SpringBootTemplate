package com.springboottemplate.domain.system.post.dto;

import cn.hutool.core.bean.BeanUtil;
import com.springboottemplate.common.enums.BasicEnumUtil;
import com.springboottemplate.common.enums.common.StatusEnum;
import com.springboottemplate.domain.system.post.db.SysPostEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDTO {

    public PostDTO(SysPostEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
            statusStr = BasicEnumUtil.getDescriptionByValue(StatusEnum.class, entity.getStatus());
        }
    }

    @Schema(description = "岗位ID")
    private Long postId;


    @Schema(description = "岗位编码")
    private String postCode;

    @Schema(description = "岗位名称")
    private String postName;


    @Schema(description = "岗位排序")
    private Integer postSort;

    @Schema(description = "备注")
    private String remark;

    private Integer status;

    @Schema(description = "状态")
    private String statusStr;

    private Date createTime;

}
