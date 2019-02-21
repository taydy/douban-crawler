package com.crawler.douban.parser.block;

import com.crawler.douban.entry.Block;
import com.crawler.douban.entry.builder.block.PhotoBlockBuilder;
import com.crawler.douban.parser.BlockType;

import org.jsoup.nodes.Element;

import java.util.Optional;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/21
 */
@BlockType("photo")
public class PhotoBlockParse implements BlockParseService {

    @Override
    public Block parse(Optional<Element> blockSubject) {
        PhotoBlockBuilder blockBuilder = new PhotoBlockBuilder();

        String img = blockSubject.map(entry -> entry.getElementsByClass("block-photo").first())
                .map(entry -> entry.getElementsByTag("img").first())
                .map(entry -> entry.attr("src"))
                .orElse(null);
        blockBuilder.picture(img);

        return blockBuilder.build();
    }

}
