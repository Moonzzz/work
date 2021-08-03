package com.moon.work.design;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pzz
 * @date 2021/8/3 13:56
 */
public class ProcessChainTests {
    @Test
    void testChain() {
        ProcessorChain chain = new ProcessorChain();
        chain.addProcessor(new AudiProcessor());
        chain.addProcessor(new MazdaProcessor());

        chain.doHandle(new FactoryTests.MazdaFactory.Mazda());
    }

    public interface Processor {
        void handle(FactoryTests.Car car, ProcessorChain chain);
    }

    public static class MazdaProcessor implements Processor {

        @Override
        public void handle(FactoryTests.Car car, ProcessorChain chain) {
            if (car instanceof FactoryTests.MazdaFactory.Mazda) {
                System.out.println("mazda processor handle!");
            }

            chain.doHandle(car);
        }
    }

    public static class AudiProcessor implements Processor {

        @Override
        public void handle(FactoryTests.Car car, ProcessorChain chain) {
            if (car instanceof FactoryTests.AudiFactory.Audi) {
                System.out.println("audi processor handle!");
            }

            chain.doHandle(car);
        }
    }

    public static class ProcessorChain {
        List<Processor> chain = new LinkedList<>();
        Iterator<Processor> it = null;

        public void addProcessor(Processor processor) {
            chain.add(processor);
        }

        public void doHandle(FactoryTests.Car car) {
            if (it == null) {
                it = chain.iterator();
            }
            if (it.hasNext()) {
                Processor processor = it.next();
                processor.handle(car, this);
            }

        }

    }
}
