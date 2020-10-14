package com.api.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: endure
 * @create: 2020-06-04 10:06
 **/
public class MuteEvenGenerator extends IntGenerator{

    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();

    @Override
    public int next() {
        lock.lock();
       try {
           ++currentEvenValue;
           Thread.yield();
           ++currentEvenValue;
           return currentEvenValue;
       }finally {
           lock.unlock();
       }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MuteEvenGenerator());
    }
}
