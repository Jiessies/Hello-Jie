package com.jiespace.designPattern.structuralModel.adapterMode.Object;

import com.jiespace.designPattern.structuralModel.adapterMode.Class.Source;
import com.jiespace.designPattern.structuralModel.adapterMode.Class.Targetable;

/**
 * Created by huangmingjie on 2017/11/15.
 */
public class AdapterTest {
    public static void main(String[] args) {
        Source source = new Source();
        Targetable target = new Wrapper(source);
        target.method1();
        target.method2();
    }
}
