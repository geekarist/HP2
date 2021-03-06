package com.github.geekarist.hp2.presentation;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.github.geekarist.hp2.R;
import com.github.geekarist.hp2.data.RetrofitOfferCatalog;
import com.github.geekarist.hp2.domain.Book;
import com.github.geekarist.hp2.domain.bestoffer.BestOffer;
import com.github.geekarist.hp2.domain.bestoffer.discount.OfferCatalog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DisplayCartActivity extends AppCompatActivity {

    private static final String EXTRA_BOOK = "BOOK";
    private static final String BOOKS_BUNDLE_KEY = "BOOKS";
    private static final String TAG = DisplayCartActivity.class.getSimpleName();

    private RecyclerView mCartListView;
    private ListBooksAdapter mAdapter;

    private BestOffer mBookBestOffer;

    public static Intent newAddToCartIntent(BookDetailActivity bookDetailActivity, Book book) {
        Intent intent = new Intent(bookDetailActivity, DisplayCartActivity.class);
        intent.putExtra(EXTRA_BOOK, new ParcelableBook(book));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_cart);

        mCartListView = (RecyclerView) findViewById(R.id.book_cart_view);
        mCartListView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ListBooksAdapter();
        mCartListView.setAdapter(mAdapter);
        ParcelableBook book = getIntent().getParcelableExtra(EXTRA_BOOK);
        mAdapter.addBook(book);

        List<ParcelableBook> books = deserialize(
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

        OfferCatalog offerCatalog = new RetrofitOfferCatalog(HenriPotierApplication.getInstance().getBookApi());
        mBookBestOffer = new BestOffer(mAdapter, offerCatalog);

        final TextView totalDiscountText = (TextView) findViewById(R.id.cart_total_discount_text);
        mBookBestOffer.calculate(
                bestValue -> totalDiscountText.setText(getString(R.string.cart_total_discount, mAdapter.totalPrice() - bestValue)),
                error -> Log.e(TAG, "Error while listing commercial offers", error));
    }

    @Override
    protected void onStop() {
        super.onStop();
        getSharedPreferences(BOOKS_BUNDLE_KEY, MODE_PRIVATE)
                .edit()
                .putString(BOOKS_BUNDLE_KEY, serialize(mAdapter.getBooks()))
                .commit();
    }

    private List<ParcelableBook> deserialize(String booksAsJson) {
        Type typeOfBookList = new TypeToken<List<ParcelableBook>>() {
        }.getType();
        return new Gson().fromJson(booksAsJson, typeOfBookList);
    }

    private String serialize(List<Book> books) {
        return new Gson().toJson(books);
    }
}
