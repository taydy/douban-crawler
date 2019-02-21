package com.crawler.douban.service;

import com.alibaba.fastjson.JSONObject;
import com.crawler.douban.entry.Statuses;
import com.crawler.douban.parser.StatusesBeanFactory;
import com.google.common.collect.Lists;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/16
 */
@Service
public class StatusesParseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusesParseService.class);

    public List<Statuses> parse(String html) {
        Document document = Jsoup.parse(html);
        Elements contents = Optional.ofNullable(document.getElementById("content"))
                .map(entry -> entry.getElementsByClass("article").first())
                .map(entry -> entry.getElementsByClass("stream-items").first())
                .map(entry -> entry.getElementsByClass("new-status"))
                .orElse(null);
        if (contents == null || contents.size() == 0) {
            return null;
        }

        List<Statuses> statusesList = Lists.newArrayList();
        int i = 1;
        for (Element content : contents) {
            Optional<Element> optionalElement = Optional.ofNullable(content);
            Optional<String> dataAtype = optionalElement
                    .map(entry -> entry.attr("data-atype"))
                    .map(entry -> StringUtils.isEmpty(entry) ? null : entry);
            Optional<Element> statusItem = optionalElement.map(entry -> entry.getElementsByClass("status-item").first());
            Optional<String> dataTargetType = statusItem
                    .map(entry -> entry.attr("data-target-type"))
                    .map(entry -> StringUtils.isEmpty(entry) ? null : entry);
            Optional<String> dataObjectKind = statusItem
                    .map(entry -> entry.attr("data-object-kind"))
                    .map(entry -> StringUtils.isEmpty(entry) ? null : entry);
            Statuses statuse = null;
            if (dataTargetType.isPresent()) {
                statuse = StatusesBeanFactory.getBeanByType(dataTargetType.get()).parse(optionalElement);
            }
            if (statuse == null && dataObjectKind.isPresent()) {
                statuse = StatusesBeanFactory.getBeanByType(dataObjectKind.get()).parse(optionalElement);
            }
            if (statuse == null && dataAtype.isPresent()) {
                statuse = StatusesBeanFactory.getBeanByType(dataAtype.get()).parse(optionalElement);
            }
            if (statuse == null) {
                statuse = StatusesBeanFactory.getBeanByType("default").parse(optionalElement);
            }
            if (Objects.isNull(statuse)) {
                LOGGER.info(" ------------------------ 第 {} 条数据无法解析", i);
                continue;
            }
            statusesList.add(statuse);
        }

        return statusesList;
    }


}
