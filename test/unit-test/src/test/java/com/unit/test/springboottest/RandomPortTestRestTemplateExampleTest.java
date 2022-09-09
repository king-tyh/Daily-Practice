package com.unit.test.springboottest;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * 定义测试类
 *  Spring Boot Test 默认使用 JUnit 5 框架，@RunWith(SpringRunner.class) 注解可方便开发者使用 JUnit 4 框架
 *
 * Spring Boot 用 @SpringBootTest 注解替代了 spring-test 中的 @ContextConfiguration 注解，该注解可以创建 ApplicationContext，
 * 而且还添加了一些其他注解来测试特定的应用。
 *
 * 使用 @SpringBootTest 的 WebEnvironment 属性来修改测试的运行方式。
 * MOCK：加载 Web 应用程序上下文并提供模拟的 Web 环境。该注解不会启动嵌入的服务器，可以结合@AutoConfigureMockMvc 和 @AutoConfigureWebTest-Client 注解使用。
 * RANDOM_PORT：加载 WebServerApplicationContext 并提供真实的 Web环境，嵌入的服务器启动后可以监听随机端口。
 * DEFINED_PORT：加载 WebServerApplicationContext 并提供真实的 Web 环境，嵌入的服务器启动后可以监听特定的端口。特定的端口可以从 application.properties 获取，也可以设置为默认的 8080 端口。
 * NONE：使用 SpringApplication 加载 ApplicationContext，但不提供任何 Web 环境
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RandomPortTestRestTemplateExampleTest {
    @Test
    void exampleTest(@Autowired TestRestTemplate restTemplate) {
        String body = restTemplate.getForObject("/", String.class);
        // 返回结果断言
        AssertionsForClassTypes.assertThat(body).isEqualTo("Hello World");
    }
}
