package com.framework.core.http;

import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpHandler {

    public static RestResponse post(RestRequest request){

        RestResponse restResponse = new RestResponse();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(request.getUrl(), HttpMethod.POST,
                request.getBody(), String.class);

        restResponse.setStatusCode(response.getStatusCodeValue());

        if(response.hasBody()) {

            restResponse.setContent(new JSONObject(response.getBody()));
        }
        return restResponse;
    }

    public static RestResponse get(RestRequest request){

        RestResponse restResponse = new RestResponse();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(request.getUrl(), HttpMethod.GET,
                request.getBody(), String.class);

        restResponse.setStatusCode(response.getStatusCodeValue());

        if(response.hasBody()) {

            restResponse.setContent(new JSONObject(response.getBody()));
        }
        return restResponse;
    }

    public static RestResponse delete(RestRequest request){

        RestResponse restResponse = new RestResponse();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(request.getUrl(), HttpMethod.DELETE,
                request.getBody(), String.class);

        restResponse.setStatusCode(response.getStatusCodeValue());

        if(response.hasBody()) {

            restResponse.setContent(new JSONObject(response.getBody()));
        }
        return restResponse;
    }
}
