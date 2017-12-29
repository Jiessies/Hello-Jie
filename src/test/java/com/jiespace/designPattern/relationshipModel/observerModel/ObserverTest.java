package com.jiespace.designPattern.relationshipModel.observerModel;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class ObserverTest {
    public static void main(String[] args) {
        Subject sub = new MySubject();
        sub.add(new Observer1());
        sub.add(new Observer2());
        
        sub.operation();
    }
}
