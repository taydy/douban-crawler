package com.crawler.douban.parser.bd;

import com.crawler.douban.entry.Attachment;
import com.crawler.douban.entry.Block;
import com.crawler.douban.entry.builder.bd.AttachmentBuilder;
import com.crawler.douban.parser.block.BlockParseService;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

/**
 * 兼容旧数据.
 *
 * @author taydy
 * @date 2019/2/21
 */
public class AttachmentParseService implements BlockParseService {

    @Override
    public Block parse(Optional<Element> attachments) {
        AttachmentBuilder attachmentBuilder = new AttachmentBuilder();

        Optional<Element> media = attachments.map(entry -> entry.getElementsByTag("a").first());
        String url = media
                .map(entry -> entry.attr("href"))
                .orElse(null);
        String image = media
                .map(entry -> entry.getElementsByTag("img"))
                .map(entry -> entry.attr("src"))
                .orElse(null);
        String title = attachments
                .map(entry -> entry.getElementsByTag("h6"))
                .map(Elements::text)
                .orElse(null);
        String description = attachments
                .map(entry -> entry.getElementsByClass("description"))
                .map(Elements::text)
                .orElse(null);

        attachmentBuilder
                .content(description)
                .picture(image)
                .title(title)
                .url(url);

        return attachmentBuilder.build();
    }

}
