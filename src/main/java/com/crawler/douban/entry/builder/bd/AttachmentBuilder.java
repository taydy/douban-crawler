package com.crawler.douban.entry.builder.bd;

import com.crawler.douban.entry.Attachment;
import com.crawler.douban.entry.builder.block.BlockBuilder;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/21
 */
public class AttachmentBuilder extends BlockBuilder<Attachment> {

    private Attachment attachment;

    public AttachmentBuilder() {
        this.attachment = new Attachment();
    }

}
