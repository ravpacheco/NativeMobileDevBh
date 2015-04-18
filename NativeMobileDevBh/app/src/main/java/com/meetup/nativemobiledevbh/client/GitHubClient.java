package com.meetup.nativemobiledevbh.client;

import com.meetup.nativemobiledevbh.User;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.RequiresHeader;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = "https://api.github.com",
        converters = {MappingJackson2HttpMessageConverter.class},
        requestFactory = DefaultRequestFactory.class)
public interface GitHubClient {

        @Get("/users/{userName}")
        @Accept(MediaType.APPLICATION_JSON)
        //@RequiresHeader()
        User getAccount(String userName);

}
