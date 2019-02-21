package com.crawler.douban.entry.builder.block;

import com.crawler.douban.entry.Movie;

import java.util.List;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/21
 */
public class MovieBlockBuilder extends BlockBuilder<Movie> {

    private Movie movie;

    public MovieBlockBuilder() {
        this.movie = new Movie();
        super.init(movie);
    }

    public MovieBlockBuilder name(String name) {
        this.movie.setTitle(name);
        return this;
    }

    public MovieBlockBuilder year(Integer year) {
        this.movie.setYear(year);
        return this;
    }

    public MovieBlockBuilder director(String director) {
        this.movie.setDirector(director);
        return this;
    }

    public MovieBlockBuilder starring(String starring) {
        this.movie.setStarring(starring);
        return this;
    }

    public MovieBlockBuilder rating(Double rating) {
        this.movie.setRating(rating);
        return this;
    }

    public MovieBlockBuilder types(List<String> types) {
        this.movie.setTypes(types);
        return this;
    }

}
