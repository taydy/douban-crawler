package com.crawler.douban.entry.builder.statuses;

import com.crawler.douban.entry.Statuses;
import com.crawler.douban.entry.StatusesForward;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
public class StatusesForwardBuilder extends BasicInfoBuilder<StatusesForward> {

    private StatusesForward statusesForward;

    public StatusesForwardBuilder() {
        this.statusesForward = new StatusesForward();
        super.init(this.statusesForward);
    }

    public StatusesForwardBuilder addOrigin(Statuses statuses) {
        this.statusesForward.setOrigin(statuses);
        return this;
    }

    public StatusesForwardBuilder content(String content) {
        this.statusesForward.setContent(content);
        return this;
    }

    public StatusesForward build() {
        return this.statusesForward;
    }

}
