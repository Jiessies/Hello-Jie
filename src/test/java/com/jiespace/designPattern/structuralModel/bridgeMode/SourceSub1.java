package com.jiespace.designPattern.structuralModel.bridgeMode;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class SourceSub1 implements Sourceable {
    @Override
    public void method() {
        System.out.println("this is the first sub!");
    }
}
