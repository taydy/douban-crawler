package com.crawler.douban.entry;

import java.util.List;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
public class Music extends Block {

    private String year;

    private Double rating;

    private String performer;

    private List<String> types;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

}
