package com.github.geekarist.hp2.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.geekarist.hp2.R;
import com.github.geekarist.hp2.domain.Book;

public class BookDetailActivity extends AppCompatActivity {

    public static final String EXTRA_BOOK = "book";

    public static Intent newIntent(Context context, @NonNull Book book) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(EXTRA_BOOK, book);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        final Book book = getIntent().getParcelableExtra(EXTRA_BOOK);

        TextView titleText = (TextView) findViewById(R.id.book_detail_title_text);
        titleText.setText(book.getTitle());
        ImageView coverImageView = (ImageView) findViewById(R.id.book_detail_image_view);
        Glide.with(this).load(book.getCover()).placeholder(R.drawable.book_cover_placeholder).into(coverImageView);
        Button buyButton = (Button) findViewById(R.id.book_detail_buy_button);
        buyButton.setText(getString(R.string.buy, book.getPrice()));
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DisplayCartActivity.newAddToCartIntent(BookDetailActivity.this, book));
            }
        });
    }
}
