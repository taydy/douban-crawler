package com.crawler.douban.parser.statuses;

import com.crawler.douban.entry.Statuses;
import com.crawler.douban.entry.builder.statuses.StatusesNoteBuilder;
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
@StatusesType("default")
public class DefaultParseService extends BasicInfoParseService implements ParseService {

    @Override
    public Statuses parse(Optional<Element> root) {
        StatusesNoteBuilder statusesNoteBuilder = new StatusesNoteBuilder();
        parseType(root, statusesNoteBuilder);

        Optional<Element> statusItem = root.map(entry -> entry.getElementsByClass("status-item").first());

        Optional<Element> mod = statusItem.map(entry -> entry.getElementsByClass("mod").first());
        Optional<Element> hd = mod.map(entry -> entry.getElementsByClass("hd").first());
        parseUrl(hd, statusesNoteBuilder);
        parseUser(hd, statusesNoteBuilder);

        Optional<Element> text = hd.map(entry -> entry.getElementsByClass("text").first());
        Optional<Element> blockquote = text
                .map(entry -> entry.getElementsByTag("blockquote").first());

        String comment = blockquote
                .map(entry -> entry.getElementsByTag("p").first())
                .map(Element::text)
                .orElse(null);

        statusesNoteBuilder.content(comment);

        Optional<Element> bd = mod.map(entry -> entry.getElementsByClass("bd").first());
        parseBlock(bd, statusesNoteBuilder);

        Optional<Element> actions = bd.map(entry -> entry.getElementsByClass("actions").first());
        parseAction(actions, statusesNoteBuilder);

        return statusesNoteBuilder.build();
    }

}
