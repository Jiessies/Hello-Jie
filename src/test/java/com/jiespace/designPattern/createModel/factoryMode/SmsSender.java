package com.jiespace.designPattern.createModel.factoryMode;

/**
 * Created by huangmingjie on 2017/11/20.
 */
public class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is sms sender!");
    }
}
