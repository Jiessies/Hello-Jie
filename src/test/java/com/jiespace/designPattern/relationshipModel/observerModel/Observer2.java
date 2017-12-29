package com.jiespace.designPattern.relationshipModel.observerModel;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class Observer2 implements Observer {
    @Override
    public void update() {
        System.out.println("observer2 has received!");
    }
}
