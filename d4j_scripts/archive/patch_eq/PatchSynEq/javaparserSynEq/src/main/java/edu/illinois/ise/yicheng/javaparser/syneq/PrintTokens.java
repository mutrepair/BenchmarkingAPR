package edu.illinois.ise.yicheng.javaparser.syneq;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * @author Yicheng Ouyang
 * @Date 1/31/23
 */

public class PrintTokens {

    public static void iterateNodes(Node node){
        if (node.getChildNodes().size() == 0){
            System.out.println(node.toString());
            return;
        }
        for (Node child: node.getChildNodes()){
            iterateNodes(child);
        }
    }

    public static void printTokens(String filePath, String outputPath) throws Throwable{
        StaticJavaParser.getParserConfiguration().setAttributeComments(false);
        PrintStream console = System.out;
        try (FileInputStream in = new FileInputStream(filePath)) {
            PrintStream o = new PrintStream(outputPath);
            System.setOut(o);
            CompilationUnit cu = StaticJavaParser.parse(new File(filePath));
            iterateNodes(cu);
//            TokenizeVisitor tokenizeVisitor = new TokenizeVisitor();
//            tokenizeVisitor.visit(cu, null);
        } catch (ParseProblemException e) {
            e.printStackTrace();
            System.exit(124);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(125);
        } catch (Throwable t) {
            t.printStackTrace();
            throw t;
        } finally {
            System.setOut(console);
        }
    }

    public static void main(String[] args) throws Throwable{
//        Long start = System.currentTimeMillis();
        if (args.length == 0 || args.length % 2 != 0){
            System.out.println("Need 2 arguments: path of the class file, output file path");
            System.exit(1);
        }
        for (int i = 0; i < args.length; i+=2){
            printTokens(args[i], args[i+1]);
        }
        Long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }
}
