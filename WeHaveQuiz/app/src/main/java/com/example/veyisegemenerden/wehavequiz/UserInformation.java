package com.example.veyisegemenerden.wehavequiz;

/**
 * Created by veyisegemenerden on 22.12.2016.
 */


public class UserInformation {


    private String name;
    private String surname;
    private String email;
    private String sex;

    public UserInformation() {
    }

    public UserInformation(String name, String surname, String email, String sex) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }



}
