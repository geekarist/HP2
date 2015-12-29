package com.github.geekarist.hp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;

public class ListBooksActivity extends AppCompatActivity {

    private static final String TAG = ListBooksActivity.class.getSimpleName();

    private ListBooksAdapter mBookListAdapter;
    private View mFixConnectivityView;
    private View mReloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);
        RecyclerView bookListView = (RecyclerView) findViewById(R.id.book_list_view);
        bookListView.setLayoutManager(new LinearLayoutManager(this));
        mBookListAdapter = new ListBooksAdapter(this);
        bookListView.setAdapter(mBookListAdapter);
        mFixConnectivityView = findViewById(R.id.fix_connectivity_group);
        mReloadButton = findViewById(R.id.reload_button);
        mReloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchBooks();
            }
        });
        fetchBooks();
    }

    private void fetchBooks() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://henri-potier.xebia.fr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookService bookService = retrofit.create(BookService.class);
        bookService.listBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Response<List<Book>> response, Retrofit retrofit) {
                mBookListAdapter.setBooks(response.body());
                mFixConnectivityView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Throwable t) {
                mBookListAdapter.setBooks(new ArrayList<Book>());
                Log.e(TAG, getString(R.string.list_books_error), t);
                mFixConnectivityView.setVisibility(View.VISIBLE);
            }
        });
    }

    public interface BookService {
        @GET("/books")
        Call<List<Book>> listBooks();
    }
}
