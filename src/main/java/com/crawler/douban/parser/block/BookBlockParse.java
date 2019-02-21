package com.crawler.douban.parser.block;

import com.crawler.douban.entry.Block;
import com.crawler.douban.entry.builder.block.BookBlockBuilder;
import com.crawler.douban.parser.BlockType;

import org.jsoup.nodes.Element;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/21
 */
@BlockType("book")
public class BookBlockParse implements BlockParseService {

    @Override
    public Block parse(Optional<Element> blockSubject) {
        BookBlockBuilder blockBuilder = new BookBlockBuilder();

        String img = blockSubject
                .map(entry -> entry.getElementsByClass("pic").first())
                .map(entry -> entry.getElementsByTag("a").first())
                .map(entry -> entry.getElementsByTag("img").first())
                .map(entry -> entry.attr("src"))
                .orElse(null);
        blockBuilder.picture(img);

        Optional<Element> bookContentElement = blockSubject
                .map(entry -> entry.getElementsByClass("content").first());
        Optional<Element> bookTitleElement = bookContentElement.map(entry -> entry.getElementsByClass("title").first());
        Optional<Element> bookBasinInfo = bookTitleElement.map(entry -> entry.getElementsByTag("a").first());
        String bookUrl = bookBasinInfo.map(entry -> entry.attr("href")).orElse(null);
        String bookName = bookBasinInfo.map(Element::text).orElse(null);
        Optional<String> bookRating = bookTitleElement
                .map(entry -> entry.getElementsByClass("rating_num").first())
                .map(Element::text)
                .map(entry -> StringUtils.isEmpty(entry) ? null : entry);
        String bookContent = bookContentElement
                .map(entry -> entry.getElementsByTag("p").first())
                .map(Element::text)
                .orElse(null);
        blockBuilder
                .rating(bookRating.map(entry -> Double.parseDouble(entry)).orElse(null))
                .url(bookUrl)
                .title(bookName)
                .content(bookContent);

        Optional<Element> bookInfo = bookContentElement
                .map(entry -> entry.getElementsByClass("info").first());
        if (bookInfo.isPresent()) {
            bookInfo.get().getElementsByTag("li").forEach(entry -> {
                String label = entry.getElementsByClass("label").first().text().trim();
                String value = entry.getElementsByTag("span").last().text().trim();
                switch (label.substring(0, label.length() - 1)) {
                    case "作者":
                        blockBuilder.author(value);
                        break;
                    case "出版":
                        blockBuilder.press(value);
                        break;
                    default:
                        break;
                }
            });
        }

        return blockBuilder.build();
    }

}

