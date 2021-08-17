package com.moon.work.design2;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author zhenzhen.peng
 * @date 2021-08-17 17:59
 **/
public class DynamicProxyTests {

    @Test
    void testDynamicProxy() {
        InvocationHandler handler = (proxy, method, args) -> {
            String methodName = method.getName();
            System.out.println("方法名：" + methodName);
            if (methodName.equals("say")) {
                System.out.println(args[0] + ",i am dynamicProxy");
            } else if (methodName.equals("listen")) {
                return "111";
            }

            return null;
        };

        Fun fun = (Fun) Proxy.newProxyInstance(Fun.class.getClassLoader(),
                                               new Class[]{Fun.class},
                                               handler);
        fun.say("hallo");
        System.out.println(fun.listen());
    }

    interface Fun {
        void say(String msg);

        String listen();
    }
}
