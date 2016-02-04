package com.github.geekarist.hp2.model;

import com.github.geekarist.hp2.domain.Book;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

public interface BookService {
    @GET("/books")
    Call<List<Book>> listBooks();
}
