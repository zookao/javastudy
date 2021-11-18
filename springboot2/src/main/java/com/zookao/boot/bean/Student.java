package com.zookao.boot.bean;

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
public class Student {
    private Long id;
    private String name;
    private String email;
    private Integer age;
}
