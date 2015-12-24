package com.github.geekarist.hp2;

public class Book {
    final String title;
    final String isbn;
    final int price;
    final String cover;

    public Book(String title, String isbn, int price, String cover) {
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.cover = cover;
    }
}
