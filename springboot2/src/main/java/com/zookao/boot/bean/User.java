package com.zookao.boot.bean;

import lombok.*;

/**
 * User: zookao
 * Date: 2021-11-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private String username;
    private String password;
}
