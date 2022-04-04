package com.framework.core.models;

import java.util.Map;

public class Feature {

	public String name;
	
	public Map<String, Map<String, String>> testCaseData;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Map<String, String>> getTestCaseData() {
		return testCaseData;
	}

	public void setTestCaseData(Map<String, Map<String, String>> testCaseData) {
		this.testCaseData = testCaseData;
	}
}
