package com.jiespace.designPattern.createModel.abstractFactoryModel;

import com.jiespace.designPattern.createModel.factoryMode.Sender;

/**
 * Created by huangmingjie on 2017/11/20.
 */
public class Test {
    public static void main(String[] args) {
        Provider provider = new SendMailFactory();
        Sender sender = provider.produce();
        sender.Send();
    }
}
