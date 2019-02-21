package com.crawler.douban.service.parser;

import com.crawler.douban.entry.Attachment;
import com.crawler.douban.entry.Block;
import com.crawler.douban.entry.builder.statuses.BasicInfoBuilder;
import com.crawler.douban.parser.BlockBeanFactory;
import com.crawler.douban.parser.bd.AttachmentParseService;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
public class BasicInfoParseService {

    public void parseType(Optional<Element> root, BasicInfoBuilder basicInfoBuilder) {
        String type = root
                .map(entry -> entry.getElementsByClass("status-item").first())
                .map(entry -> entry.attr("data-target-type"))
                .map(entry -> StringUtils.isEmpty(entry) ? null : entry)
                .orElseGet(() -> root
                        .map(entry -> entry.getElementsByClass("status-item").first())
                        .map(entry -> entry.attr("data-object-kind"))
                        .map(entry -> StringUtils.isEmpty(entry) ? null : entry)
                        .orElseGet(() -> root
                                .map(entry -> entry.attr("data-atype"))
                                .map(entry -> StringUtils.isEmpty(entry) ? null : entry)
                                .orElse(null)));
        basicInfoBuilder.type(type);
    }

    public void parseUser(Optional<Element> hd, BasicInfoBuilder basicInfoBuilder) {
        String avatar = hd
                .map(entry -> entry.getElementsByClass("usr-pic").first())
                .map(entry -> entry.getElementsByTag("a").first())
                .map(entry -> entry.getElementsByTag("img").first())
                .map(entry -> entry.attr("src"))
                .orElse(null);
        basicInfoBuilder.userAvatar(avatar);

        Optional<Element> text = hd.map(entry -> entry.getElementsByClass("text").first());
        // nickname
        String nickname = text
                .map(entry -> entry.getElementsByTag("a").first())
                .map(Element::text)
                .orElse(null);
        // verb
        String verb = text
                .map(entry -> entry.childNode(2))
                .map(Node::toString)
                .orElse(null);
        basicInfoBuilder
                .userNickname(nickname)
                .verb(verb.trim());

    }

    public void parseUrl(Optional<Element> hd, BasicInfoBuilder basicInfoBuilder) {
        Optional<String> url = hd.map(entry -> entry.attr("data-status-url"));
        if (url.isPresent()) {
            basicInfoBuilder.url(url.get());
        }
    }

    public void parseAction(Optional<Element> actions, BasicInfoBuilder basicInfoBuilder) {
        Long createdAt = actions
                .map(entry -> entry.getElementsByClass("created_at").first())
                .map(entry -> entry.attr("title"))
                .map(entry -> entry.replaceFirst(" ", "T"))
                .map(entry -> LocalDateTime.parse(entry).toEpochSecond(ZoneOffset.ofHours(+8)))
                .orElse(null);
        Integer reply = Integer.parseInt(actions
                .map(entry -> entry.getElementsByClass("new-reply").first())
                .map(entry -> entry.attr("data-count"))
                .orElse(String.valueOf(basicInfoBuilder.getReply())));
        Integer like = Integer.parseInt(actions
                .map(entry -> entry.getElementsByClass("count like-count").first())
                .map(entry -> entry.attr("data-count"))
                .orElse(String.valueOf(basicInfoBuilder.getLike())));
        Integer reshared = Integer.parseInt(actions
                .map(entry -> entry.getElementsByClass("count reshared-count").first())
                .map(entry -> entry.attr("data-count"))
                .orElse(String.valueOf(basicInfoBuilder.getResharded())));
        String url = actions
                .map(entry -> entry.getElementsByClass("btn-key-like").first())
                .map(entry -> entry.attr("href"))
                .orElse(String.valueOf(basicInfoBuilder.getUrl()));

        basicInfoBuilder
                .createdAt(createdAt)
                .actionReply(reply)
                .actionLike(like)
                .actionReshared(reshared)
                .url(url);
    }

    public void parseBlock(Optional<Element> bd, BasicInfoBuilder basicInfoBuilder) {
        String bdType = bd.get().classNames().stream().filter(className -> !className.trim().equals("bd")).findFirst().orElse(null);
        Optional<Element> block = bd.map(entry -> entry.getElementsByAttributeValueMatching("class", "block").first());
        if (block.isPresent()) {
            int offset = -1;
            for (String className : block.get().classNames()) {
                if ((offset = className.indexOf("-block")) > -1) {
                    String type = className.substring(0, offset);
                    Block target = BlockBeanFactory.getBeanByType(type).parse(block);
                    basicInfoBuilder.block(target);
                    break;
                }
                if ((offset = className.indexOf("block-")) > -1) {
                    String type = className.substring(6);
                    if (type.equals("subject")) {
                        offset = -1;
                        continue;
                    }
                    Block target = BlockBeanFactory.getBeanByType(type).parse(block);
                    basicInfoBuilder.block(target);
                    break;
                }
            }
            if (offset == -1) {
                Block target = BlockBeanFactory.getBeanByType(bdType).parse(block);
                basicInfoBuilder.block(target);
            }
        } else {
            Optional<Element> attachments = bd.map(entry -> entry.getElementsByClass("attachments").first());
            if (attachments.isPresent()) {
                Block target = new AttachmentParseService().parse(attachments);
                basicInfoBuilder.block(target);
            }
        }
    }

}
