package com.zookao;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: zookao
 * Date: 2021-10-21
 */
public class TestHelloMaven {
    @Test
    public void testAdd(){
        HelloMaven helloMaven = new HelloMaven();
        int add = helloMaven.add(1, 2);
        Assert.assertEquals(3,add);
    }
}
