package com.pphh.demo.api;

import com.pphh.demo.common.TimeDateUtil;

/**
 * Created by pphh on 2022/6/23.
 */
public class Greeting {

    public String sayHello() {
        String strTime = TimeDateUtil.getCurrentTimeString();
        String hello = "hello,world, time = " + strTime;
        System.out.println(hello);
        return hello;
    }

}
