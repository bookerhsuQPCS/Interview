package com.interview.demo.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@Configuration
@Profile({"loc","sit"})
public class SwaggerConfigure {

    @Value("${springfox.documentation.swagger-ui.enabled}")
    private boolean enabled;
    @Bean
    public Docket createRestApi() {
        return new Docket(
        		// 設定使用 OpenApi 3.0 規範
                DocumentationType.OAS_30)
                // 是否開啟 Swagger
                .enable(enabled)
                // 設定項目基本訊息
                .apiInfo(apiInfo())
                // 設定項目組名
                //.groupName("xxx組")
                // 選擇那些路徑和api會產生document
                .select()
                // 對所有api進行監控
                .apis(RequestHandlerSelectors.any())
                // 如果需要指定對某個套件的介面進行監控，則可以設定如下
                .apis(RequestHandlerSelectors.basePackage("com.interview.demo.controller"))
                // 對所有路徑進行監控
                .paths(PathSelectors.any())
                // 忽略以"/error"開頭的路徑,可以防止顯示如404錯誤接口
                .paths(PathSelectors.regex("/error.*").negate())
                // 忽略以"/actuator"開頭的路徑
                .paths(PathSelectors.regex("/actuator.*").negate())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文件標題
                .title("DEMO RES API ")
                // 文件描述
                .description("API")
                // 文件版本
                .version("0.0.1")
                // 設定版權訊息
                .license("Apache LICENSE 2.0")
                // 設定版權地址
                .licenseUrl("https://github/my-dlq")
                // 設定管理員訊息
                .contact(new Contact("Larry's Eexamination", "http://localhost", "larhsu@outlook.com"))
                .build();
    }

}
