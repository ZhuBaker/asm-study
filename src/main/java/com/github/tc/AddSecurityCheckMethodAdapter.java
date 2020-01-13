package com.github.tc;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author bozhu
 * @description
 * @data 2020/1/11
 */
public class AddSecurityCheckMethodAdapter extends MethodVisitor {

    public AddSecurityCheckMethodAdapter(MethodVisitor methodVisitor) {
        super(Opcodes.ASM6, methodVisitor);
    }

    @Override
    public void visitCode() {
        Label continueLabel = new Label();
        // Type.getInternalName(SecurityChecker.class)
        visitMethodInsn(Opcodes.INVOKESTATIC, "com/github/tc/SecurityChecker", "checkSecurity", "()Z", false);
        visitJumpInsn(Opcodes.IFNE, continueLabel);
        visitInsn(Opcodes.RETURN);
        visitLabel(continueLabel);
        super.visitCode();
    }
}
