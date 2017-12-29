package com.jiespace.designPattern.createModel.builderModel;

/**
 * Created by huangmingjie on 2017/11/20.
 */
public class Test {
    public static void main(String[] args) {
        Builder builder = new Builder();
        builder.produceMailSender(10);
    }
}
