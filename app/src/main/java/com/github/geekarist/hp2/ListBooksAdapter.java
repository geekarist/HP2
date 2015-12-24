package com.github.geekarist.hp2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class ListBooksAdapter extends RecyclerView.Adapter<ListBooksAdapter.ViewHolder> {
    private final List<Book> mBooks;

    public ListBooksAdapter() {
        mBooks = Arrays.asList(
                new Book("Henri Potier 1", "isbn", 10, "http://henri-potier.xebia.fr/hp0.jpg"),
                new Book("Henri Potier 2", "isbn2", 20, "http://henri-potier.xebia.fr/hp1.jpg"));
    }

    @Override
    public ListBooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ListBooksAdapter.ViewHolder holder, int position) {
        holder.mTitleView.setText(mBooks.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTitleView = (TextView) itemView.findViewById(R.id.book_title_view);
        }
    }
}
