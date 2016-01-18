package com.github.geekarist.hp2;

import android.util.Log;

import com.github.geekarist.hp2.bestoffer.discount.Discount;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalog;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalogCallback;
import com.github.geekarist.hp2.bestoffer.discount.MinusDiscount;
import com.github.geekarist.hp2.bestoffer.discount.PercentageDiscount;
import com.github.geekarist.hp2.bestoffer.discount.SliceDiscount;

import java.util.Arrays;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

public class HenriPotierDiscountCatalog implements DiscountCatalog<Book> {

    private static final String TAG = HenriPotierDiscountCatalog.class.getSimpleName();

    private final BookService mBookService;

    public HenriPotierDiscountCatalog() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://henri-potier.xebia.fr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mBookService = retrofit.create(BookService.class);
    }

    @Override
    public void list(List<Book> items, final DiscountCatalogCallback<Book> callback) {
        final Discount<Book> percentageDiscount = new PercentageDiscount<>(5);
        final Discount<Book> minusDiscount = new MinusDiscount<>(15);
        final Discount<Book> sliceDiscount = new SliceDiscount<>(12, 100);

        String joinedIsbnList = "";
        for (Book book : items) {
            if (!"".equals(joinedIsbnList)) {
                joinedIsbnList += ',';
            }
            joinedIsbnList += book.isbn;
        }

        mBookService.listCommercialOffers(joinedIsbnList).enqueue(new retrofit.Callback<BookDiscountCatalog>() {
            @Override
            public void onResponse(Response<BookDiscountCatalog> response, Retrofit retrofit) {
                callback.onListResult(Arrays.asList(percentageDiscount, minusDiscount, sliceDiscount));
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public interface BookService {
        @GET("/books/{isbnList}/commercialOffers")
        Call<BookDiscountCatalog> listCommercialOffers(@Path("isbnList") String isbnList);
    }

    private static class BookDiscount implements Discount<Book> {
        String type;
        int value;
        int sliceValue;

        @Override
        public String toString() {
            return "BookDiscount{" +
                    "type='" + type + '\'' +
                    ", value='" + value + '\'' +
                    ", sliceValue='" + sliceValue + '\'' +
                    '}';
        }

        @Override
        public double calculate(List<Book> items) {
            Discount<Book> discount;
            if ("minus".equals(type)) {
                discount = new MinusDiscount<Book>(value);
            } else if ("percentage".equals(type)) {
                discount = new PercentageDiscount<>(value);
            } else if ("slice".equals(type)) {
                discount = new SliceDiscount<>(value, sliceValue);
            } else {
                Log.w(TAG, String.format("Unknown offer [%s]", type));
                return 0;
            }
            return discount.calculate(items);
        }
    }

    private static class BookDiscountCatalog {
        List<BookDiscount> offers;

        @Override
        public String toString() {
            return "BookDiscountCatalog{" +
                    "offers=" + offers +
                    '}';
        }
    }
}
