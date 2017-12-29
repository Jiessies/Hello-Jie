package com.jiespace.designPattern.structuralModel.proxyModel;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public class ProxyTest {
    public static void main(String[] args) {
        Sourceable sourceable = new Proxy();
        sourceable.method();
    }
}
