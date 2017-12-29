package com.jiespace.designPattern.relationshipModel.observerModel;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class Observer1 implements Observer {
    @Override
    public void update() {
        System.out.println("observer1 has received!");
    }
}
