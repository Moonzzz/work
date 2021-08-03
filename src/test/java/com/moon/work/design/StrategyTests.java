package com.moon.work.design;

import com.sun.tracing.dtrace.FunctionName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 策略模式
 * @author pzz
 * @date 2021/8/3 13:08
 */
public class StrategyTests {

    @Test
    void run() throws InvocationTargetException, IllegalAccessException {
        StrategyTests instance = new StrategyTests();
        getMethod("fish").invoke(instance);
        getMethod("bird").invoke(instance);
    }

    private Method getMethod(String name) {
        Class<StrategyTests> clazz = StrategyTests.class;

        return Arrays.stream(clazz.getMethods())
                .filter(v -> {
                    FunctionName annotation = v.getAnnotation(FunctionName.class);
                    return annotation != null && annotation.value().equalsIgnoreCase(name);
                })
                .findFirst()
                .orElse(null);
    }

    @FunctionName("fish")
    public void method1() {
        System.out.println("i am fish");
    }

    @FunctionName("bird")
    public void method2() {
        System.out.println("i am bird");
    }
}
