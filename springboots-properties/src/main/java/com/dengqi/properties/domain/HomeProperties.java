package com.dengqi.properties.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description
 * @author
 * @date
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "home")
public class HomeProperties {
    private String provance;
    private String city;
    private String desc;

    @Override
    public String toString() {
        return "HomeProperties:{" +
                "provance = " + provance +
                ",city = " + city +
                ",desc = " + desc +
                "}";
    }
}
