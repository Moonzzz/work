package com.moon.work.design2;

import org.junit.jupiter.api.Test;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式
 *
 * @author zhenzhen.peng
 * @date 2021-08-17 17:42
 **/
public class ObserverTests {


    @Test
    void testObserver() {
        MyObservable publisher = new MyObservable();
        publisher.addObserver(new ObserverA());
        publisher.addObserver(new ObserverB());
        publisher.addObserver(new ObserverC());

        publisher.setStatus(2);
    }

    static class MyObservable extends Observable {
        int status;

        public MyObservable() {
            this.status = 0;
        }

        public void setStatus(int status) {
            this.status = status;
            setChanged();
            notifyObservers(this);
        }
    }

    static class ObserverA implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            System.out.println("observerA receive:" + arg);

        }
    }

    static class ObserverB implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            System.out.println("observerA receive:" + arg);

        }
    }

    static class ObserverC implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            System.out.println("observerA receive:" + arg);

        }
    }
}
