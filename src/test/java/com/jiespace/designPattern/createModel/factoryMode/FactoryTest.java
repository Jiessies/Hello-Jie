package com.jiespace.designPattern.createModel.factoryMode;

/**
 * Created by huangmingjie on 2017/11/20.
 */
public class FactoryTest {
    public static void main(String[] args) {
        SendFactory factory = new SendFactory();
        Sender sender = factory.produce("sms");
        sender.Send();
    }
}
