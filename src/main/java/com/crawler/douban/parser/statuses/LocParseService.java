package com.crawler.douban.parser.statuses;

import com.crawler.douban.entry.Statuses;
import com.crawler.douban.entry.builder.statuses.StatusesLocBuilder;
import com.crawler.douban.parser.StatusesType;
import com.crawler.douban.service.parser.BasicInfoParseService;
import com.crawler.douban.service.parser.ParseService;

import org.jsoup.nodes.Element;

import java.util.Optional;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/21
 */
@StatusesType("loc")
public class LocParseService extends BasicInfoParseService implements ParseService {

    @Override
    public Statuses parse(Optional<Element> root) {
        StatusesLocBuilder statusesLocBuilder = new StatusesLocBuilder();

        parseType(root, statusesLocBuilder);

        Optional<Element> statusItem = root.map(entry -> entry.getElementsByClass("status-item").first());

        Optional<Element> mod = statusItem.map(entry -> entry.getElementsByClass("mod").first());
        Optional<Element> hd = mod.map(entry -> entry.getElementsByClass("hd").first());
        parseUrl(hd, statusesLocBuilder);
        parseUser(hd, statusesLocBuilder);

        Optional<Element> text = hd.map(entry -> entry.getElementsByClass("text").first());
        Optional<Element> blockquote = text
                .map(entry -> entry.getElementsByTag("blockquote").first());

        String ratingStars = blockquote
                .map(entry -> entry.getElementsByClass("rating-stars").first())
                .map(Element::text)
                .orElse(null);
        String comment = blockquote
                .map(entry -> entry.getElementsByTag("p").first())
                .map(Element::text)
                .orElse(null);
        statusesLocBuilder.ratingStars(ratingStars).comment(comment);

        Optional<Element> bd = mod.map(entry -> entry.getElementsByClass("bd").first());
        parseBlock(bd, statusesLocBuilder);

        Optional<Element> actions = bd.map(entry -> entry.getElementsByClass("actions").first());
        parseAction(actions, statusesLocBuilder);

        return statusesLocBuilder.build();
    }

}
