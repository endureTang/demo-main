package com.api.test.fanxing;

/**
 * @description:
 * @author: endure
 * @create: 2020-06-05 10:07
 **/
public class Generic<T> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
    public static void test(Generic<?> obj){
        System.out.println("泛型测试"+obj.getT());
    }

        public static  <T> T genericMethod(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        T instance = tClass.newInstance();
        return instance;
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//        Generic<Integer> integerGeneric = new Generic<>();
//        Generic<Number> numberGeneric = new Generic<>();
//        test(integerGeneric);
//        test(numberGeneric);

        Object obj = genericMethod(Class.forName("com.api.test.fanxing.Generic"));
        System.out.println(obj.getClass().getName());
    }
}
