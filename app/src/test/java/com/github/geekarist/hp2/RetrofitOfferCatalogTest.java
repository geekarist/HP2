package com.github.geekarist.hp2;

import com.github.geekarist.hp2.data.RetrofitOfferCatalog;
import com.github.geekarist.hp2.domain.bestoffer.BookDiscountCatalog;
import com.github.geekarist.hp2.domain.bestoffer.discount.Offer;
import com.github.geekarist.hp2.domain.bestoffer.discount.OfferCatalog;

import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class RetrofitOfferCatalogTest {

    @Test
    public void shouldListDiscounts() throws Exception {
        // Given
        OfferCatalog offerCatalog = new RetrofitOfferCatalog((joinedIsbnList, callback) ->
                callback.onResponse(new BookDiscountCatalog(asList(
                        new Offer("slice", 5, 100),
                        new Offer("percentage", 5, 0),
                        new Offer("minus", 5, 0)))));

        // When
        offerCatalog.list(new ArrayList<>(), discounts -> {

            // Then
            assertThat(discounts).containsOnlyElementsOf(
                    asList(new Offer("slice", 5, 100), new Offer("percentage", 5, 0), new Offer("minus", 5, 0)));
        });
    }

}