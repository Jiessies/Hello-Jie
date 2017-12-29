package com.jiespace.designPattern.relationshipModel.templateMethodModel;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class StrategyTest {
    public static void main(String[] args) {
        String exp = "8+8";
        AbstractCalculator cal = new Plus();
        int result = cal.calculate(exp, "\\+");
        System.out.println(result);
    }
}
