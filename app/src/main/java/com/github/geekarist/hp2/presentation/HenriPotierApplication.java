package com.github.geekarist.hp2.presentation;

import android.app.Application;

import com.github.geekarist.hp2.data.BookService;
import com.github.geekarist.hp2.data.BookServiceFactory;

public class HenriPotierApplication extends Application {
    private static HenriPotierApplication mInstance;

    private BookService mBookService;

    public static HenriPotierApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookService = BookServiceFactory.createBookService();
        // TODO: is this Ok?
        mInstance = this;
    }

    public BookService getBookService() {
        return mBookService;
    }

}
