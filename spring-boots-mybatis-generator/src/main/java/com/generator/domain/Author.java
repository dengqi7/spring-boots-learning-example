package com.generator.domain;

public class Author {
    private Integer id;

    private String name;

    private Integer age;

    private Integer homeCityId;

    public Author(Integer id, String name, Integer age, Integer homeCityId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.homeCityId = homeCityId;
    }

    public Author() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHomeCityId() {
        return homeCityId;
    }

    public void setHomeCityId(Integer homeCityId) {
        this.homeCityId = homeCityId;
    }
}