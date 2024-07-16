package com.pphh.demo;

import com.pphh.demo.api.Greeting;

/**
 * Created by pphh on 2022/6/23.
 */
public class Application {

    public static void main(String[] args) {

        int count = 0;
        while (++count < 1000) {
            sleep(3);
            System.out.println();
            System.out.println("count = " + count);

            // test
            Greeting greeting = new Greeting();
            greeting.sayHello();
        }

    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
