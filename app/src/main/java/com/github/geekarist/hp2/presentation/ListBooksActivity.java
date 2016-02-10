package com.github.geekarist.hp2.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.geekarist.hp2.R;
import com.github.geekarist.hp2.data.BookApi;
import com.github.geekarist.hp2.domain.Book;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ListBooksActivity extends AppCompatActivity {

    private static final String TAG = ListBooksActivity.class.getSimpleName();

    private ListBooksAdapter mBookListAdapter;
    private View mFixConnectivityView;
    private View mReloadButton;
    private RecyclerView mBookListView;
    private BookApi mBookApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);
        mBookListView = (RecyclerView) findViewById(R.id.book_list_view);
        mBookListView.setLayoutManager(new LinearLayoutManager(this));
        mBookListAdapter = new ListBooksAdapter();
        mBookListView.setAdapter(mBookListAdapter);
        mFixConnectivityView = findViewById(R.id.fix_connectivity_group);

        mBookApi = HenriPotierApplication.getInstance().getBookApi();

        mReloadButton = findViewById(R.id.reload_button);
        mReloadButton.setOnClickListener(v -> fetchBooks());
        fetchBooks();
    }

    private void fetchBooks() {
        mBookApi.listBooks(new FetchBookCallback(this));
    }

    private static class FetchBookCallback implements BookApi.BookApiCallback<List<Book>> {
        private final WeakReference<ListBooksActivity> mActivityWeakReference;

        public FetchBookCallback(ListBooksActivity activity) {
            mActivityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void onResponse(List<Book> response) {
            if (getActivity() != null && !getActivity().isFinishing()) {
                getActivity().mBookListAdapter.setBooks(response);
                getActivity().mFixConnectivityView.setVisibility(View.GONE);
            }
        }

        @Override
        public void onFailure(Throwable t) {
            Log.e(TAG, getActivity().getString(R.string.list_books_error), t);
            if (getActivity() != null && !getActivity().isFinishing()) {
                getActivity().mBookListAdapter.setBooks(new ArrayList<>());
                getActivity().mFixConnectivityView.setVisibility(View.VISIBLE);
            }
        }

        private ListBooksActivity getActivity() {
            return mActivityWeakReference.get();
        }
    }
}
