package com.jiespace.entity;

import java.util.concurrent.*;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class Test {
    
    public static String getString() {
        
        return "IU";
    }
    
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            Future<String> futrue = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    
                    return getString();
                }
            });
            if (futrue.isDone()) {
                System.out.println(futrue.get().toString());
            } else {
                System.out.println("asdfghdjfsfadgh");
            }
        }
        executorService.shutdown();
    }
    
}
