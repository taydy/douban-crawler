package com.crawler.douban.service.parser;

import com.crawler.douban.entry.Statuses;

import org.jsoup.nodes.Element;

import java.util.Optional;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
public interface ParseService {

    Statuses parse(Optional<Element> root);

}
