package com.meetup.nativemobiledevbh.client;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * Inherits from HttpComponentsClientHttpRequestFactory in order to
 * fix for 'ResourceAccessException: I/O error: null; nested exception is java.io.EOFException'
 * as described in http://sapandiwakar.in/eofexception-with-spring-rest-template-android/
 */
public class DefaultRequestFactory extends HttpComponentsClientHttpRequestFactory {
    private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = (1000 * 1000);

    public DefaultRequestFactory() {
        super();
        System.setProperty("http.keepAlive", "false");
        setReadTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS);
    }
}

