package org.example.controller.bean;
// User类

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
@Data
@RedisHash("users")
public class User implements Serializable {
    private static final long serialVersionUID = -4413465130871644046L;

    private String id;
    private String username;
    private int age;
    private String password;

    public User(String username, int age,String password) {
        this.id = username+"_"+age;
        this.username = username;
        this.age = age;
        this.password = password;
    }

}
