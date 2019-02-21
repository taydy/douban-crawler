package com.crawler.douban.entry;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
public class StatusesMusic extends BasicInfo implements Statuses {

    private String ratingStars;

    private String comment;

    public String getRatingStars() {
        return ratingStars;
    }

    public void setRatingStars(String ratingStars) {
        this.ratingStars = ratingStars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
