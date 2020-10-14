package com.api.test.fanxing;

import java.util.Iterator;
import java.util.Random;

/**
 * @description:
 * @author: endure
 * @create: 2020-06-05 14:29
 **/
public class CoffeeGenerator implements Generator<Coffee> {

    private Class[] types = {Latte.class, Mocha.class, Cappuccino.class};
    private static Random rand = new Random(47);

    public CoffeeGenerator(){}

    private int size = 0;
    public  CoffeeGenerator(int sz){
        size = sz;
    }

    @Override
    public Coffee next() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].newInstance();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    class CoffeeIterator implements Iterable<Coffee>{

        int count = size;
        public boolean hasNExt(){
            return count > 0;
        }
        public Coffee next(){
            count--;
            return CoffeeGenerator.this.next();
        }
        @Override
        public Iterator<Coffee> iterator() {
            return (Iterator<Coffee>) new CoffeeIterator();
        }
    }
    public static void main(String[] args) {
        CoffeeGenerator gen = new CoffeeGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());


        }
    }
}
