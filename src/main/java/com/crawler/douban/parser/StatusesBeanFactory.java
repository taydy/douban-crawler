package com.crawler.douban.parser;

import com.crawler.douban.parser.statuses.EmptyParseService;
import com.crawler.douban.service.parser.ParseService;

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
public class StatusesBeanFactory {

    private StatusesBeanFactory() {
    }

    public static Map<String, ParseService> map = new ConcurrentHashMap<>();

    static {
        Reflections reflections = new Reflections("com.crawler.douban.parser.statuses");
        Set<Class<?>> classList = reflections.getTypesAnnotatedWith(StatusesType.class);
        for (Class classes : classList) {
            StatusesType statusesType = (StatusesType) classes.getAnnotation(StatusesType.class);
            String[] valueList = statusesType.value();
            for (String value : valueList) {
                try {
                    map.put(value, (ParseService) classes.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ParseService getBeanByType(String statusesType) {
        return Optional.ofNullable(map.get(statusesType)).orElse(new EmptyParseService());
    }

}
