package com.zookao.boot;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * User: zookao
 * Date: 2021-11-22
 *
 * 注解
 * Test
 * ParameterizedTest 参数化测试
 * RepeatedTest 方法可重复执行
 * DisplayName 展示名称
 * BeforeEach 每个单元测试之前执行
 * AfterEach
 * BeforeAll
 * AfterAll
 * Tag
 * Disabled
 * Timeout
 * ExtendWith
 * Transactional 测试完成后自动回滚
 * Nested 嵌套测试 class
 *
 * 方法
 * assertEquals
 * assertSame
 * assertArrayEquals
 * assertAll
 * assertThrows
 * fail 快速失败
 */
@SpringBootTest
@DisplayName("junit5测试")
public class Junit5Test {

    @Test
    @Disabled
    @DisplayName("testDisplayName")
    @Timeout(value = 5,unit = TimeUnit.SECONDS)
    public void testDisplayName(){
        System.out.println(1);
    }

    @BeforeEach
    public void testBeforeEach(){
        System.out.println("unit test begin");
    }

    @AfterEach
    public void testAfterEach(){
        System.out.println("unit test end");
    }

    @BeforeAll
    public static void testBeforeAll(){
        System.out.println("test begin");
    }

    @AfterAll
    public static void testAfterAll(){
        System.out.println("test end");
    }

    /**
     * 断言失败后，终止运行
     */
    @RepeatedTest(5) //重复5次
    public void testSimpleAssert(){
        int cal = cal(2, 3);
        Assertions.assertEquals(6,cal,"test fail");
    }

    public int cal(int i,int j){
        return i+j;
    }

    /**
     * 测试前置条件
     */
    @Test
    @DisplayName("测试前置条件")
    public void testAssumptions(){
        Assumptions.assumeTrue(true,"结果不是true");
        System.out.println(1111111);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    // @MethodSource("stringProvider")
    @DisplayName("参数化测试")
    public void testParameterized(int i){
        System.out.println(i);
    }

    static Stream<String> stringProvider(){
        return Stream.of("apple","banana");
    }
}
