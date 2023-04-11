package edu.illinois.ise.yicheng.logall;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import edu.illinois.ise.yicheng.antlr.syneq.AntlrPrintTokens;

public class LogAll {
    private String[] projects = {
            "chart",
            "closure",
            "lang",
            "math",
            "time",
            "mockito",
            "collections",
            "compress",
            "codec",
            "csv",
            "jacksoncore",
            "jacksondatabind",
            "jacksonxml",
            "jsoup",
            "jxpath",
            "gson",
            "cli"
    };

    private static String[] tools = {
        "alpha_repair",
        "recoder",
        "reward_repair",
        "selfapr",
        "sequencer",
        "simfix",
        "cure",
        "coconut",
        "tufano",
        "tbar",
        "edits"
    };

    public static void handlePatch(String filePath, String antlrOutputPath) throws Throwable{
        // if result exists, skip
        if (Utils.fileExists(antlrOutputPath)) {
            return;
        }
        AntlrPrintTokens.printTokens(filePath, antlrOutputPath);
    }

    public static void logMutantsAll(String patchRootDir, String mutantListFilePath, String patchAntlrOutputDirPath,
        String mutantFileRootDirPath, String mutantFileAntlrOutputDirPath) throws Throwable {
        List<String> mutantIdList = Utils.readLines(mutantListFilePath);
        List<String> alphaRepairToSkip = Arrays.asList(new String[] {"closure_102", "jsoup_49", "time_7", "jsoup_64", "closure_13"});
        for (String mutantId : mutantIdList) {
            String proj = mutantId.split("_")[0];
            String id = mutantId.split("_")[1];
            String antlrOutputFilePath = Paths.get(mutantFileAntlrOutputDirPath, proj + "-" + id + ".txt").toString();
            String buggyFileDirPath = Paths.get(mutantFileRootDirPath, proj + "-1f", "mutants", id).toString();
            String[] res = Utils.findJavaFile(new File(buggyFileDirPath));
            String buggyFilePath = res[0];
            assert buggyFilePath.endsWith(".java") : "must be a java file";
            handlePatch(buggyFilePath, antlrOutputFilePath);
        }

        for (String tool : tools) {
            for (String mutantId : mutantIdList) {
                if (tool.equals("alpha_repair")) {
                    if (alphaRepairToSkip.contains(mutantId)) {
                        continue;
                    }
                }
                String proj = mutantId.split("_")[0];
                String id = mutantId.split("_")[1];
                
                List<String[]> patchList = PatchExtractor.extractPatchPath(tool, patchRootDir, proj, id);
                for (String[] patch : patchList) {
                    String patchId = patch[0];
                    String patchPath = patch[1];
                    String antlrOutputFilePath = Paths.get(patchAntlrOutputDirPath, patchId + ".txt").toString();
                    handlePatch(patchPath, antlrOutputFilePath);
                }
            }
        }
    }

    public static void logD4jOriAll(String patchRootDir, String bugListFilePath, String patchAntlrOutputDirPath,
        String buggyFileRootDirPath, String buggyFileAntlrOutputDirPath) throws Throwable {
        List<String> bugIdList = Utils.readLines(bugListFilePath);
        List<String> alphaRepairToSkip = Arrays.asList(new String[] {"closure_102", "jsoup_49", "time_7", "jsoup_64", "closure_13"});
        for (String bugId : bugIdList) {
            String proj = bugId.split("_")[0];
            String id = bugId.split("_")[1];
            String buggyFileDirPath = Paths.get(buggyFileRootDirPath, proj, id, "buggy").toString();
            String[] res = Utils.findJavaFile(new File(buggyFileDirPath));
            String buggyFilePath = res[0];
            assert buggyFilePath.endsWith(".java") : "must be a java file";
            String antlrOutputFilePath = Paths.get(buggyFileAntlrOutputDirPath, proj + "-" + id + ".txt").toString();
            handlePatch(buggyFilePath, antlrOutputFilePath);
        }

        for (String tool : tools) {
            for (String bugId : bugIdList) {
                if (tool.equals("alpha_repair")) {
                    if (alphaRepairToSkip.contains(bugId)) {
                        continue;
                    }
                }
                String proj = bugId.split("_")[0];
                String id = bugId.split("_")[1];
                
                List<String[]> patchList = PatchExtractor.extractPatchPath(tool, patchRootDir, proj, id);
                for (String[] patch : patchList) {
                    String patchId = patch[0];
                    String patchPath = patch[1];
                    String antlrOutputFilePath = Paths.get(patchAntlrOutputDirPath, patchId + ".txt").toString();
                    handlePatch(patchPath, antlrOutputFilePath);
                }
            }
        }
        }


    public static void main(String[] args) throws Throwable {
        String mode = args[0];
        if (mode.equals("d4j_ori")) {
            String patchRootDir = "/home/jun/fastd/dlapr-mirror/validation/src_d4j_ori_patches";
            String bugListFilePath = "/home/jun/research/dlapr/d4j_scripts/resources/single_line_bug_list.txt";
            String patchAntlrOutputDirPath = "/home/jun/fastd/dlapr-mirror/validation/d4j_ori_patch_antlr_output";
            String buggyFileRootDirPath = "/home/jun/research/dlapr/d4j_scripts/resources/d4j_ori_buggy_files";
            String buggyFileAntlrOutputDirPath = "/home/jun/fastd/dlapr-mirror/validation/d4j_ori_buggy_file_antlr_output";
            logD4jOriAll(patchRootDir, bugListFilePath, patchAntlrOutputDirPath, buggyFileRootDirPath, buggyFileAntlrOutputDirPath);
        }
        else if (mode.equals("mutants")) {
            String patchRootDir = "/home/jun/fastd/dlapr-mirror/validation/src_patches";
            String mutantListFilePath = "/home/jun/research/dlapr/d4j_scripts/resources/sample_1700_mutants.txt";
            String patchAntlrOutputDirPath = "/home/jun/fastd/dlapr-mirror/validation/mutant_patch_antlr_output";
            String mutantFileRootDirPath = "/home/jun/research/dlapr/all_mutants";
            String mutantFileAntlrOutputDirPath = "/home/jun/fastd/dlapr-mirror/validation/mutant_file_antlr_output";
            logMutantsAll(patchRootDir, mutantListFilePath, patchAntlrOutputDirPath, mutantFileRootDirPath, mutantFileAntlrOutputDirPath);
        }    
    }
}
