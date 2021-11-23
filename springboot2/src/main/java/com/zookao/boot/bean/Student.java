package com.zookao.boot.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * User: zookao
 * Date: 2021-11-04
 */
@ApiModel("学生实体")
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@TableName("student")
public class Student {
    @ApiModelProperty("编号")
    private Long id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("年龄")
    private Integer age;

    // @TableField(exist = false)
    // private String password;
}
