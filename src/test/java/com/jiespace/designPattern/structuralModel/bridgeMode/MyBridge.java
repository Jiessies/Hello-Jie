package com.jiespace.designPattern.structuralModel.bridgeMode;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class MyBridge extends Bridge {
    public void method(){
        getSource().method();
    }
}
