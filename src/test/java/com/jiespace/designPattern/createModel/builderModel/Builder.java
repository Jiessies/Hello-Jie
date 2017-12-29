package com.jiespace.designPattern.createModel.builderModel;

import com.jiespace.designPattern.createModel.factoryMode.MailSender;
import com.jiespace.designPattern.createModel.factoryMode.Sender;
import com.jiespace.designPattern.createModel.factoryMode.SmsSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangmingjie on 2017/11/20.
 */
public class Builder {
    private List<Sender> list = new ArrayList<Sender>();
    
    public void produceMailSender(int count){
        for(int i=0; i<count; i++){
            list.add(new MailSender());
        }
    }
    
    public void produceSmsSender(int count){
        for(int i=0; i<count; i++){
            list.add(new SmsSender());
        }
    }
}
