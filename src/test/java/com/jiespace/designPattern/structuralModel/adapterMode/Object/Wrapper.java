package com.jiespace.designPattern.structuralModel.adapterMode.Object;

import com.jiespace.designPattern.structuralModel.adapterMode.Class.Source;
import com.jiespace.designPattern.structuralModel.adapterMode.Class.Targetable;

/**
 * Created by huangmingjie on 2017/11/15.
 */
public class Wrapper implements Targetable {
    private Source source;
    
    public Wrapper(Source source){
        super();
        this.source = source;
    }
    public void method1() {
        source.method1();
    }
    
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
