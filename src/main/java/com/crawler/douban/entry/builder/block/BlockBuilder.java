package com.crawler.douban.entry.builder.block;

import com.crawler.douban.entry.Block;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
public class BlockBuilder<T extends Block> {

    private Block block;

    public BlockBuilder() {
        this.block = new Block();
    }

    public void init(T block) {
        this.block = block;
    }

    public BlockBuilder title(String title) {
        this.block.setTitle(title);
        return this;
    }

    public BlockBuilder content(String content) {
        this.block.setPost(content);
        return this;
    }

    public BlockBuilder picture(String picture) {
        this.block.setPicture(picture);
        return this;
    }

    public BlockBuilder url(String url) {
        this.block.setUrl(url);
        return this;
    }

    public Block build() {
        return this.block;
    }

}
