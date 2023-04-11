package edu.illinois.ise.yicheng.logall;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Utils {
    public static List<String> readLines(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static boolean fileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }
    
    public static String[] findJavaFile(File javaFileDir) throws IOException {
        assert javaFileDir.isDirectory() : "must be a directory";
        try (Stream<Path> pathStream = Files.walk(javaFileDir.toPath()).filter(path -> Files.isRegularFile(path) && path.toString().endsWith(".java"))) {
            Optional<Path> javaFile = pathStream.findFirst();
            assert javaFile.isPresent() : "java file not found";
            Path relativePath = javaFileDir.toPath().relativize(javaFile.get());
            return new String[]{javaFile.get().toString(), relativePath.toString()};
        }
        // Optional<Path> javaFile = Files.walk(javaFileDir.toPath())
        //         .filter(path -> Files.isRegularFile(path) && path.toString().endsWith(".java"))
        //         .findFirst();

        // assert javaFile.isPresent() : "java file not found";
        // Path relativePath = javaFileDir.toPath().relativize(javaFile.get());
        // return new String[]{javaFile.get().toString(), relativePath.toString()};
    }

    public static void main(String[] args) {
        List<String> lines = readLines("/home/jun/research/dlapr/d4j_scripts/resources/single_line_bug_list.txt");
        System.out.println(lines);
    }
}
