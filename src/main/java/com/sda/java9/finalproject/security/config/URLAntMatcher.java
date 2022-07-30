package com.sda.java9.finalproject.security.config;

import org.springframework.stereotype.Component;

@Component
public class URLAntMatcher {
    public static final String[] RESTRICTED_ENDPOINTS = {"/bookings/**", "/admin/**", "/passengers/**"};
    public static final String[] ALLOWED_ENDPOINTS = {"/news/**", "/airports/**", "/api/**", "/search/**", "/flights/**"};
}
