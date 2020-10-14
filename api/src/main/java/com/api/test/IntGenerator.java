package com.api.test;

/**
 * @description:
 * @author: endure
 * @create: 2020-06-04 09:18
 **/
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();
    public void cancel(){
        canceled = true;
    }
    public boolean isCanceled(){
        return canceled;
    }
}
