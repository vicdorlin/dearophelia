package com.vic.dearophelia.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vicdor
 * @create 2018-02-11 12:23
 */
@Getter
@Setter
public class User {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
