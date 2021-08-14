package com.lxj.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 刘兴俊
 * @since 2021-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String token;

    private String role;

    private String permission;

    private Date createTime;

    @Version
    private Integer version;

    private Date updateTime;

    @TableLogic
    private Integer deleted;


}
