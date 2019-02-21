package com.crawler.douban.entry.builder.statuses;

import com.crawler.douban.entry.StatusesPhoto;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/16
 */
public class StatusesPhotoBuilder extends BasicInfoBuilder<StatusesPhoto> {

    private StatusesPhoto statusesPhoto;

    public StatusesPhotoBuilder() {
        this.statusesPhoto = new StatusesPhoto();
        super.init(this.statusesPhoto);
    }

    public StatusesPhoto build() {
        return this.statusesPhoto;
    }

}
