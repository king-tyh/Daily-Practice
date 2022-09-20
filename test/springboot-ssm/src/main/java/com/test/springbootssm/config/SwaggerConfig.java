package com.test.springbootssm.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {
    @Bean
    public Docket getDocket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(getApiInfo())      //指定文档的“封面"信息
                .enable(true)//是否启动swagger 默认为true ,如果为false，则Swagger不能再浏览器中访问
                .select()                   //监控哪些接口
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //指定文档扫描范围
                .paths(PathSelectors.any()) //指定生成api的路径
                .build();
        return docket;
    }

    public ApiInfo getApiInfo(){
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("小说网站接口文档")
                .description("此文档详细描述了网站接口文档")
                .version("v1.0")
                .contact(new Contact("tyh", "", "1292989480@qq.com"))
                .build();
        return apiInfo;
    }
}
