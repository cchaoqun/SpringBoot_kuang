package com.kuang.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/27-16:28
 */

@Component
@ConfigurationProperties(prefix="dog")
public class Dog {

    private String firstName;
    private Integer age;

    public Dog() {
    }

    public Dog(String firstName, Integer age) {
        this.firstName = firstName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
