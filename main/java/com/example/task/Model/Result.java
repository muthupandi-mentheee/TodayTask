package com.example.task.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {
    String name;
    String email;
    String ddress;
    String phoneno;

    public String getDdress() {
        return ddress;
    }

    public void setDdress(String ddress) {
        this.ddress = ddress;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Result(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Result(String name, String email, String ddress, String phoneno) {
        this.name = name;
        this.email = email;
        this.ddress = ddress;
        this.phoneno = phoneno;
    }
}
