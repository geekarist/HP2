package com.github.geekarist.hp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.widget.TextView;

public class BookDetailActivity extends Activity {

    public static final String EXTRA_BOOK = "book";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        TextView bookTitleText = (TextView) findViewById(R.id.book_detail_title_text);
        Book book = getIntent().getParcelableExtra(EXTRA_BOOK);
        bookTitleText.setText(book.title);
    }

    public static Intent newIntent(Context context, @NonNull Book book) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(EXTRA_BOOK, book);
        return intent;
    }
}
