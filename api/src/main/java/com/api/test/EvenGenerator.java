package com.api.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: endure
 * @create: 2020-06-04 09:34
 **/
public class EvenGenerator extends IntGenerator {

    private AtomicInteger currentEvenValue = new AtomicInteger(0);
    @Override
    public  int next() {

        return currentEvenValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
