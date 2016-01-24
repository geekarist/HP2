package com.github.geekarist.hp2;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface BookService {
    @GET("/books/{isbnList}/commercialOffers")
    Call<BookDiscountCatalog> listCommercialOffers(@Path("isbnList") String isbnList);
}
