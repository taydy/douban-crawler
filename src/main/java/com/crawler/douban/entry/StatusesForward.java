package com.crawler.douban.entry;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
public class StatusesForward extends BasicInfo implements Statuses {

    private String content;

    private Statuses origin;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Statuses getOrigin() {
        return origin;
    }

    public void setOrigin(Statuses origin) {
        this.origin = origin;
    }

}
