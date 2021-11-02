package com.zookao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User: zookao
 * Date: 2021-10-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCaozongchao {
    private Long id;
    private String name;
    private int age;
    private Long managerId;
    private Date createTime;
}
