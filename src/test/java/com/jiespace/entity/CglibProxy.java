package com.jiespace.entity;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by huangmingjie on 2017/11/2.
 */
public class CglibProxy implements MethodInterceptor {
    
    private static Enhancer enhancer = new Enhancer();
    
    public Object getProxy(Class clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
    
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置代理");
        //通过代理类调用父类中的方法
        Object result = methodProxy.invokeSuper(o, objects);
        int i = 0;
        for(Object o1 : objects){
            i = o1 instanceof Integer ? ((Integer) o1) : 0;
        }
        if(i == 100){
            return 123;
        }
        System.out.println("后置代理");
        return result;
    }
}

class TestProxy{
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        JVMShowcase jvmShowcase = (JVMShowcase)cglibProxy.getProxy(JVMShowcase.class);
        int i = jvmShowcase.runNonStaticMethod(10);
        System.out.println(i);
    }
}