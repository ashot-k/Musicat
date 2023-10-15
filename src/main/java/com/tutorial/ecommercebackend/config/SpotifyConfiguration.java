package com.tutorial.ecommercebackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;

import java.net.URI;

@Service
public class SpotifyConfiguration {
    @Value("${redirect.server.ip")
    private String customIp;
    public SpotifyApi getSpotifyObject(){
        URI redirectedURL = SpotifyHttpManager.makeUri(customIp + "/api/get-user-code/");
        return new SpotifyApi
                .Builder()
                .setClientId("2589aeb7464143cdaca99be59934136c")
                .setClientSecret("c6d6314534e24a81902e7784a53c0c27")
                .setRedirectUri(redirectedURL)
                .build();
    }
}
