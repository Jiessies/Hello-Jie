package com.jiespace.entity;

/**
 * Created by huangmingjie on 2017/11/2.
 */
public class JVMShowcase {
    //静态类常量,
    public final static String ClASS_CONST = "I'm a Const";
    //私有实例变量
    private int instanceVar = 15;
    
    public static void main(String[] args) {
        //调用静态方法
        runStaticMethod();
        //调用非静态方法
        JVMShowcase showcase = new JVMShowcase();
        showcase.runNonStaticMethod(100);
    }
    
    //常规静态方法
    public static String runStaticMethod() {
        return ClASS_CONST;
    }
    
    //非静态方法
    public int runNonStaticMethod(int parameter) {
        int methodVar = this.instanceVar * parameter;
        return methodVar;
    }
}
