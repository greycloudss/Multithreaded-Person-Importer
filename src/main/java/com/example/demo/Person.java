package com.example.demo;

import java.util.ArrayList;

public class Person {
    private final String id;
    private final String first_name;
    private final String last_name;
    private final String email;
    private final String gender;
    private final String country;
    private final String domain;
    private final String birth_date;

    public Person(String id, String first_name, String last_name, String email, String gender, String country, String domain, String birth_date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.domain = domain;
        this.birth_date = birth_date;
    }
    public String getId() {
        return id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public String getEmail() {
        return email;
    }
    public String getGender() {
        return gender;
    }
    public String getCountry() {
        return country;
    }
    public String getDomain() {
        return domain;
    }
    public String getBirth_date() {
        return birth_date;
    }

    public void printData() {
        System.out.printf("%s %s %s %s %s %s %s %s%n", id, first_name, last_name, email, gender, country, domain, birth_date);
    }
}
