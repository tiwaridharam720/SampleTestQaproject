package com.qa.Analyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class MyTransformer implements IAnnotationTransformer{
	
	public void transform(ITestAnnotation annoation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		annoation.setRetryAnalyzer(RetryAnalyzer.class);
	}

}
