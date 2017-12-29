package com.jiespace.entity;

/**
 * Created by huangmingjie on 2017/11/3.
 */
public class Singleton {
    private Singleton(){
    
    }
    private static class SingletonHandle{
        private static final Singleton SINGLETON = new Singleton();
    }
    public static Singleton getSingleton(){
        return SingletonHandle.SINGLETON;
    }
}
