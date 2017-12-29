package com.jiespace.designPattern.createModel.abstractFactoryModel;

import com.jiespace.designPattern.createModel.factoryMode.MailSender;
import com.jiespace.designPattern.createModel.factoryMode.Sender;

/**
 * Created by huangmingjie on 2017/11/20.
 */
public class SendMailFactory implements Provider{
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
