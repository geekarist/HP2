package com.github.geekarist.hp2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
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
    public void onBindViewHolder(ListBooksAdapter.ViewHolder holder, final int position) {
        holder.mBook = mBooks.get(position);
        holder.mTitleView.setText(holder.mBook.title);
        holder.mPriceView.setText(mContext.getString(R.string.price, holder.mBook.price));
        Glide.with(mContext)
                .load(holder.mBook.cover)
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

    public void addBook(Book book) {
        mBooks.add(book);
        notifyDataSetChanged();
    }

    public double totalPrice() {
        double total = 0;
        for (Book b : mBooks) {
            total += b.price;
        }
        return total;
    }

    public List<Book> getBooks() {
        return mBooks;
    }

    public void addBooks(List<Book> books) {
        mBooks.addAll(books);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleView;
        public TextView mPriceView;
        public ImageView mImageView;
        public Book mBook;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleView = (TextView) itemView.findViewById(R.id.book_title_view);
            mPriceView = (TextView) itemView.findViewById(R.id.book_price_view);
            mImageView = (ImageView) itemView.findViewById(R.id.book_image_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(BookDetailActivity.newIntent(v.getContext(), mBook));
                }
            });
        }
    }
}
