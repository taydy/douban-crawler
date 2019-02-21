package com.crawler.douban.entry.builder.block;

import com.crawler.douban.entry.builder.Photo;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/21
 */
public class PhotoBlockBuilder extends BlockBuilder<Photo> {

    private Photo photo;

    public PhotoBlockBuilder() {
        this.photo = new Photo();
        super.init(this.photo);
    }

}
