package com.jiespace.designPattern.createModel.factoryMode;

/**
 * Created by huangmingjie on 2017/11/20.
 */
public class MailSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is mailsender!");
    }
}
