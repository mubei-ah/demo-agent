package com.pphh.demo;

import com.pphh.demo.api.Greeting;
import com.pphh.demo.common.Logger;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Created by pphh on 2022/6/23.
 */
public class Agent {

    public static void premain(String args, Instrumentation inst) {
        Logger.info("loading agent..");

        // 对Greeting类字节码进行增强转换
        try {
            inst.addTransformer(new Transformer(), false);
            inst.retransformClasses(Greeting.class);
        } catch (UnmodifiableClassException e) {
            Logger.error("a error happened when transforming Greeting class");
        }

        Logger.info("agent has been loaded.");
    }

    public static void agentmain(String args, Instrumentation inst) {
    }

}
