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
public class CompareByTemplateTestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(CompareByPDFTemplateNegativeTest.class,
											 CompareByPDFTemplatePositiveTest.class,
											 CompareByDOCXTemplatePositiveTest.class,
											 CompareByDOCXTemplateNegativeTest.class,
											 CompareByDOCTemplatePositiveTest.class, 
											 CompareByDOCTemplateNegativeTest.class,
											 CompareByTXTTemplatePositiveTest.class,
											 CompareByTXTTemplateNegativeTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
} 