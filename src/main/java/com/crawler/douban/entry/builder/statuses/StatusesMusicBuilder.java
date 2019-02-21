package com.crawler.douban.entry.builder.statuses;

import com.crawler.douban.entry.StatusesMusic;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
public class StatusesMusicBuilder extends BasicInfoBuilder<StatusesMusic> {

    private StatusesMusic statusesMusic;

    public StatusesMusicBuilder() {
        this.statusesMusic = new StatusesMusic();
        super.init(this.statusesMusic);
    }

    public StatusesMusicBuilder ratingStars(String ratingStars) {
        this.statusesMusic.setRatingStars(ratingStars);
        return this;
    }

    public StatusesMusicBuilder comment(String comment) {
        this.statusesMusic.setComment(comment);
        return this;
    }

    public StatusesMusic build() {
        return this.statusesMusic;
    }

}
