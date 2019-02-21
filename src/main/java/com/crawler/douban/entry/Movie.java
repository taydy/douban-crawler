package com.crawler.douban.entry;

import java.util.List;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/15
 */
public class Movie extends Block {

    private Integer year;

    private Double rating;

    private String director;

    private String starring;

    private List<String> types;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}
