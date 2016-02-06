package com.github.geekarist.hp2.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.geekarist.hp2.R;

import java.lang.ref.WeakReference;
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
    private RecyclerView mBookListView;
    private BookService mBookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);
        mBookListView = (RecyclerView) findViewById(R.id.book_list_view);
        mBookListView.setLayoutManager(new LinearLayoutManager(this));
        mBookListAdapter = new ListBooksAdapter();
        mBookListView.setAdapter(mBookListAdapter);
        mFixConnectivityView = findViewById(R.id.fix_connectivity_group);
        mReloadButton = findViewById(R.id.reload_button);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://henri-potier.xebia.fr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mBookService = retrofit.create(BookService.class);

        mReloadButton.setOnClickListener(v -> fetchBooks());
        fetchBooks();
    }

    private void fetchBooks() {
        mBookService.listBooks().enqueue(new FetchBookCallback(this));
    }

    public interface BookService {
        @GET("/books")
        Call<List<ParcelableBook>> listBooks();
    }

    private static class FetchBookCallback implements Callback<List<ParcelableBook>> {
        private final WeakReference<ListBooksActivity> mActivityWeakReference;

        public FetchBookCallback(ListBooksActivity activity) {
            mActivityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void onResponse(Response<List<ParcelableBook>> response, Retrofit retrofit) {
            if (getActivity() != null && !getActivity().isFinishing()) {
                getActivity().mBookListAdapter.setBooks(response.body());
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
