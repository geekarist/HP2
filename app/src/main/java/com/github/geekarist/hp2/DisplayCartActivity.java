package com.github.geekarist.hp2;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
        mAdapter = new ListBooksAdapter();
        mCartListView.setAdapter(mAdapter);
        Book book = getIntent().getParcelableExtra(EXTRA_BOOK);
        mAdapter.addBook(book);

        List<Book> books = deserialize(
                getSharedPreferences(BOOKS_BUNDLE_KEY, MODE_PRIVATE).getString(BOOKS_BUNDLE_KEY, "[]"));
        mAdapter.addBooks(books);

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
        totalValueText.setPaintFlags(totalValueText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        TextView totalDiscountText = (TextView) findViewById(R.id.cart_total_discount_text);
        double discount = mAdapter.totalPrice() / 10;
        totalDiscountText.setText(getString(R.string.cart_total_discount, mAdapter.totalPrice() - discount));
    }

    @Override
    protected void onStop() {
        super.onStop();
        getSharedPreferences(BOOKS_BUNDLE_KEY, MODE_PRIVATE)
                .edit()
                .putString(BOOKS_BUNDLE_KEY, serialize(mAdapter.getBooks()))
                .commit();
    }

    private List<Book> deserialize(String booksAsJson) {
        Type typeOfBookList = new TypeToken<List<Book>>() {
        }.getType();
        return new Gson().fromJson(booksAsJson, typeOfBookList);
    }

    private String serialize(List<Book> books) {
        return new Gson().toJson(books);
    }

    public static Intent newAddToCartIntent(BookDetailActivity bookDetailActivity, Book book) {
        Intent intent = new Intent(bookDetailActivity, DisplayCartActivity.class);
        intent.putExtra(EXTRA_BOOK, book);
        return intent;
    }
}
