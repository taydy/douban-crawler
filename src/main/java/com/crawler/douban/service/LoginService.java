package com.crawler.douban.service;

import com.alibaba.fastjson.JSONObject;
import com.crawler.douban.entry.Certification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/15
 */
@Service
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private Certification certification;

    @Value("${http.user-agent}")
    private String userAgent;

    @Autowired
    private RestTemplate restTemplate;

    public String loginDouban() {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.USER_AGENT, userAgent);

        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("name", certification.getUsername());
        requestEntity.add("password", certification.getPassword());
        requestEntity.add("remember", certification.getRemember());

        ResponseEntity responseEntity = restTemplate.postForEntity(certification.getLoginUrl(), new HttpEntity<>(requestEntity, headers), Object.class);
        List<String> cookies = responseEntity.getHeaders().get("Set-Cookie");
        cookies.forEach(LOGGER::info);
        for (String cookie : cookies.stream().filter(cookie -> cookie.startsWith("dbcl2")).collect(Collectors.toList())) {
            return cookie.substring(0, cookie.indexOf(";"));
        }
        return null;
    }

}
