package com.github.owgrant24.springbootone;

public class Help {
    public static void main(String[] args) {
        String email = "bangladesh@mail.ru";
        String emailSecret = new StringBuilder(email.substring(email.indexOf('@'))).insert(0,"***").toString();
        System.out.println(emailSecret);
    }
}
