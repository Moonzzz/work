package com.moon.work.design;

import org.junit.jupiter.api.Test;

/**
 * @author pzz
 * @date 2021/8/3 13:31
 */
public class FactoryTests {
    // 简单工厂
    public static class SimpleCarFactory {
        public Car create() {
            return () -> System.out.println("auto car start running");
        }
    }

    @Test
    void simpleFactoryTest() {
        SimpleCarFactory factory = new SimpleCarFactory();
        factory.create().run();
    }

    @Test
    void abstractFactoryTest() {
        AbstractFactory factory = new MazdaFactory();
        factory.createCar().run();
        factory.createTank().fire();

        AbstractFactory factory2 = new AudiFactory();
        factory2.createCar().run();
        factory2.createTank().fire();
    }

    // 抽象工厂
    public static abstract class AbstractFactory {
        public abstract Car createCar();

        public abstract Tank createTank();
    }

    interface Car {
        void run();
    }

    interface Tank {
        void fire();
    }

    public static class MazdaFactory extends AbstractFactory {

        @Override
        public Car createCar() {
            return new Mazda();
        }

        @Override
        public Tank createTank() {
            return new MazdaTank();
        }

        public static class Mazda implements Car {
            @Override
            public void run() {
                System.out.println("Mazda start running!");
            }
        }

        public static class MazdaTank implements Tank {
            @Override
            public void fire() {
                System.out.println("Mazda fire!");
            }
        }
    }

    public static class AudiFactory extends AbstractFactory {

        @Override
        public Car createCar() {
            return new Audi();
        }

        @Override
        public Tank createTank() {
            return new AudiTank();
        }

        public static class Audi implements Car {
            @Override
            public void run() {
                System.out.println("Audi start running!");
            }
        }

        public static class AudiTank implements Tank {
            @Override
            public void fire() {
                System.out.println("Audi fire!");
            }
        }
    }

}
