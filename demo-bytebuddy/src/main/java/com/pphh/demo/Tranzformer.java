package com.pphh.demo;

import com.pphh.demo.aspect.AspectProxy;
import com.pphh.demo.aspect.SimpleAspect;
import com.pphh.demo.common.AgentAnnotation;
import com.pphh.demo.common.Logger;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * Created by pphh on 2022/6/24.
 */
public class Tranzformer implements AgentBuilder.Transformer {
    @Override
    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
        Logger.info("transforming %s...", typeDescription.getTypeName());
        SimpleAspect simpleAspect = new SimpleAspect();
        AspectProxy proxy = new AspectProxy();
        proxy.setiAspect(simpleAspect);

        ElementMatcher.Junction<MethodDescription> junction = not(isStatic())
                .and(ElementMatchers.isAnnotatedWith(AgentAnnotation.class));

        return builder.method(junction)
                .intercept(MethodDelegation.withDefaultConfiguration()
                        .to(proxy));
    }
}
