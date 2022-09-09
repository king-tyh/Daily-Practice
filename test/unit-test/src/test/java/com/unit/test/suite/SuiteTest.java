package com.unit.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 此类的作用是整合测试也称 打包测试;可以把之前所有的写好的test class类进行集成;
 * 如需测试多个类时，只需要把相关的测试类加入到"{}"即可;如果不是同一个包类的class记得加上package名称。
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestTwo.class, TestOne.class})
public class SuiteTest {
     /* 写一个空类：不包含任何方法
      * 更改测试运行器Suite.class
      * 将测试类作为数组传入到Suite.SuiteClasses（{}）中
      */
}
