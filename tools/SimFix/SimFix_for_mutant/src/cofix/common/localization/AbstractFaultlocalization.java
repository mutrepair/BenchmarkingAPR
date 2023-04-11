/**
 * Copyright (C) SEI, PKU, PRC. - All Rights Reserved.
 * Unauthorized copying of this file via any medium is
 * strictly prohibited Proprietary and Confidential.
 * Written by Jiajun Jiang<jiajun.jiang@pku.edu.cn>.
 */
package cofix.common.localization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cofix.common.config.Constant;
import cofix.common.util.Pair;
import cofix.common.util.Subject;

/**
 * @author Jiajun
 * @date Jul 4, 2017
 */
public abstract class AbstractFaultlocalization {
	
	protected Subject _subject = null;
	protected int _total = 0;
	protected List<String> _failedTests = null;
	protected List<String> _passedTests = null;
	
	
	public AbstractFaultlocalization(Subject subject) {
		_subject = subject;
		_failedTests = new ArrayList<>();
		_passedTests = new ArrayList<>();
	}
	
	public int getTotalTestCases(){
		return _total;
	}
	
	public List<String> getPassedTestCases(){
		return _passedTests;
	}
	
	public List<String> getFailedTestCases(){
		return _failedTests;
	}

	public List<String> getFailedTestsForMutants() {
		File file = new File(Constant.MUTANT_FAILING_TESTS_HOME + "/" + _subject.getName() + "-1f" + "/"
		 + _subject.getCurMutantId() + ".txt");
		if(!file.exists()){
			System.err.println("Failed test file does not exist : " + file.getAbsolutePath());
			System.exit(0);
		}
		List<String> failedTests = new ArrayList<>();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String line = null;
		try {
			while((line = bufferedReader.readLine()) != null){
				failedTests.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return failedTests;
	}
	
	protected abstract void locateFault(double threshold);
	
	public abstract List<Pair<String, Integer>> getLocations(int topK);
	
}
