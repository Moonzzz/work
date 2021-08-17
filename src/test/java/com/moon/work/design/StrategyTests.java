package com.moon.work.design;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 策略模式
 *
 * @author pzz
 * @date 2021/8/3 13:08
 */
public class StrategyTests {

    @Test
    @EnumSource
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

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface FunctionName {
        String value();
    }

}
