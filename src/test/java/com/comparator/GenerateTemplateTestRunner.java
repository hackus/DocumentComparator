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
public class GenerateTemplateTestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(GenerateDOCXTemplateTest.class, GenerateTXTTemplateTest.class, GeneratePDFTemplateTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
} 