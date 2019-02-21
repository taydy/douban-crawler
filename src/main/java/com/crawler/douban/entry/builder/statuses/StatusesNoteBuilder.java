package com.crawler.douban.entry.builder.statuses;

import com.crawler.douban.entry.StatusesNote;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/16
 */
public class StatusesNoteBuilder extends BasicInfoBuilder<StatusesNote> {

    private StatusesNote statusesNote;

    public StatusesNoteBuilder() {
        this.statusesNote = new StatusesNote();
        super.init(this.statusesNote);
    }

    public StatusesNoteBuilder picture(String picture) {
        this.statusesNote.setPicture(picture);
        return this;
    }

    public StatusesNoteBuilder title(String title) {
        this.statusesNote.setTitle(title);
        return this;
    }

    public StatusesNoteBuilder content(String content) {
        this.statusesNote.setContent(content);
        return this;
    }

    public StatusesNote build() {
        return this.statusesNote;
    }

}
