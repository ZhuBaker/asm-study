package com.github.tc;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhubo
 * @description:
 * @time: 2020年01月11日
 * @modifytime:
 */
public class Agent {
    //   -javaagent:D:\server\mvn_repository\com\github\tc\asm-study\1.0-SNAPSHOT\asm-study-1.0-SNAPSHOT.jar -noverify
    public static void premain(String agentArgs, Instrumentation inst) throws Exception {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                System.out.println(className);
                if (!className.equals("com/github/tc/Account")) {
                    return classfileBuffer;
                }
                ClassReader classReader = new ClassReader(classfileBuffer);
                ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                AddSecurityCheckClassAdapter ascca = new AddSecurityCheckClassAdapter(cw);
                classReader.accept(ascca, 0);
                return cw.toByteArray();
            }
        });
    }
}
