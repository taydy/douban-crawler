package com.crawler.douban.entry.builder.block;

import com.crawler.douban.entry.Book;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/21
 */
public class BookBlockBuilder extends BlockBuilder<Book> {

    private Book book;

    public BookBlockBuilder() {
        this.book = new Book();
        super.init(this.book);
    }

    public BookBlockBuilder author(String author) {
        this.book.setAuthor(author);
        return this;
    }

    public BookBlockBuilder press(String press) {
        this.book.setPress(press);
        return this;
    }

    public BookBlockBuilder rating(Double rating) {
        this.book.setRating(rating);
        return this;
    }

}
