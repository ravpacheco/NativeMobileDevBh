package com.meetup.nativemobiledevbh.client;

import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = "https://api.github.com",
        converters = {MappingJackson2HttpMessageConverter.class},
        requestFactory = DefaultRequestFactory.class)
public interface GitHubClient {

        //@Get("/users/{userName}")
        //@Accept(MediaType.APPLICATION_JSON)
        //@RequiresHeader(Constants.SESSION_ID_HEADER)
        //User getAccount(String userName);

}
