package com.github.geekarist.hp2.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.geekarist.hp2.R;
import com.github.geekarist.hp2.domain.Book;
import com.github.geekarist.hp2.domain.Cart;

import java.util.ArrayList;
import java.util.List;

public class ListBooksAdapter extends RecyclerView.Adapter<ListBooksAdapter.ViewHolder> implements Cart {
    private List<Book> mBooks;

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

    public void addBook(Book book) {
        mBooks.add(book);
        notifyDataSetChanged();
    }

    public double totalPrice() {
        double total = 0;
        for (Book b : mBooks) {
            total += b.getPrice();
        }
        return total;
    }

    @Override
    public List<Book> getItems() {
        return mBooks;
    }

    public List<Book> getBooks() {
        return mBooks;
    }

    public void setBooks(List<Book> books) {
        mBooks = books;
        notifyDataSetChanged();
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
