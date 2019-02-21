package com.crawler.douban.parser.statuses;

import com.crawler.douban.entry.Statuses;
import com.crawler.douban.entry.builder.statuses.StatusesForwardBuilder;
import com.crawler.douban.parser.StatusesBeanFactory;
import com.crawler.douban.parser.StatusesType;
import com.crawler.douban.service.parser.BasicInfoParseService;
import com.crawler.douban.service.parser.ParseService;

import org.jsoup.nodes.Element;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
@StatusesType({"doulist", "rec", "1018", "1015", "1022", "1060"})
public class ForwardParseService extends BasicInfoParseService implements ParseService {

    @Override
    public Statuses parse(Optional<Element> root) {
        StatusesForwardBuilder statusesForwardBuilder = new StatusesForwardBuilder();

        parseType(root, statusesForwardBuilder);

        Optional<Element> statusItem = root.map(entry -> entry.getElementsByClass("status-item").first());

        Optional<Element> mod = statusItem.map(entry -> entry.getElementsByClass("mod").first());
        Optional<Element> hd = mod.map(entry -> entry.getElementsByClass("hd").first());

        parseUrl(hd, statusesForwardBuilder);
        parseUser(hd, statusesForwardBuilder);

        Optional<Element> text = hd.map(entry -> entry.getElementsByClass("text").first());
        // 判断是否有专门的 blockquote
        Optional<Element> blockquote = hd
                .map(entry -> entry.getElementsByTag("blockquote").first());
        if (!blockquote.isPresent()) {
            blockquote = text.map(entry -> entry.getElementsByTag("blockquote").first());
        }

        String content = blockquote
                .map(Element::text)
                .orElse(null);
        statusesForwardBuilder.content(content);

        Optional<Element> bd = mod.map(entry -> entry.getElementsByClass("bd").first());
        Optional<Element> actions = bd.map(entry -> entry.getElementsByClass("actions").first());
        parseAction(actions, statusesForwardBuilder);

        Optional<Element> realWrapper = root.map(entry -> entry.getElementsByClass("status-real-wrapper").first());
        if (realWrapper.isPresent() && !realWrapper.equals(root)) {
            String kind = realWrapper
                    .map(entry -> entry.getElementsByClass("status-item").first())
                    .map(entry -> entry.attr("data-target-type"))
                    .map(entry -> StringUtils.isEmpty(entry) ? null : entry)
                    .orElseGet(() -> realWrapper
                            .map(entry -> entry.getElementsByClass("status-item").first())
                            .map(entry -> entry.getElementsByClass("status-item").first())
                            .map(entry -> entry.attr("data-object-kind"))
                            .orElse(null));
            Statuses statuses = StatusesBeanFactory.getBeanByType(kind).parse(realWrapper);
            statusesForwardBuilder.addOrigin(statuses);
        }

        parseBlock(bd, statusesForwardBuilder);

        return statusesForwardBuilder.build();
    }

}
