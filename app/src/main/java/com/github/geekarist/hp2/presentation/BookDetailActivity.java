package com.github.geekarist.hp2.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.geekarist.hp2.R;

public class BookDetailActivity extends AppCompatActivity {

    public static final String EXTRA_BOOK = "book";

    public static Intent newIntent(Context context, @NonNull ParcelableBook book) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(EXTRA_BOOK, book);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        final ParcelableBook book = getIntent().getParcelableExtra(EXTRA_BOOK);

        TextView titleText = (TextView) findViewById(R.id.book_detail_title_text);
        titleText.setText(book.getTitle());
        ImageView coverImageView = (ImageView) findViewById(R.id.book_detail_image_view);
        Glide.with(this).load(book.getCover()).placeholder(R.drawable.book_cover_placeholder).into(coverImageView);
        Button buyButton = (Button) findViewById(R.id.book_detail_buy_button);
        buyButton.setText(getString(R.string.buy, book.getPrice()));
        buyButton.setOnClickListener(v -> startActivity(DisplayCartActivity.newAddToCartIntent(BookDetailActivity.this, book)));
    }
}
