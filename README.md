
## 1. 简介
本项目主要对ASM/Javassist/Bytebuddy等字节码增强技术进行演示。

相关博客文章见[Java字节码增强技术Bytebuddy探路篇](https://www.hyhblog.cn/2022/07/04/java_bytecode_instrumentation_bytebuddy/)

## 2. 项目结构

```
+ demo-core 基础类
+ demo-app 演示应用，循环调用Greeting.sayHello()
+ demo-agent 一个简单的Java Agent，输出所有JVM加载的类列表
+ demo-asm 一个Java Agent，通过ASM对Greeting类进行增强
+ demo-javaassist 一个Java Agent，通过Javassist对Greeting类进行增强
+ demo-bytebuddy 一个Java Agent，通过Bytebuddy对Greeting类进行增强
+ demo-cglib 通过cglib对Greeting类进行增强

```
## 3. 项目构建
本项目使用maven 3+进行构建，命令为，
```bash
mvn clean package
```

一个简单的运行命令，
```bash
java -javaagent:./demo-agent/target/agent-agent.jar -jar ./demo-app/target/demo-app.jar
```

可以通过控制台查看所有JVM加载的类列表，

```bash
loading agent..
agent has been loaded.
transforming class = java/lang/invoke/MethodHandleImpl
transforming class = java/lang/invoke/MethodHandleImpl$1
transforming class = java/lang/invoke/MethodHandleImpl$2
transforming class = java/util/function/Function
transforming class = java/lang/invoke/MethodHandleImpl$3
transforming class = java/lang/invoke/MethodHandleImpl$4
transforming class = java/lang/ClassValue
```

## 4. 演示

### 4.1 Greeting类

为了方便展示不同字节码增强技术，下面将以Greeting类为例，对sayHello()方法进行加强。

```java
public class Greeting {

    public String sayHello() {
        String hello = "hello,world";
        System.out.println(hello);
        return hello;
    }

}
```

分别在sayHello函数执行前后添加打印语句，输出如下类似信息。

```bash
begin of sayhello().
hello,world
end of sayhello().
```

### 4.2 通过ASM增强Greeting类

执行命令

```bash
java -javaagent:./demo-asm/target/agent-asm.jar -jar ./demo-app/target/demo-app.jar
```

输出日志
```bash
count = 1
begin of sayhello().
hello,world
end of sayhello().

count = 2
begin of sayhello().
hello,world
end of sayhello().
```

### 4.3 通过Javaassist增强Greeting类

执行命令

```bash
java -javaagent:./demo-javaassist/target/agent-jassist.jar -jar ./demo-app/target/demo-app.jar
```

输出日志
```bash
count = 1
begin of sayhello()
hello,world
end of sayhello()

count = 2
begin of sayhello()
hello,world
end of sayhello()
```

### 4.4 通过Bytebuddy增强Greeting类

执行命令

```bash
java -javaagent:./demo-bytebuddy/target/agent-buddy.jar -jar ./demo-app/target/demo-app.jar
```

输出日志
```bash
[main][INFO] loading agent..
[main][INFO] agent has been loaded.

count = 1
[main][INFO] transforming com.pphh.demo.api.Greeting...
start of method()
hello,world
end of method()

count = 2
start of method()
hello,world
end of method()

```

## 5. 联系 Contact
邮箱地址：peipeihh@qq.com，欢迎来信联系。

更多的信息，可以访问博客地址：[hyhblog.cn](https://hyhblog.cn)。

## 6. 开源许可协议 License
Apache License 2.0