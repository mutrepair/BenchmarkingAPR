package edu.illinois.ise.yicheng.antlr.syneq;


import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.List;

/**
 * @author Yicheng Ouyang
 * @Date 1/31/23
 */

public class AntlrPrintTokens {

    private void test() throws Throwable{
        Long start = System.currentTimeMillis();
        ANTLRInputStream in = new ANTLRInputStream(new FileReader("/tmp/fix.java"));
        Java8Lexer lexer = new Java8Lexer(in);
        List<? extends Token> tokenList = lexer.getAllTokens();
        for(Token token : tokenList){
            System.out.println(token.getText());
        }
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void printTokens(String filePath, String outputPath) throws Throwable{
        PrintStream console = System.out;
        PrintStream stderr = System.err;
        try (FileInputStream fin = new FileInputStream(filePath)) {
            PrintStream o = new PrintStream(outputPath);
            System.setOut(o);
            System.setErr(o);
            ANTLRInputStream in = new ANTLRInputStream(fin);
            Java8Lexer lexer = new Java8Lexer(in);
            List<? extends Token> tokenList = lexer.getAllTokens();
            for(Token token : tokenList){
                System.out.println(token.getText());
            }
            fin.close();
        } catch (Throwable t) {
            t.printStackTrace();
            throw t;
        } finally {
            System.setOut(console);
            System.setErr(stderr);
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
//        Long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }
}
