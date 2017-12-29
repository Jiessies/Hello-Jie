package com.jiespace.designPattern.structuralModel.adapterMode.Class;

/**
 * Created by huangmingjie on 2017/11/15.
 */
public class Adapter extends Source implements Targetable {
    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
