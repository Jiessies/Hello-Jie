package com.jiespace.designPattern.relationshipModel.observerModel;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class MySubject extends AbstractSubject {
    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }
}
