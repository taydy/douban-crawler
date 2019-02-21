package com.crawler.douban.entry.builder.statuses;

import com.crawler.douban.entry.Action;
import com.crawler.douban.entry.BasicInfo;
import com.crawler.douban.entry.Block;
import com.crawler.douban.entry.User;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
public class BasicInfoBuilder<T extends BasicInfo> {

    private BasicInfo basicInfo;

    public BasicInfoBuilder() {
        this.basicInfo = new BasicInfo();
    }

    public void init(T basicInfo) {
        this.basicInfo = basicInfo;
        this.basicInfo.setAction(new Action());
        this.basicInfo.setUser(new User());
    }

    public BasicInfoBuilder type(String type) {
        this.basicInfo.setType(type);
        return this;
    }

    public BasicInfoBuilder verb(String verb) {
        this.basicInfo.setVerb(verb);
        return this;
    }

    public BasicInfoBuilder url(String url) {
        this.basicInfo.setUrl(url);
        return this;
    }

    public String getUrl() {
        return this.basicInfo.getUrl();
    }

    public BasicInfoBuilder createdAt(Long createdAt) {
        this.basicInfo.setCreatedAt(createdAt);
        return this;
    }

    public BasicInfoBuilder userAvatar(String userAvatar) {
        this.basicInfo.getUser().setAvatar(userAvatar);
        return this;
    }

    public BasicInfoBuilder userNickname(String userNickname) {
        this.basicInfo.getUser().setNickname(userNickname);
        return this;
    }

    public BasicInfoBuilder actionReply(Integer actionReply) {
        this.basicInfo.getAction().setReply(actionReply);
        return this;
    }

    public Integer getReply() {
        return this.basicInfo.getAction().getReply();
    }

    public BasicInfoBuilder actionLike(Integer actionLike) {
        this.basicInfo.getAction().setLike(actionLike);
        return this;
    }

    public Integer getLike() {
        return this.basicInfo.getAction().getLike();
    }

    public BasicInfoBuilder actionReshared(Integer actionReshared) {
        this.basicInfo.getAction().setReshared(actionReshared);
        return this;
    }

    public Integer getResharded() {
        return this.basicInfo.getAction().getReshared();
    }

    public BasicInfoBuilder block(Block block) {
        this.basicInfo.setBlock(block);
        return this;
    }

}
