package com.github.geekarist.hp2.data;

import com.github.geekarist.hp2.domain.Book;
import com.github.geekarist.hp2.domain.bestoffer.BookDiscountCatalog;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface BookService {
    @GET("/books/{isbnList}/commercialOffers")
    Call<BookDiscountCatalog> listCommercialOffers(@Path("isbnList") String isbnList);

    @GET("/books")
    Call<List<Book>> listBooks();
}
