package com.jiespace.designPattern.createModel.abstractFactoryModel;

import com.jiespace.designPattern.createModel.factoryMode.Sender;

/**
 * Created by huangmingjie on 2017/11/20.
 */
public interface Provider {
    public Sender produce();
}
