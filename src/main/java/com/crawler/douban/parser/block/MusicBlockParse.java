package com.crawler.douban.parser.block;

import com.crawler.douban.entry.Block;
import com.crawler.douban.entry.builder.block.MusicBlockBuilder;
import com.crawler.douban.parser.BlockType;

import org.jsoup.nodes.Element;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/21
 */
@BlockType("music")
public class MusicBlockParse implements BlockParseService {

    @Override
    public Block parse(Optional<Element> blockSubject) {
        MusicBlockBuilder blockBuilder = new MusicBlockBuilder();

        String img = blockSubject
                .map(entry -> entry.getElementsByClass("pic").first())
                .map(entry -> entry.getElementsByTag("a").first())
                .map(entry -> entry.getElementsByTag("img").first())
                .map(entry -> entry.attr("src"))
                .orElse(null);
        blockBuilder.picture(img);

        Optional<Element> contentElement = blockSubject
                .map(entry -> entry.getElementsByClass("content").first());
        Optional<Element> titleElement = contentElement.map(entry -> entry.getElementsByClass("title").first());
        Optional<Element> basicElement = titleElement.map(entry -> entry.getElementsByTag("a").first());
        String musicUrl = basicElement.map(entry -> entry.attr("href")).orElse(null);
        String musicName = basicElement.map(Element::text).orElse(null);
        Optional<String> musicRating = titleElement
                .map(entry -> entry.getElementsByClass("rating_num").first())
                .map(Element::text)
                .map(entry -> StringUtils.isEmpty(entry) ? null : entry);
        String musicContent = contentElement
                .map(entry -> entry.getElementsByTag("p").first())
                .map(Element::text)
                .orElse(null);
        blockBuilder
                .rating(musicRating.map(entry -> Double.parseDouble(entry)).orElse(null))
                .url(musicUrl)
                .title(musicName)
                .content(musicContent);

        Optional<Element> info = contentElement
                .map(entry -> entry.getElementsByClass("info").first());
        if (info.isPresent()) {
            info.get().getElementsByTag("li").forEach(entry -> {
                String label = entry.getElementsByClass("label").first().text().trim();
                String value = entry.getElementsByTag("span").last().text().trim();
                switch (label.substring(0, label.length() - 1)) {
                    case "表演者":
                        blockBuilder.performer(value);
                        break;
                    case "发行时间":
                        blockBuilder.year(value);
                        break;
                    case "流派":
                        blockBuilder.types(Arrays.asList(value.split(" ")));
                    default:
                        break;
                }
            });
        }

        return blockBuilder.build();
    }

}
