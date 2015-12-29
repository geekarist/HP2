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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListBooksAdapter extends RecyclerView.Adapter<ListBooksAdapter.ViewHolder> {
    private List<Book> mBooks;
    private Context mContext;

    public ListBooksAdapter(Context mContext) {
        this.mContext = mContext;
        mBooks = new ArrayList<>();
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
        holder.mPriceView.setText(mContext.getString(R.string.price, mBooks.get(position).price));
        Glide.with(mContext)
                .load(mBooks.get(position).cover)
                .placeholder(R.drawable.book_cover_placeholder)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public void setBooks(List<Book> books) {
        mBooks = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleView;
        public TextView mPriceView;
        public ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTitleView = (TextView) itemView.findViewById(R.id.book_title_view);
            this.mPriceView = (TextView) itemView.findViewById(R.id.book_price_view);
            this.mImageView = (ImageView) itemView.findViewById(R.id.book_image_view);
        }
    }
}