package com.github.geekarist.hp2;

import com.github.geekarist.hp2.bestoffer.discount.BookDiscount;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalog;
import com.github.geekarist.hp2.bestoffer.discount.DiscountCatalogCallback;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

public class HenriPotierDiscountCatalog implements DiscountCatalog {

    private static final String TAG = HenriPotierDiscountCatalog.class.getSimpleName();

    private final BookService mBookService;

    public HenriPotierDiscountCatalog() {
        // TODO: move to Application
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://henri-potier.xebia.fr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mBookService = retrofit.create(BookService.class);
    }

    // TODO: unit test
    @Override
    public void list(List<Book> items, final DiscountCatalogCallback callback) {
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
                List<BookDiscount> offers = response.body().offers;
                callback.onListResult(offers);
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
