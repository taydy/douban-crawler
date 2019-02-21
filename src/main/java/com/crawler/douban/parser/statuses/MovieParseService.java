package com.crawler.douban.parser.statuses;

import com.crawler.douban.entry.Statuses;
import com.crawler.douban.entry.builder.statuses.StatusesMovieBuilder;
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
@StatusesType("movie")
public class MovieParseService extends BasicInfoParseService implements ParseService {

    @Override
    public Statuses parse(Optional<Element> root) {
        StatusesMovieBuilder statusesMovieBuilder = new StatusesMovieBuilder();

        parseType(root, statusesMovieBuilder);

        Optional<Element> statusItem = root.map(entry -> entry.getElementsByClass("status-item").first());

        Optional<Element> mod = statusItem.map(entry -> entry.getElementsByClass("mod").first());
        Optional<Element> hd = mod.map(entry -> entry.getElementsByClass("hd").first());
        parseUrl(hd, statusesMovieBuilder);
        parseUser(hd, statusesMovieBuilder);

        Optional<Element> text = hd.map(entry -> entry.getElementsByClass("text").first());
        Optional<Element> quoteClamp = text
                .map(entry -> entry.getElementsByClass("quote-clamp").first());

        String ratingStars = quoteClamp
                .map(entry -> entry.getElementsByClass("rating-stars").first())
                .map(Element::text)
                .orElse(null);
        String comment = quoteClamp
                .map(entry -> entry.getElementsByTag("p").first())
                .map(Element::text)
                .orElse(null);
        statusesMovieBuilder.ratingStars(ratingStars).comment(comment);

        Optional<Element> bd = mod.map(entry -> entry.getElementsByClass("bd").first());
        parseBlock(bd, statusesMovieBuilder);

        Optional<Element> actions = bd.map(entry -> entry.getElementsByClass("actions").first());
        parseAction(actions, statusesMovieBuilder);

        return statusesMovieBuilder.build();
    }

}
