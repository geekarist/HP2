package com.github.geekarist.hp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

public class BookDetailActivity extends Activity {

    public static final String EXTRA_ISBN = "isbn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        TextView detailTitleText = (TextView) findViewById(R.id.book_detail_title_text);
        detailTitleText.setText(getIntent().getStringExtra(EXTRA_ISBN));
    }

    public static Intent newIntent(Context context, @NonNull Book book) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(EXTRA_ISBN, book.isbn);
        return intent;
    }
}
