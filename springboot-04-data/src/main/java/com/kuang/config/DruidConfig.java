package com.kuang.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.servlet.FilterRegistration;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/28-17:19
 */

@Configuration
public class DruidConfig {

    /*
       将自定义的 Druid数据源添加到容器中，不再让 Spring Boot 自动创建
       绑定全局配置文件中的 druid 数据源属性到 com.alibaba.druid.pool.DruidDataSource从而让它们生效
       @ConfigurationProperties(prefix = "spring.datasource")：作用就是将 全局配置文件中
       前缀为 spring.datasource的属性值注入到 com.alibaba.druid.pool.DruidDataSource 的同名参数中
     */
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }


    //后台监控   web.xml       ServletRegistrationBean
    //因为SpringBoot内置了servlet容器, 所以没有web.xml, 替代方法 ServletRegistrationBean
    @Bean
    public ServletRegistrationBean stateViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //后台需要有人登陆,  账号密码配置
        HashMap<String, String> initParameter = new HashMap<>();

        //增加配置
        initParameter.put("loginUsername","admin");//登陆key是固定的loginUsername loginPassword
        initParameter.put("loginPassword","123");

        //允许谁可以访问
        initParameter.put("allow", "");

        //禁止谁能访问 initParameter.put("ccq", "192.168.11")


        //设置初始化参数
        bean.setInitParameters(initParameter);
        return bean;
    }

    //filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();

        bean.setFilter(new WebStatFilter());
        //可以过滤哪些请求
        Map<String, String> initParameters = new HashMap<>();

        //这些东西不进行统计
        initParameters.put("excelusion", "*.js, *.css, /druid/*");

        bean.setInitParameters(initParameters);
        return bean;
    }

}
