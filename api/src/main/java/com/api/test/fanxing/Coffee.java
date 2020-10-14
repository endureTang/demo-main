package com.api.test.fanxing;

/**
 * @description:
 * @author: endure
 * @create: 2020-06-05 14:26
 **/
public class Coffee {

    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}
