package com.github.geekarist.hp2;

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

        mBookService.listCommercialOffers(joinedIsbnList).enqueue(new Callback<List<BookDiscount>>() {
            @Override
            public void onResponse(Response<List<BookDiscount>> response, Retrofit retrofit) {
                System.out.println(response);
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
        Call<List<BookDiscount>> listCommercialOffers(@Path("isbnList") String isbnList);
    }

    private static class BookDiscount implements Discount<Book> {
        @Override
        public double calculate(List<Book> items) {
            return 0;
        }
    }
}
