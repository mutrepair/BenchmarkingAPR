package edu.illinois.ise.yicheng.asm.syneq;

import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM9;

/**
 * @author Yicheng Ouyang
 * @Date 1/31/23
 */

public class PrintMV extends MethodVisitor {

    String indent = "    ";
    boolean includeLabel = false;

    public PrintMV(MethodVisitor methodVisitor, boolean includeLabel) {
        super(ASM9, methodVisitor);
        this.includeLabel = includeLabel;
    }

    @Override
    public void visitCode() {
        super.visitCode();
    }

    @Override
    public void visitInsn(int opcode) {
        System.out.println(indent + "Insn:" + opcode);
        super.visitInsn(opcode);
    }

    @Override
    public void visitIincInsn(int var, int increment) {
        System.out.println(indent + "IINC " + var + " " + increment);
        super.visitIincInsn(var, increment);
    }

    @Override
    public void visitLdcInsn(Object value) {
        System.out.println(String.format("%sLDC %s", indent, value.toString()));
        super.visitLdcInsn(value);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        if (includeLabel){
            System.out.println(String.format("%sJUMP(%d) %s", indent, opcode, label.toString()));
        } else {
            System.out.println(String.format("%sJUMP(%d)", indent, opcode));
        }
        super.visitJumpInsn(opcode, label);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        System.out.println(String.format("%sINVOKE(%d): %s, %s, %s", indent, opcode, owner, name, descriptor));
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        System.out.println(String.format("%sFIELD(%d): %s, %s, %s", indent, opcode, owner, name, descriptor));
        super.visitFieldInsn(opcode, owner, name, descriptor);
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        System.out.println(String.format("%sTYPEINSN(%d): %s", indent, opcode, type));
        super.visitTypeInsn(opcode, type);
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        System.out.println(String.format("%sTYPEINSN(%d): %d", indent, opcode, var));
        super.visitVarInsn(opcode, var);
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        System.out.println(String.format("%sIntInsn(%d): %d", indent, opcode, operand));
        super.visitIntInsn(opcode, operand);
    }

    @Override
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < keys.length; i++){
            if (includeLabel){
                tmp.append(String.format("(%d:%s)", keys[i], labels[i].toString()));
            } else {
                tmp.append(String.format("(%d)", keys[i]));
            }
        }
        if (includeLabel)
            tmp.append(dflt.toString());
        System.out.println(String.format("%sLookupSwitch: %s", indent, tmp));
        super.visitLookupSwitchInsn(dflt, keys, labels);
    }

    @Override
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        if (includeLabel){
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i <= labels.length; i++){
                tmp.append(labels[i].toString());
            }
            tmp.append(dflt.toString());
            System.out.println(String.format("%sTableSwitchInsn: %d, %d, %s", indent, min, max, tmp));
        } else {
            System.out.println(String.format("%sTableSwitchInsn: %d, %d", indent, min, max));
        }

        super.visitTableSwitchInsn(min, max, dflt, labels);
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        System.out.println(String.format("%sINVOKEDYNAMIC(%d): %s, %s", indent, name, descriptor));
        super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
    }

    @Override
    public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
        System.out.println(String.format("%sMULTIANEWARRAY: %s, %d", indent, descriptor, numDimensions));
        super.visitMultiANewArrayInsn(descriptor, numDimensions);
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        if (includeLabel){
            System.out.println(String.format("%sTryCatch: %s, %s, %s, %s", indent, type,
                    start.toString(), end.toString(), handler.toString()));
        } else {
            System.out.println(String.format("%sTryCatch: %s", indent, type));
        }
        super.visitTryCatchBlock(start, end, handler, type);
    }

}
