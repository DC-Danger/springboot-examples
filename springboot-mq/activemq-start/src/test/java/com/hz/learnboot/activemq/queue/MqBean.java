package com.hz.learnboot.activemq.queue;

import java.io.Serializable;

/**
 * 消息对象
 *
 * @Author hezhao
 * @Time 2018-07-24 23:52
 */
public class MqBean implements Serializable {

    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MqBean{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
