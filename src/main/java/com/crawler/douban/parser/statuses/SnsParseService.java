package com.crawler.douban.parser.statuses;

import com.crawler.douban.entry.Statuses;
import com.crawler.douban.entry.builder.statuses.StatusesSnsBuilder;
import com.crawler.douban.parser.StatusesType;
import com.crawler.douban.service.parser.BasicInfoParseService;
import com.crawler.douban.service.parser.ParseService;
import com.google.common.collect.Lists;

import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Optional;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
@StatusesType("sns")
public class SnsParseService extends BasicInfoParseService implements ParseService {

    @Override
    public Statuses parse(Optional<Element> root) {
        StatusesSnsBuilder statusesSnsBuilder = new StatusesSnsBuilder();

        parseType(root, statusesSnsBuilder);

        Optional<Element> statusItem = root.map(entry -> entry.getElementsByClass("status-item").first());

        Optional<Element> mod = statusItem.map(entry -> entry.getElementsByClass("mod").first());
        Optional<Element> hd = mod.map(entry -> entry.getElementsByClass("hd").first());
        parseUrl(hd, statusesSnsBuilder);
        parseUser(hd, statusesSnsBuilder);

        Optional<Element> bd = mod.map(entry -> entry.getElementsByClass("bd").first());
        Optional<Element> statusSaying = bd.map(entry -> entry.getElementsByClass("status-saying").first());
        String content = statusSaying
                .map(entry -> entry.getElementsByClass("quote-clamp").first())
                .map(entry -> entry.getElementsByTag("p").first())
                .map(Element::text)
                .orElse(null);
        statusesSnsBuilder.content(content);

        List<String> imgs = statusSaying
                .map(entry -> entry.getElementsByClass("attachments-saying group-pics").first())
                .map(entry -> entry.getElementsByTag("img"))
                .map(entry -> entry.eachAttr("src"))
                .orElse(Lists.newArrayList());

        statusesSnsBuilder.picture(imgs);

        parseBlock(bd, statusesSnsBuilder);

        Optional<Element> actions = bd.map(entry -> entry.getElementsByClass("actions").first());
        parseAction(actions, statusesSnsBuilder);

        return statusesSnsBuilder.build();
    }

}
