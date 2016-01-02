package com.github.geekarist.hp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookDetailActivity extends Activity {

    public static final String EXTRA_BOOK = "book";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        Book book = getIntent().getParcelableExtra(EXTRA_BOOK);

        TextView titleText = (TextView) findViewById(R.id.book_detail_title_text);
        titleText.setText(book.title);
        ImageView coverImageView = (ImageView) findViewById(R.id.book_detail_image_view);
        Glide.with(this).load(book.cover).placeholder(R.drawable.book_cover_placeholder).into(coverImageView);
    }

    public static Intent newIntent(Context context, @NonNull Book book) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(EXTRA_BOOK, book);
        return intent;
    }
}
