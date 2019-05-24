package com.kostiantynd.google.indexing.util;

public class UrlUtil {

    public static String ridQueryParams(String url) {
        int endPos;

        if (url.indexOf("?") > 0) {
            endPos = url.indexOf("?");
        } else if (url.indexOf("#") > 0) {
            endPos = url.indexOf("#");
        } else {
            endPos = url.length();
        }

        return url.substring(0, endPos);
    }
}
