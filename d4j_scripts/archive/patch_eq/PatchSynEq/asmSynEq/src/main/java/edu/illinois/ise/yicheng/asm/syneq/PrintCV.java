package edu.illinois.ise.yicheng.asm.syneq;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM9;

/**
 * @author Yicheng Ouyang
 * @Date 1/31/23
 */

public class PrintCV extends ClassVisitor{

    boolean includeLabel = false;

    public PrintCV(ClassVisitor classVisitor, boolean includeLabel) {
        super(ASM9, classVisitor);
        this.includeLabel = includeLabel;
    }
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitSource(String source, String debug) {
        super.visitSource(source, debug);
    }

    @Override
    public void visitOuterClass(String owner, String name, String descriptor) {
        super.visitOuterClass(owner, name, descriptor);
//        System.out.println(String.format("OuterClass: %s, %s, %s", owner, name, descriptor));
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        super.visitInnerClass(name, outerName, innerName, access);
//        System.out.println(String.format("InnerClass: %s, %s, %s, %d", name, outerName, innerName, access));
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        System.out.println(String.format("Field: %d, %s, %s, %s, %s", access, name, descriptor, signature, value==null?"null":value.toString()));
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println(String.format("Method: %d, %s, %s, %s", access, name, descriptor, signature));
        if (exceptions != null && exceptions.length > 0){
            StringBuilder tmp = new StringBuilder();
            for (String exception: exceptions){
                tmp.append(exception).append(",");
            }
            System.out.println("    throws: " + tmp);
        }
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new PrintMV(mv, includeLabel);
    }
}
