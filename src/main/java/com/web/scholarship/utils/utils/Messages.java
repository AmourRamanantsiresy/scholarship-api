package com.web.scholarship.utils.utils;
public class Messages {
    public static String idNotFound (Long id){
        return new StringBuilder("The applications with the id ")
                .append(id)
                .append(" not found")
                .toString();
    }
}
