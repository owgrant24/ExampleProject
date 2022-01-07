package com.github.owgrant24.springbootone.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TextReplacer {

    public static String emailCrypt(String text) {
        int beginIndex = text.indexOf('@');
        StringBuilder sb = new StringBuilder(text.substring(beginIndex));
        sb.insert(0, "***");
        return sb.toString();
    }

}
