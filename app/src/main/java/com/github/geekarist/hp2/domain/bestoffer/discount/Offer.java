package com.github.geekarist.hp2.domain.bestoffer.discount;

import android.util.Log;

import com.github.geekarist.hp2.domain.Book;
import com.github.geekarist.hp2.domain.bestoffer.discount.calculation.DiscountCalculation;
import com.github.geekarist.hp2.domain.bestoffer.discount.calculation.MinusDiscountCalculation;
import com.github.geekarist.hp2.domain.bestoffer.discount.calculation.PercentageDiscountCalculation;
import com.github.geekarist.hp2.domain.bestoffer.discount.calculation.SliceDiscountCalculation;

import java.util.List;

public class Offer {
    private static final String TAG = Offer.class.getSimpleName();

    String type;
    int value;
    int sliceValue;

    public Offer(String type, int value, int sliceValue) {
        this.type = type;
        this.value = value;
        this.sliceValue = sliceValue;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", sliceValue='" + sliceValue + '\'' +
                '}';
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer that = (Offer) o;

        if (value != that.value) return false;
        if (sliceValue != that.sliceValue) return false;
        return !(type != null ? !type.equals(that.type) : that.type != null);

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + value;
        result = 31 * result + sliceValue;
        return result;
    }

    public double apply(List<Book> items) {
        DiscountCalculation discount;
        if ("minus".equals(type)) {
            discount = new MinusDiscountCalculation(value);
        } else if ("percentage".equals(type)) {
            discount = new PercentageDiscountCalculation(value);
        } else if ("slice".equals(type)) {
            discount = new SliceDiscountCalculation(value, sliceValue);
        } else {
            Log.w(TAG, String.format("Unknown offer [%s]", type));
            return 0;
        }
        return discount.apply(items);
    }
}
