package com.github.geekarist.hp2;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Arrays;
import java.util.Collections;

public class DisplayCartActivity extends AppCompatActivity {

    private static final String EXTRA_BOOK = "BOOK";

    private RecyclerView mCartListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_cart);

        mCartListView = (RecyclerView) findViewById(R.id.book_cart_view);
        mCartListView.setLayoutManager(new LinearLayoutManager(this));
        ListBooksAdapter adapter = new ListBooksAdapter(this);
        mCartListView.setAdapter(adapter);
        Book book = getIntent().getParcelableExtra(EXTRA_BOOK);
        adapter.addBook(book);

        Toolbar toolbar = (Toolbar) findViewById(R.id.book_cart_toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static Intent newAddToCartIntent(BookDetailActivity bookDetailActivity, Book book) {
        Intent intent = new Intent(bookDetailActivity, DisplayCartActivity.class);
        intent.putExtra(EXTRA_BOOK, book);
        return intent;
    }
}
