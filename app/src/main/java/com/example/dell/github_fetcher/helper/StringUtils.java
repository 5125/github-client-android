package com.example.dell.github_fetcher.helper;

public class StringUtils {

    public static String[] splitByDelim(final String inputString) {
        String[] def = "Error-IsString".split("-");
        if (inputString != null && inputString.contains("/")) {
            String[] outStr;
            outStr = inputString.split("/");
            return outStr;
        }
        return def;
    }

}
