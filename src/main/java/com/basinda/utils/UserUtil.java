package com.basinda.utils;

import jakarta.servlet.http.HttpServletRequest;

public class UserUtil {
    public static String getApplicationUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return url.replace(request.getServletPath(), "");
    }
}