package com.github.geekarist.hp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ListBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);
        RecyclerView bookListView = (RecyclerView) findViewById(R.id.book_list_view);
        bookListView.setLayoutManager(new LinearLayoutManager(this));
        bookListView.setAdapter(new ListBooksAdapter());
    }
}
