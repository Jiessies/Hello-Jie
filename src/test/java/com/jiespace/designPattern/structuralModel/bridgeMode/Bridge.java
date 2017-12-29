package com.jiespace.designPattern.structuralModel.bridgeMode;

/**
 * Created by huangmingjie on 2017/11/17.
 */
public abstract class Bridge {
    private Sourceable source;
    
    public void method(){
        source.method();
    }
    
    public Sourceable getSource() {
        return source;
    }
    
    public void setSource(Sourceable source) {
        this.source = source;
    }
}
