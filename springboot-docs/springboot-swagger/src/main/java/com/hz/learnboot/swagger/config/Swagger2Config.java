package com.hz.learnboot.swagger.config;

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

/**
 * Swagger 配置
 *
 * Created by hezhao on 2018-07-10 13:33
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    // 此类主要是整个的Swagger配置项，利用这个类需要来指派扫描包
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.hz.learnboot.swagger.controller")) // 文档包路径, 一般设置为控制器所在包名
                .paths(PathSelectors.any()) // 设置文档的显示类型
                .build();
    }

    /**
     * 构建 api文档的详细信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot 测试使用 Swagger2 构建RESTful API") // 页面标题
                .description("API 描述") // 描述
                .version("1.0") // 版本号
                .termsOfServiceUrl("http://localhost/")
                .contact(new Contact("Horace", "https://gitee.com/hezhao", "hezhao_java@163.com")) // 作者
                .license("Apache 2.0") // 开源许可证
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0") // 许可证URL
                .build();
    }
}