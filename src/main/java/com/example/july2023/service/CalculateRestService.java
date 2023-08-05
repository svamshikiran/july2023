package com.example.july2023.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;

@Service
public class CalculateRestService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${calculate.add.url}")//to pick up the value from property file and assign to variable
    private String calculateAddUrl;

    //private String calculateAddUrl = "http://localhost:9010/march2023/calculate/add/12/23";

    public double calculateAddtion(double first, double second) {

        HttpEntity<String> entity = new HttpEntity<String>(createHeader());

        ResponseEntity<Object> responseEntity =
                restTemplate.exchange(calculateAddUrl+"/"+first+"/"+second, HttpMethod.GET, entity, Object.class);

        return (Double) responseEntity.getBody();
    }

    private HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //Authentication details
        String authentication = "user:user"; // username + ":" + password
        byte[] encodeAuth = Base64.getEncoder().encode(authentication.getBytes());
        String authenticationHeader = "Basic "+new String(encodeAuth);

        headers.set("Authorization", authentication);

        return headers;
    }
}
