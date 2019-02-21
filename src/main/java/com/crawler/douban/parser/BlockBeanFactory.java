package com.crawler.douban.parser;

import com.crawler.douban.parser.block.BlockParseService;
import com.crawler.douban.parser.block.DefaultBlockParseService;

import org.reflections.Reflections;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
public class BlockBeanFactory {

    private BlockBeanFactory() {
    }

    public static Map<String, BlockParseService> map = new ConcurrentHashMap<>();

    static {
        Reflections reflections = new Reflections("com.crawler.douban.parser.block");
        Set<Class<?>> classList = reflections.getTypesAnnotatedWith(BlockType.class);
        for (Class classes : classList) {
            BlockType blockType = (BlockType) classes.getAnnotation(BlockType.class);
            String[] valueList = blockType.value();
            for (String value : valueList) {
                try {
                    map.put(value, (BlockParseService) classes.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static BlockParseService getBeanByType(String statusesType) {
        return Optional.ofNullable(map.get(statusesType)).orElse(new DefaultBlockParseService());
    }

}
