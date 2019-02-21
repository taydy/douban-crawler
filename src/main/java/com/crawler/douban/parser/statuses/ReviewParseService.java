package com.crawler.douban.parser.statuses;

import com.crawler.douban.entry.Statuses;
import com.crawler.douban.entry.builder.statuses.StatusesReviewBuilder;
import com.crawler.douban.parser.StatusesType;
import com.crawler.douban.service.parser.BasicInfoParseService;
import com.crawler.douban.service.parser.ParseService;

import org.jsoup.nodes.Element;

import java.util.Optional;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
@StatusesType({"review"})
public class ReviewParseService extends BasicInfoParseService implements ParseService {

    @Override
    public Statuses parse(Optional<Element> root) {
        StatusesReviewBuilder statusesReviewBuilder = new StatusesReviewBuilder();

        parseType(root, statusesReviewBuilder);

        Optional<Element> statusItem = root.map(entry -> entry.getElementsByClass("status-item").first());

        Optional<Element> mod = statusItem.map(entry -> entry.getElementsByClass("mod").first());
        Optional<Element> hd = mod.map(entry -> entry.getElementsByClass("hd").first());

        parseUrl(hd, statusesReviewBuilder);
        parseUser(hd, statusesReviewBuilder);

        Optional<Element> bd = mod.map(entry -> entry.getElementsByClass("bd").first());

        String img = bd
                .map(entry -> entry.getElementsByClass("article-pic").first())
                .map(entry -> entry.getElementsByClass("pic-wrap").first())
                .map(entry -> entry.getElementsByTag("a").first())
                .map(entry -> entry.getElementsByTag("img").first())
                .map(entry -> entry.attr("src"))
                .orElse(null);
        statusesReviewBuilder.picture(img);

        Optional<Element> contentElement = bd
                .map(entry -> entry.getElementsByClass("content").first());

        String title = contentElement
                .map(entry -> entry.getElementsByClass("title").first())
                .map(Element::text)
                .orElse(null);
        String content = contentElement
                .map(entry -> entry.getElementsByTag("p").first())
                .map(Element::text)
                .orElse(null);
        statusesReviewBuilder.title(title).content(content);

        Optional<Element> actions = bd.map(entry -> entry.getElementsByClass("actions").first());
        parseAction(actions, statusesReviewBuilder);
        return statusesReviewBuilder.build();
    }

}
