package com.github.geekarist.hp2;

import android.util.Log;

import com.github.geekarist.hp2.bestoffer.discount.Discount;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalog;
import com.github.geekarist.hp2.bestoffer.discount.MinusDiscount;
import com.github.geekarist.hp2.bestoffer.discount.PercentageDiscount;
import com.github.geekarist.hp2.bestoffer.discount.SliceDiscount;

import java.util.Arrays;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
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
    public List<Discount<Book>> list(List<Book> items) {
        Discount<Book> percentageDiscount = new PercentageDiscount<>(5);
        Discount<Book> minusDiscount = new MinusDiscount<>(15);
        Discount<Book> sliceDiscount = new SliceDiscount<>(12, 100);

        String joinedIsbnList = "";
        for (Book book : items) {
            if (!"".equals(joinedIsbnList)) {
                joinedIsbnList += ',';
            }
            joinedIsbnList += book.isbn;
        }

        mBookService.listCommercialOffers(joinedIsbnList).enqueue(new Callback<BookDiscountCatalog>() {
            @Override
            public void onResponse(Response<BookDiscountCatalog> response, Retrofit retrofit) {
                Log.i(TAG, String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });

        return Arrays.asList(percentageDiscount, minusDiscount, sliceDiscount);
    }

    public interface BookService {
        @GET("/books/{isbnList}/commercialOffers")
        Call<BookDiscountCatalog> listCommercialOffers(@Path("isbnList") String isbnList);
    }

    private static class BookDiscount implements Discount<Book> {
        String type;
        String value;
        String sliceValue;

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
            return 0;
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
