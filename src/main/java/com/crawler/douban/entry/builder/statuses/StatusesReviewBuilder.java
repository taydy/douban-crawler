package com.crawler.douban.entry.builder.statuses;

import com.crawler.douban.entry.StatusesReview;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/16
 */
public class StatusesReviewBuilder extends BasicInfoBuilder<StatusesReview> {

    private StatusesReview statusesReview;

    public StatusesReviewBuilder() {
        this.statusesReview = new StatusesReview();
        super.init(this.statusesReview);
    }

    public StatusesReviewBuilder picture(String picture) {
        this.statusesReview.setPicture(picture);
        return this;
    }

    public StatusesReviewBuilder title(String title) {
        this.statusesReview.setTitle(title);
        return this;
    }

    public StatusesReviewBuilder content(String content) {
        this.statusesReview.setContent(content);
        return this;
    }

    public StatusesReview build() {
        return this.statusesReview;
    }

}
