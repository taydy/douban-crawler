package com.crawler.douban.entry.builder.statuses;

import com.crawler.douban.entry.StatusesLoc;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/16
 */
public class StatusesLocBuilder extends BasicInfoBuilder<StatusesLoc> {

    private StatusesLoc statusesLoc;

    public StatusesLocBuilder() {
        this.statusesLoc = new StatusesLoc();
        super.init(this.statusesLoc);
    }

    public StatusesLocBuilder ratingStars(String ratingStars) {
        this.statusesLoc.setRatingStars(ratingStars);
        return this;
    }

    public StatusesLocBuilder comment(String comment) {
        this.statusesLoc.setComment(comment);
        return this;
    }

    public StatusesLoc build() {
        return this.statusesLoc;
    }

}
