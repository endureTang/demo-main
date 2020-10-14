package com.api.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: endure
 * @create: 2020-06-03 10:40
 **/
public class LiftOff implements Runnable {

    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount ++;

    public LiftOff(){}
    public LiftOff(int countDown){
        this.countDown = countDown;
    }
    public String status(){
        return "#"+id+"("+
                (countDown >0 ?countDown:"Liftoff!") + "),";
    }

    @Override
    public void run() {
        while (countDown -- > 0){
            System.out.println(status());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
//            new Thread(new LiftOff()).start();
            executorService.execute(new LiftOff());
        }
        System.out.println("Waiting for LiftOff");
        executorService.shutdown();
    }
}
