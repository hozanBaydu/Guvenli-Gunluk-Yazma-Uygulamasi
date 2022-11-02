package com.hozanbaydu.adobe;

import java.io.Serializable;

public class Model implements Serializable {
    public String name;
    public String password;
    public String email;


    public Model(String name, String password,String email) {
        this.name = name;
        this.password = password;
        this.email=email;

    }
}
