package com.github.geekarist.hp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DisplayCartActivity extends AppCompatActivity {

    private RecyclerView mCartListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_cart);
        mCartListView = (RecyclerView) findViewById(R.id.book_cart_view);
        mCartListView.setAdapter(new ListBooksAdapter(this));
    }

    public static Intent newAddToCartIntent(BookDetailActivity bookDetailActivity, Book book) {
        return new Intent(bookDetailActivity, DisplayCartActivity.class);
    }
}
