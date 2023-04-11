package edu.illinois.ise.yicheng.logall;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


public class PatchExtractor {

    private static String[] projects = {
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
    
    public static List<String[]> extractPatchPath(String tool, String patchRootDir, String proj, String mutantId) throws Exception {
        patchRootDir = Paths.get(patchRootDir, tool + "_patches").toString();
        if (tool.equals("tbar")) {
            return extractTbarPatchPath(tool, patchRootDir, proj, mutantId);
        } else if (tool.equals("reward_repair") || tool.equals("selfapr") || tool.equals("alpha_repair")) {
            return extractRewardRepairPatchPath(tool, patchRootDir, proj, mutantId);
        } else if (tool.equals("sequencer")) {
            return extractSequencerPatchPath(tool, patchRootDir, proj, mutantId);
        } else if (tool.equals("recoder") || tool.equals("simfix")) {
            return extractRecoderPatchPath(tool, patchRootDir, proj, mutantId);
        } else if (tool.equals("cure") || tool.equals("coconut") || tool.equals("tufano") || tool.equals("edits")) {
            return extractTbarPatchPath(tool, patchRootDir, proj, mutantId);
        } else {
            throw new Exception("unknown tool: " + tool);
        }
    }

    private static List<String[]> extractRecoderPatchPath(String tool, String patchRootDir, String proj, String mutantId) {
        List<String[]> patchList = new ArrayList<>();
        File srcPatchDir = new File(patchRootDir, proj + File.separator + mutantId);
        if (!srcPatchDir.isDirectory()) {
            return patchList;
        }
        for (File patch : srcPatchDir.listFiles()) {
            String patchPath = patch.getAbsolutePath();
            String patchId = patch.getName().replace(".java", "");
            patchList.add(new String[]{String.join("-", tool, proj, mutantId, patchId), patchPath});
        }
        return patchList;
    }

    private static List<String[]> extractTbarPatchPath(String tool, String patchRootDir, String proj, String mutantId) throws IOException {
        List<String[]> patchList = new ArrayList<>();
        File patchesPoolDir = new File(patchRootDir, proj + "_" + mutantId + File.separator + "patches-pool");
        if (!patchesPoolDir.isDirectory()) {
            return patchList;
        }
        for (File patchIdDir : patchesPoolDir.listFiles()) {
            if (!patchIdDir.isDirectory()) {
                continue;
            }
            String[] result = Utils.findJavaFile(patchIdDir);
            String patchId = patchIdDir.getName();
            patchList.add(new String[]{String.join("-", tool, proj, mutantId, patchId), result[0]});
        }
        return patchList;
    }

    private static List<String[]> extractRewardRepairPatchPath(String tool, String patchRootDir, String proj, String mutantId) {
        List<String[]> patchList = new ArrayList<>();
        File srcPatchDir = new File(patchRootDir, proj + File.separator + mutantId);
        // assert srcPatchDir.isDirectory() : "src patch dir not found: " + srcPatchDir.getAbsolutePath();
        if (!srcPatchDir.isDirectory()) {
            return patchList;
        }
        
        for (File patch : srcPatchDir.listFiles()) {
            String patchPath = patch.getAbsolutePath();
            String patchId = patch.getName().replace(".java", "");
            patchList.add(new String[]{String.join("-", tool, proj, mutantId, patchId), patchPath});
        }
        return patchList;
    }

    private static List<String[]> extractSequencerPatchPath(String tool, String patchRootDir, String proj, String mutantId) throws IOException {
        List<String[]> patchList = new ArrayList<>();
        File srcPatchDir = new File(patchRootDir, proj + mutantId);
        if (!srcPatchDir.isDirectory()) {
            return patchList;
        }
        // assert srcPatchDir.isDirectory() : "src patch dir not found: " + srcPatchDir.getAbsolutePath();
        for (File patchIdDir : srcPatchDir.listFiles()) {
            String[] result = Utils.findJavaFile(patchIdDir);
            String patchId = patchIdDir.getName();
            patchList.add(new String[]{String.join("-", tool, proj, mutantId, patchId), result[0]});
        }
        return patchList;
    }

    public static void main(String[] args) throws Exception {
        String patchRootDir = "/home/jun/fastd/dlapr-mirror/validation/src_d4j_ori_patches";
        for (String tool : tools) {
            System.out.println("tool: " + tool);
            for (String[] patchInfo : extractPatchPath(tool, patchRootDir, args[0], args[1])) {
                System.out.println("patchId: " + patchInfo[0] + ", patchPath: " + patchInfo[1]);
            }
        }
    }
}