package com.crawler.douban.entry;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/15
 */
public class Action {

    private Integer reply = 0;

    private Integer like = 0;

    private Integer reshared = 0;

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getReshared() {
        return reshared;
    }

    public void setReshared(Integer reshared) {
        this.reshared = reshared;
    }
}
