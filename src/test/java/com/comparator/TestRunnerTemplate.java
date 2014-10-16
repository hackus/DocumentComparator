/**
 * 
 */
package com.comparator;

/**
 * @author misirghi
 *
 */
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
public class TestRunnerTemplate {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestTemplateNegative.class, TestTemplatePositive.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
} 