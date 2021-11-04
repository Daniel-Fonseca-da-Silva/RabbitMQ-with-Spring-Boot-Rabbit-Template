package com.project.rabbit.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class People implements Serializable {
    private Long id;
    private String name;

    public People() {}

    public People(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
