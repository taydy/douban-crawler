package com.crawler.douban.entry.builder.statuses;

import com.crawler.douban.entry.StatusesMovie;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/16
 */
public class StatusesMovieBuilder extends BasicInfoBuilder<StatusesMovie> {

    private StatusesMovie statusesMovie;

    public StatusesMovieBuilder() {
        this.statusesMovie = new StatusesMovie();
        super.init(this.statusesMovie);
    }

    public StatusesMovieBuilder ratingStars(String ratingStars) {
        this.statusesMovie.setRatingStars(ratingStars);
        return this;
    }

    public StatusesMovieBuilder comment(String comment) {
        this.statusesMovie.setComment(comment);
        return this;
    }

    public StatusesMovie build() {
        return this.statusesMovie;
    }
}
