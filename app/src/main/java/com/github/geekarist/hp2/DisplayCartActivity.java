package com.github.geekarist.hp2;

import android.content.Intent;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DisplayCartActivity extends AppCompatActivity {

    private static final String EXTRA_BOOK = "BOOK";
    private static final String BOOKS_BUNDLE_KEY = "BOOKS";

    private RecyclerView mCartListView;
    private ListBooksAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_cart);

        mCartListView = (RecyclerView) findViewById(R.id.book_cart_view);
        mCartListView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ListBooksAdapter(this);
        mCartListView.setAdapter(mAdapter);
        Book book = getIntent().getParcelableExtra(EXTRA_BOOK);
        mAdapter.addBook(book);

        if (savedInstanceState != null) {
            Book[] books = (Book[]) savedInstanceState.getParcelableArray(BOOKS_BUNDLE_KEY);
            if (books != null) {
                mAdapter.addBooks(Arrays.asList(books));
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.book_cart_toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        TextView totalLabelText = (TextView) findViewById(R.id.cart_total_label_text);
        totalLabelText.setText(getString(R.string.cart_total_label, mAdapter.getItemCount()));
        TextView totalValueText = (TextView) findViewById(R.id.cart_total_value_text);
        totalValueText.setText(getString(R.string.cart_total_value, mAdapter.totalPrice()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        List<Book> books = mAdapter.getBooks();
        outState.putParcelableArray(BOOKS_BUNDLE_KEY, books.toArray(new Book[books.size()]));
    }

    public static Intent newAddToCartIntent(BookDetailActivity bookDetailActivity, Book book) {
        Intent intent = new Intent(bookDetailActivity, DisplayCartActivity.class);
        intent.putExtra(EXTRA_BOOK, book);
        return intent;
    }
}
