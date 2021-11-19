package com.zookao.boot.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * User: zookao
 * Date: 2021-11-04
 */
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@TableName("student")
public class Student {
    private Long id;
    private String name;
    private String email;
    private Integer age;

    @TableField(exist = false)
    private String password;
}
