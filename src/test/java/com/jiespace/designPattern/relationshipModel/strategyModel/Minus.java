package com.jiespace.designPattern.relationshipModel.strategyModel;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class Minus extends AbstractCalculator implements ICalculator {
    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp,"-");
        return arrayInt[0]-arrayInt[1];
    }
}
