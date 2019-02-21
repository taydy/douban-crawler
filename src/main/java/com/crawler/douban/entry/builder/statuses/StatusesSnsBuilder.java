package com.crawler.douban.entry.builder.statuses;

import com.crawler.douban.entry.StatusesSns;

import java.util.List;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/16
 */
public class StatusesSnsBuilder extends BasicInfoBuilder<StatusesSns> {

    private StatusesSns statusesSns;

    public StatusesSnsBuilder() {
        this.statusesSns = new StatusesSns();
        super.init(this.statusesSns);
    }

    public StatusesSnsBuilder picture(List<String> picture) {
        this.statusesSns.setPicture(picture);
        return this;
    }

    public StatusesSnsBuilder content(String content) {
        this.statusesSns.setContent(content);
        return this;
    }

    public StatusesSns build() {
        return this.statusesSns;
    }
}
