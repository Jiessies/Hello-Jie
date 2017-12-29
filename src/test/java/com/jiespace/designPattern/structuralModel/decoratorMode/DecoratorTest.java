package com.jiespace.designPattern.structuralModel.decoratorMode;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Sourceable source = new Source();
        Sourceable obj = new Decorator(source);
        obj.method();
    }
}
