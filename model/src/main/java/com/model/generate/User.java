package com.model.generate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "新增用户参数类")
public class User {
    @ApiModelProperty(value = "主键ID")
    private String id;
    @ApiModelProperty(value = "用户名",required = true)
    private String name;
    @ApiModelProperty(value = "性别",required = true)
    private Integer sex;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
    @ApiModelProperty(value = "创建时间")
    private Date createdate;
}