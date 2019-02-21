package com.crawler.douban.parser.block;

import com.crawler.douban.entry.Block;
import com.crawler.douban.entry.builder.block.MovieBlockBuilder;
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
@BlockType("movie")
public class MovieBlockParse implements BlockParseService {

    @Override
    public Block parse(Optional<Element> blockSubject) {
        MovieBlockBuilder blockBuilder = new MovieBlockBuilder();

        String img = blockSubject
                .map(entry -> entry.getElementsByClass("pic").first())
                .map(entry -> entry.getElementsByTag("a").first())
                .map(entry -> entry.getElementsByTag("img").first())
                .map(entry -> entry.attr("src"))
                .orElse(null);
        blockBuilder.picture(img);

        Optional<Element> contentElement = blockSubject.map(entry -> entry.getElementsByClass("content").first());
        Optional<Element> titleElement = contentElement.map(entry -> entry.getElementsByClass("title").first());
        Optional<Element> basinInfoElement = titleElement.map(entry -> entry.getElementsByTag("a").first());

        String movieUrl = basinInfoElement.map(entry -> entry.attr("href")).orElse(null);
        blockBuilder.url(movieUrl);

        String[] basicDatas = basinInfoElement.map(Element::text).orElse("").split("\\(");
        if (basicDatas.length == 2) {
            Integer year = StringUtils.isEmpty(basicDatas[1]) ? null : Integer.parseInt(basicDatas[1].substring(0, basicDatas[1].length() - 1));
            blockBuilder.name(basicDatas[0]).year(year);
        } else if (basicDatas.length == 1) {
            blockBuilder.name(basicDatas[0]);
        }

        String rating = titleElement
                .map(entry -> entry.getElementsByClass("rating_num").first())
                .map(Element::text)
                .orElse(null);
        String content = contentElement
                .map(entry -> entry.getElementsByTag("p").first())
                .map(Element::text)
                .orElse(null);
        blockBuilder
                .rating(StringUtils.isEmpty(rating) ? null : Double.parseDouble(rating))
                .content(content);

        Optional<Element> movieInfo = contentElement.map(entry -> entry.getElementsByClass("info").first());
        movieInfo.map(
                entry -> entry.getElementsByTag("li")).ifPresent(
                arrays -> arrays.forEach(
                        entry -> {
                            String label = entry.getElementsByClass("label").first().text().trim();
                            String value = entry.getElementsByTag("span").last().text().trim();
                            switch (label.substring(0, label.length() - 1)) {
                                case "导演":
                                    blockBuilder.director(value);
                                    break;
                                case "主演":
                                    blockBuilder.starring(value);
                                    break;
                                case "类型":
                                    blockBuilder.types(Arrays.asList(value.split(" ")));
                                    break;
                                default:
                                    break;
                            }
                        }));

        return blockBuilder.build();
    }

}
