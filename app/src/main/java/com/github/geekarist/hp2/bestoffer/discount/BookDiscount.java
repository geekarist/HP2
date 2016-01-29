package com.github.geekarist.hp2.bestoffer.discount;

import android.util.Log;

import com.github.geekarist.hp2.Book;

import java.util.List;

public class BookDiscount implements DiscountCalculation {
    private static final String TAG = BookDiscount.class.getSimpleName();

    String type;
    int value;
    int sliceValue;

    public BookDiscount(String type, int value, int sliceValue) {
        this.type = type;
        this.value = value;
        this.sliceValue = sliceValue;
    }

    @Override
    public String toString() {
        return "BookDiscount{" +
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

        BookDiscount that = (BookDiscount) o;

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

    @Override
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
