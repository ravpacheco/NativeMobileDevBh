package com.meetup.nativemobiledevbh.client;

import org.androidannotations.annotations.rest.Rest;

@Rest(rootUrl = "https://api.github.com",
        converters = {MappingJackson2HttpMessageConverter.class},
        requestFactory = DefaultRequestFactory.class)
public interface GitHubClient {



}
