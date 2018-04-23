package com.dengqi.properties.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @description
 * @date 2018-04-23
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "user")
public class UserProperties {
    private long id;
    private int age;
    private String desc;
    private String uuid;

    @Override
    public String toString() {
        return "UserProperties{" +
                "id=" + id +
                ", age=" + age +
                ", desc='" + desc + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
