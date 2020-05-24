package com.example.mylib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <ul>
 * <li>Author: luson.he </li>
 * <li>Time: 2020/5/24 上午10:41</li>
 * <li>Contack: unicorn.luson@gmail.com</li>
 * </ul>
 */
public class DynamicProxyDemo {

    public interface HelloInterface {
        void sayHello(String toPerson);
    }


    public static class Hello implements HelloInterface{
        @Override
        public void sayHello(String toPerson) {
            System.out.println("Hello " + toPerson + "!");
        }
    }

    public static void main(String[] args) {


        final HelloInterface hello = new Hello();

        HelloInterface proxyHello = (HelloInterface) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Object invokeResult ;
                if("sayHello".equals(method.getName()) && String.class.cast(args[0]).contains("新")){
                    args[0] = String.class.cast(args[0]).replace("新","老");
                    invokeResult = method.invoke(hello, args);
                }else{
                    invokeResult = method.invoke(hello, args);
                }

                return invokeResult;
            }
        });

//        proxyHello.sayHello("王大爷");
        proxyHello.sayHello("新司机");

    }
}
