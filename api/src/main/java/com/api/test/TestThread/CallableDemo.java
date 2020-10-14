package com.api.test.TestThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @author: endure
 * @create: 2020-06-03 11:18
 **/
class TaskWithResult implements Callable<String> {
    private int id;
    public  TaskWithResult(int id){
        this.id = id;
    }
    @Override
    public String call() throws Exception {
        return "result of TaskWithResult"+id;
    }
}

public class CallableDemo{
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<String>> resultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            resultList.add(exec.submit(new TaskWithResult(i)));
        }

        for (Future<String> future : resultList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
        }

    }
}
