package com.crawler.douban.parser.block;

import com.crawler.douban.entry.Block;
import com.crawler.douban.entry.builder.block.BlockBuilder;
import com.crawler.douban.parser.BlockType;

import org.jsoup.nodes.Element;

import java.util.Optional;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
@BlockType({"note", "url"})
public class DefaultBlockParseService implements BlockParseService {
    @Override
    public Block parse(Optional<Element> block) {
        BlockBuilder blockBuilder = new BlockBuilder();

        Optional<Element> pic = block.map(entry -> entry.getElementsByClass("pic").first());
        String picture = pic
                .map(entry -> entry.getElementsByClass("pic-wrap").first())
                .map(entry -> entry.getElementsByTag("a").first())
                .map(entry -> entry.getElementsByTag("img").first())
                .map(entry -> entry.attr("src"))
                .orElse(null);
        blockBuilder.picture(picture);

        Optional<Element> body = block.map(entry -> entry.getElementsByClass("content").first());
        Optional<Element> basic = body
                .map(entry -> entry.getElementsByClass("title").first())
                .map(entry -> entry.getElementsByTag("a").first());

        String title = basic.map(Element::text).orElse(null);
        String url = basic.map(entry -> entry.attr("href")).orElse(null);
        String content = body
                .map(entry -> entry.getElementsByTag("p").first())
                .map(Element::text)
                .orElse(null);
        blockBuilder.content(content).title(title).url(url);

        return blockBuilder.build();
    }
}
