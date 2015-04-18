package com.meetup.nativemobiledevbh.client;

import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = "https://api.github.com",
        converters = {MappingJackson2HttpMessageConverter.class})
public interface GitHubClient {



}
