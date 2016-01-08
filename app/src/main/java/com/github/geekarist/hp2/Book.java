package com.github.geekarist.hp2;

import android.os.Parcel;
import android.os.Parcelable;

import com.github.geekarist.hp2.bestoffer.Item;

public class Book implements Parcelable, Item {
    final String title;
    final String isbn;
    final double price;
    final String cover;

    public Book(String title, String isbn, double price, String cover) {
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.cover = cover;
    }

    protected Book(Parcel in) {
        title = in.readString();
        isbn = in.readString();
        price = in.readDouble();
        cover = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(isbn);
        dest.writeDouble(price);
        dest.writeString(cover);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public double getPrice() {
        return price;
    }
}
