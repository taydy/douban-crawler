package com.crawler.douban.service;

import com.alibaba.fastjson.JSONObject;
import com.crawler.douban.entry.Certification;
import com.crawler.douban.entry.Statuses;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/16
 */
@Service
public class TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private Certification certification;

    @Value("${http.user-agent}")
    private String userAgent;

    @Value("${com.crawler.target.path}")
    private String targetFile;

    @Value("${com.crawler.target.html.path}")
    private String htmlFile;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoginService loginService;

    @Autowired
    private StatusesParseService statusesParseService;

    public void crawle(String personal) throws InterruptedException, IOException {

        File target = new File(String.format(targetFile, personal, personal));
        if (!target.exists()) {
            FileUtils.forceMkdirParent(target);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(target, false))) {
            for (int i = 1; ; i++) {
                LOGGER.info("正在解析第 " + i + " 页数据");

                String body = null;

                File origin = new File(String.format(htmlFile, personal, personal, i));
                if (origin.isFile()) {
                    body = String.join("", Files.readLines(origin, Charsets.UTF_8));
                } else {
                    body = getStatusesBody(personal, i);

                    // 保存请求的 html
                    File temp = new File(String.format(htmlFile, personal, personal, i));
                    FileUtils.forceMkdirParent(temp);
                    Files.write(body, temp, Charsets.UTF_8);

                    Thread.sleep(2000);
                }

                List<Statuses> tempStatuses = statusesParseService.parse(body);

                if (Objects.isNull(tempStatuses)) {
                    return;
                }
                if (!CollectionUtils.isEmpty(tempStatuses)) {
                    for (Statuses statuses : tempStatuses) {
                        writer.write(JSONObject.toJSONString(statuses));
                        writer.newLine();
                    }
                }
            }
        }
    }

    public String getStatusesBody(String personal, int page) {
        if (StringUtils.isEmpty(certification.getToken())) {
            certification.setToken(loginService.loginDouban());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.USER_AGENT, userAgent);
        headers.add(HttpHeaders.COOKIE, certification.getToken());

        Map<String, Object> urlMap = new HashMap<>();
        urlMap.put("personal", personal);
        urlMap.put("page", page);

        ResponseEntity responseEntity = restTemplate.exchange(certification.getDomain() + certification.getStatuses(), HttpMethod.GET, new HttpEntity<>(headers), String.class, urlMap);

        String body = responseEntity.getBody().toString();
        return body;
    }

}
