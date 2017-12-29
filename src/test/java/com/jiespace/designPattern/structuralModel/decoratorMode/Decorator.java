package com.jiespace.designPattern.structuralModel.decoratorMode;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class Decorator implements Sourceable {
    private Sourceable source;
    
    public Decorator(Sourceable source){
        super();
        this.source = source;
    }
    @Override
    public void method() {
        System.out.println("before decorator!");
        source.method();
        System.out.println("after decorator!");
    }
}
