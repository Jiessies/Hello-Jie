package com.jiespace.designPattern.structuralModel.proxyModel;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
