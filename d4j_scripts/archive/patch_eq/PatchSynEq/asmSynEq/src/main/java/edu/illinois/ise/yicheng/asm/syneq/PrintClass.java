package edu.illinois.ise.yicheng.asm.syneq;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.FileInputStream;
import java.io.PrintStream;

/**
 * @author Yicheng Ouyang
 * @Date 1/31/23
 */

public class PrintClass {
    public static void printClass(String filePath, String outputPath) throws Throwable{
        PrintStream console = System.out;
        try (FileInputStream in = new FileInputStream(filePath)) {
            PrintStream o = new PrintStream(outputPath);
            System.setOut(o);
            ClassReader cr = new ClassReader(in);
            ClassWriter cw = new ClassWriter(cr, 0);
            // It is better to make the includeLabel as false.
            // When printing the label, Label.toString() is based on identityHashCode,
            // which means it can not used to identify the jump location,
            // it will be different for different execution.
            // The getOffset() of Label can not work well, as it will throw "IllegalStateException: Label offset position has not been resolved yet"
            // See https://stackoverflow.com/questions/47463617/java-bytecode-asm-get-label-offset
            ClassVisitor pcv = new PrintCV(cw, false);
            cr.accept(pcv, 0);
        } catch (Throwable t){
            t.printStackTrace();
            throw t;
        }
        System.setOut(console);
    }

    public static void main(String[] args) throws Throwable {
        if (args.length == 0 || args.length % 2 != 0){
            System.out.println("Need 2 arguments: path of the class file, output file path");
            System.exit(1);
        }
        for (int i = 0; i < args.length; i+=2){
            printClass(args[i], args[i+1]);
        }
    }
}

