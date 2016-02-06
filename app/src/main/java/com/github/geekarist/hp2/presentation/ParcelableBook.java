package com.github.geekarist.hp2.presentation;

import android.os.Parcel;
import android.os.Parcelable;

import com.github.geekarist.hp2.domain.Book;

public class ParcelableBook extends Book implements Parcelable {
    public static final Creator<ParcelableBook> CREATOR = new Creator<ParcelableBook>() {
        @Override
        public ParcelableBook createFromParcel(Parcel in) {
            return new ParcelableBook(in);
        }

        @Override
        public ParcelableBook[] newArray(int size) {
            return new ParcelableBook[size];
        }
    };

    public ParcelableBook(String title, String isbn, double price, String cover) {
        super(title, isbn, cover, price);
    }

    protected ParcelableBook(Parcel in) {
        super(in.readString(), in.readString(), in.readString(), in.readDouble());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getTitle());
        dest.writeString(getIsbn());
        dest.writeDouble(getPrice());
        dest.writeString(getCover());
    }
    @Override
    public int describeContents() {
        return 0;
    }
}
