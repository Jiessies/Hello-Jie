package com.jiespace.designPattern.structuralModel.facadeMode;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class User {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }
}
