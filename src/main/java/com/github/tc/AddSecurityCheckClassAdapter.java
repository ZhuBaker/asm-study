package com.github.tc;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author bozhu
 * @description
 * @data 2020/1/11
 */
public class AddSecurityCheckClassAdapter extends ClassVisitor {

    public AddSecurityCheckClassAdapter(ClassVisitor classVisitor) {
        super(Opcodes.ASM6, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        MethodVisitor mv = methodVisitor;
        if (methodVisitor != null) {
            if (name.equals("operation")) {
                mv = new AddSecurityCheckMethodAdapter(methodVisitor);
            }
        }
        return mv;
    }
}
