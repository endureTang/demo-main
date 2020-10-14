package com.api.test.extend;

/**
 * @description:
 * @author: endure
 * @create: 2020-06-12 15:22
 **/
public class TestAbstractImpl extends TestAbstract {
    @Override
    public void testOne() {
        System.out.println("testOne");
    }

    public static void main(String[] args) {
        TestAbstract testAbstrac = new TestAbstractImpl();
        testAbstrac.testOne();
        testAbstrac.testTwo();
    }
}
