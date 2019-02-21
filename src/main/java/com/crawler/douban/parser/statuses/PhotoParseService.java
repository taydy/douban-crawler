package com.crawler.douban.parser.statuses;

import com.crawler.douban.entry.Statuses;
import com.crawler.douban.entry.builder.statuses.StatusesPhotoBuilder;
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
@StatusesType("photo")
public class PhotoParseService extends BasicInfoParseService implements ParseService {

    @Override
    public Statuses parse(Optional<Element> root) {
        StatusesPhotoBuilder statusesPhotoBuilder = new StatusesPhotoBuilder();

        parseType(root, statusesPhotoBuilder);

        Optional<Element> statusItem = root.map(entry -> entry.getElementsByClass("status-item").first());

        Optional<Element> mod = statusItem.map(entry -> entry.getElementsByClass("mod").first());
        Optional<Element> hd = mod.map(entry -> entry.getElementsByClass("hd").first());
        parseUrl(hd, statusesPhotoBuilder);
        parseUser(hd, statusesPhotoBuilder);

        Optional<Element> bd = mod.map(entry -> entry.getElementsByClass("bd").first());
        parseBlock(bd, statusesPhotoBuilder);

        Optional<Element> actions = bd.map(entry -> entry.getElementsByClass("actions").first());
        parseAction(actions, statusesPhotoBuilder);

        return statusesPhotoBuilder.build();
    }

}
