package com.github.owgrant24.springbootone.util;

public class TextReplacer {

    private TextReplacer() {
    }

    public static String emailCrypt(String text) {
        int beginIndex = text.indexOf('@');
        StringBuilder sb = new StringBuilder(text.substring(beginIndex));
        sb.insert(0, "***");
        return sb.toString();
    }

}
