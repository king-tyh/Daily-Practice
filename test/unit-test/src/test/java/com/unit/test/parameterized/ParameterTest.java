package com.unit.test.parameterized;

import static org.junit.Assert.assertEquals;

import com.unit.test.utils.Calculate;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * 此类的作用是参数化设置。如果要测试多组数据，可以通过参数化设置把测试的数据先配置好
 **/
@RunWith(Parameterized.class)
public class ParameterTest {
    // 预期
    int expected;

    // 输入1
    int input1;

    // 输入2
    int input2;

    public ParameterTest(int expected, int input1, int input2) {
        this.expected = expected;
        this.input1 = input1;
        this.input2 = input2;
    }

    /**
     * 配置一组测试的数据
     */
    @Parameters
    public static Collection<Object[]> build() {
        return Arrays.asList(new Object[][]{{3, 1, 2}, {4, 2, 2}});
    }

    @Before
    public void setUp(){
    }

    @Test
    public void test_add_success() {
        assertEquals(expected, Calculate.add(input1, input2));
    }
}
