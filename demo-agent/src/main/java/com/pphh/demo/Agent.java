package com.pphh.demo;

import java.lang.instrument.Instrumentation;

/**
 * Created by pphh on 2022/7/4.
 */
public class Agent {

    public static void premain(String args, Instrumentation inst) {
        System.out.println("loading agent..");
        inst.addTransformer(new Transformer(), false);
        System.out.println("agent has been loaded.");
    }

    public static void agentmain(String args, Instrumentation inst) {
    }
}
