package com.noideaindustry.isartbot.utils;

public class StringUtils {
    public static String capitalize(final String str){
        final String[] words = str.split("\\s");

        StringBuilder capitalizeWord= new StringBuilder();
        for(final String w : words){
            final String first = w.substring(0,1);
            final String afterfirst = w.substring(1);
            capitalizeWord.append(first.toUpperCase()).append(afterfirst).append(" ");
        }
        return capitalizeWord.toString().trim();
    }
}
