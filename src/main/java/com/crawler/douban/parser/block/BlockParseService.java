package com.crawler.douban.parser.block;

import com.crawler.douban.entry.Block;

import org.jsoup.nodes.Element;

import java.util.Optional;


/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
public interface BlockParseService {

    Block parse(Optional<Element> blockSubject);

}
