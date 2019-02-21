package com.crawler.douban.entry.builder.block;

import com.crawler.douban.entry.Loc;

import java.util.List;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/21
 */
public class LocBlockBuilder extends BlockBuilder<Loc> {

    private Loc loc;

    public LocBlockBuilder() {
        this.loc = new Loc();
        super.init(this.loc);
    }

    public LocBlockBuilder playwright(String playwright) {
        this.loc.setPlaywright(playwright);
        return this;
    }

    public LocBlockBuilder director(String director) {
        this.loc.setDirector(director);
        return this;
    }

    public LocBlockBuilder starring(String starring) {
        this.loc.setStarring(starring);
        return this;
    }

    public LocBlockBuilder rating(Double rating) {
        this.loc.setRating(rating);
        return this;
    }

    public LocBlockBuilder types(List<String> types) {
        this.loc.setTypes(types);
        return this;
    }

}
