package org.example.utils;

import com.github.javafaker.Faker;

public class FakerUtil {

    static Faker faker;

    public static String getUseName(){
        faker=new Faker();
        String name=faker.name().fullName();
        return name;
    }
}
