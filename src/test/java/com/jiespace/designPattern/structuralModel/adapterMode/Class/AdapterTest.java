package com.jiespace.designPattern.structuralModel.adapterMode.Class;

/**
 * Created by huangmingjie on 2017/11/15.
 */
public class AdapterTest {
    public static void main(String[] args) {
        Targetable target = new Adapter();
        target.method1();
        target.method2();
    }
}
