package com.jiespace.designPattern.relationshipModel.templateMethodModel;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class Plus extends AbstractCalculator {
    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}
