package com.crawler.douban.entry.builder.statuses;

import com.crawler.douban.entry.StatusesBook;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/16
 */
public class StatusesBookBuilder extends BasicInfoBuilder<StatusesBook> {

    private StatusesBook statusesBook;

    public StatusesBookBuilder() {
        this.statusesBook = new StatusesBook();
        super.init(this.statusesBook);
    }

    public StatusesBookBuilder ratingStars(String ratingStars) {
        this.statusesBook.setRatingStars(ratingStars);
        return this;
    }

    public StatusesBookBuilder comment(String comment) {
        this.statusesBook.setComment(comment);
        return this;
    }

    public StatusesBook build() {
        return this.statusesBook;
    }

}
