package com.yuyun.usercenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


/**
 * 自定义 Swagger 接口文档的配置
 *
 * @author yuyun
 */
@Configuration
@EnableSwagger2WebMvc
//@Profile({"dev", "test"})  //限定接口文档仅在部分环境开启，防止接口暴露出去
public class Knife4jConfig {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 这里一定要标注你控制器的位置
                .apis(RequestHandlerSelectors.basePackage("com.yuyun.usercenter.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    /**
     * api 信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("用户中心")
                .description("用户中心接口文档")
                .termsOfServiceUrl("https://github.com/yunhaoyu")
                .contact(new Contact("yuyun","https://github.com/yunhaoyu","xxx@qq.com"))
                .version("1.0")
                .build();
    }
}
