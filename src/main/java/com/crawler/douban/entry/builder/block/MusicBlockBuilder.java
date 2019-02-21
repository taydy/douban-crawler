package com.crawler.douban.entry.builder.block;

import com.crawler.douban.entry.Music;

import java.util.List;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/21
 */
public class MusicBlockBuilder extends BlockBuilder<Music> {

    private Music music;

    public MusicBlockBuilder() {
        this.music = new Music();
        super.init(this.music);
    }

    public MusicBlockBuilder year(String year) {
        this.music.setYear(year);
        return this;
    }

    public MusicBlockBuilder rating(Double rating) {
        this.music.setRating(rating);
        return this;
    }

    public MusicBlockBuilder performer(String performer) {
        this.music.setPerformer(performer);
        return this;
    }

    public MusicBlockBuilder types(List<String> types) {
        this.music.setTypes(types);
        return this;
    }

}
