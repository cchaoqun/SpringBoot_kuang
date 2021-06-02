package com.kuang.swaggerdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/30-17:00
 */
@Configuration
@EnableSwagger2//开启swagger2
public class SwaggerConfig {


    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }




    //配置了Swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev");
        //获取项目的环境(dev pro)
        //通过enviroment.acceptProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否启用swagger 如果false 则swagger不能在浏览器中访问
                .groupName("ccq")
                .enable(flag)
                .select()
                //RequestHandlerSelectors 配置要扫描接口的方式
                //basePackage指定要扫描的包
                //any()扫描全部
                //none()都不扫描
                //withClassAnnotation 扫描类上的注解  参数是一个注解的反射对象
                //withMethodAnnotation 扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.kuang.swaggerdemo.controller"))
                //过滤什么路径
//                .paths(PathSelectors.ant("/kuang/**"))
                .build();
    }

    //配置Swagger信息=apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("CCQ", "http://www.baidu.com", "chengchaoqun@hotmail.com");
        return  new ApiInfo("CCQ's Api Documentation",
                "This is the first swagger description",
                "1.0",
                "http://www.baidu.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }


}
