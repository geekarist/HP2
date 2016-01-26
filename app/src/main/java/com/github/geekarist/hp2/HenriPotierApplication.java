package com.github.geekarist.hp2;

import android.app.Application;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class HenriPotierApplication extends Application {
    private static HenriPotierApplication instance;

    public static HenriPotierApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: is this Ok?
        instance = this;
    }

    public BookService getBookService() {
        // TODO: move to onCreate
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://henri-potier.xebia.fr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(BookService.class);
    }
}
