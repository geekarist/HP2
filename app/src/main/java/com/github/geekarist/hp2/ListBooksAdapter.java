package com.github.geekarist.hp2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ListBooksAdapter extends RecyclerView.Adapter<ListBooksAdapter.ViewHolder> {
    private final List<Book> mBooks;
    private Context mContext;

    public ListBooksAdapter(Context mContext) {
        this.mContext = mContext;
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
        Glide.with(mContext)
                .load(mBooks.get(position).cover)
                .placeholder(R.drawable.book_cover_placeholder)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleView;
        public ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTitleView = (TextView) itemView.findViewById(R.id.book_title_view);
            this.mImageView = (ImageView) itemView.findViewById(R.id.book_image_view);
        }
    }
}
