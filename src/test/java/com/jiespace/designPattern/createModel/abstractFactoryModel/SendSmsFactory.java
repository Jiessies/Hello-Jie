package com.jiespace.designPattern.createModel.abstractFactoryModel;

import com.jiespace.designPattern.createModel.factoryMode.Sender;
import com.jiespace.designPattern.createModel.factoryMode.SmsSender;

/**
 * Created by huangmingjie on 2017/11/20.
 */
public class SendSmsFactory implements Provider{
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
