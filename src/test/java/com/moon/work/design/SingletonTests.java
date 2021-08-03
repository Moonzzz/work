package com.moon.work.design;

/**
 * @author pzz
 * @date 2021/8/3 12:50
 */
@SuppressWarnings({"InstantiationOfUtilityClass", "unused"})
public class SingletonTests {

    // 简单单例
    public static class SingletonSimple {
        private static final SingletonSimple INSTANCE = new SingletonSimple();

        private SingletonSimple() {
        }

        public static SingletonSimple getInstance() {
            return INSTANCE;
        }
    }

    // double-check单例
    public static class SingletonLazy {
        private static volatile SingletonLazy INSTANCE = null;

        private SingletonLazy() {

        }

        public static SingletonLazy getInstance() {
            if (INSTANCE == null) {
                synchronized (SingletonLazy.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new SingletonLazy();
                    }
                }
            }

            return INSTANCE;
        }
    }

    // 内部类单例
    public static class SingletonInnerClass {
        private SingletonInnerClass() {

        }

        public static SingletonInnerClass getInstance() {
            return InnerClass.INSTANCE;
        }

        private static class InnerClass {
            private static final SingletonInnerClass INSTANCE = new SingletonInnerClass();
        }
    }

    // 枚举单例
    public enum SingletonEnum {
        INSTANCE;

        public void doSomething() {
            System.out.println("枚举单例！");
        }
    }
}
