package com.github.geekarist.hp2;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    final String title;
    final String isbn;
    final int price;
    final String cover;

    public Book(String title, String isbn, int price, String cover) {
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.cover = cover;
    }

    protected Book(Parcel in) {
        title = in.readString();
        isbn = in.readString();
        price = in.readInt();
        cover = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(isbn);
        dest.writeInt(price);
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
}
