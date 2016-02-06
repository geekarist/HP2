package com.github.geekarist.hp2.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.geekarist.hp2.R;
import com.github.geekarist.hp2.domain.bestoffer.Cart;

import java.util.ArrayList;
import java.util.List;

public class ListBooksAdapter extends RecyclerView.Adapter<ListBooksAdapter.ViewHolder> implements Cart {
    private List<ParcelableBook> mBooks;

    public ListBooksAdapter() {
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
    public void onBindViewHolder(ListBooksAdapter.ViewHolder holder, final int position) {
        holder.mBook = mBooks.get(position);
        holder.mTitleView.setText(holder.mBook.getTitle());
        holder.mPriceView.setText(holder.itemView.getContext().getString(R.string.price, holder.mBook.getPrice()));
        Glide.with(holder.itemView.getContext())
                .load(holder.mBook.getCover())
                .placeholder(R.drawable.book_cover_placeholder)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public void addBook(ParcelableBook book) {
        mBooks.add(book);
        notifyDataSetChanged();
    }

    // TODO: unit test
    public double totalPrice() {
        double total = 0;
        for (ParcelableBook b : mBooks) {
            total += b.getPrice();
        }
        return total;
    }

    @Override
    public List<ParcelableBook> getItems() {
        return mBooks;
    }

    public List<ParcelableBook> getBooks() {
        return mBooks;
    }

    public void setBooks(List<ParcelableBook> books) {
        mBooks = books;
        notifyDataSetChanged();
    }

    public void addBooks(List<ParcelableBook> books) {
        mBooks.addAll(books);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleView;
        public TextView mPriceView;
        public ImageView mImageView;
        public ParcelableBook mBook;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleView = (TextView) itemView.findViewById(R.id.book_title_view);
            mPriceView = (TextView) itemView.findViewById(R.id.book_price_view);
            mImageView = (ImageView) itemView.findViewById(R.id.book_image_view);
            itemView.setOnClickListener(v -> v.getContext().startActivity(BookDetailActivity.newIntent(v.getContext(), mBook)));
        }
    }
}
