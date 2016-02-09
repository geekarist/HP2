package com.github.geekarist.hp2.domain;

public class Book {
    final String title;
    final String isbn;
    final String cover;
    final double price;

    public Book(String title, String isbn, double price, String cover) {
        this.title = title;
        this.isbn = isbn;
        this.cover = cover;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCover() {
        return cover;
    }

    public double getPrice() {
        return price;
    }
}
