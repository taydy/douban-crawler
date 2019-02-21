package com.crawler.douban.entry;

import java.util.List;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/16
 */
public class Loc extends Block {

    private Double rating;

    private List<String> types;

    private String director;

    private String starring;

    private String playwright;

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getPlaywright() {
        return playwright;
    }

    public void setPlaywright(String playwright) {
        this.playwright = playwright;
    }

}
