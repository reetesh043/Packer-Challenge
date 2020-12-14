package com.mobiquity.utils;

/*
 * Utility class
 */
public class PackerUtil {

    /**
     * This method finds the maximum of two numbers.
     *
     * @param a
     * @param b
     * @return the maximum value from two input integers.
     */
    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    /**
     * This method returns int value of the price string.
     *
     * @param price
     * @return int value of the price string.
     */
    public static int priceFormatter(String price) {
        String str = price
                .replaceAll("[^\\d.,]", "")
                .replace(",", ".");
        return Double.valueOf(str).intValue();
    }
}
