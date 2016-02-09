package com.github.geekarist.hp2.data;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class BookServiceFactory {
    public static BookService createBookService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://henri-potier.xebia.fr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(BookService.class);
    }
}
