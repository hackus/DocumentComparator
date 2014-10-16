/**
 * 
 */
package com.comparator;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * @author misirghi
 *
 */
public class GenerateDOCTemplateTest {
	@Test
	public void test() throws IOException
	{			
		String [] args = new String[] { "-type", "generate_template",
		"-doc1", "forTest/test1doc.doc"};

		new MainApp().doMain(args);

		List<String> lines = new LinkedList<String>();
		BufferedReader in = new BufferedReader(new FileReader("forTest/test1doc.template.txt"));
		String line = "";

		while ((line = in.readLine()) != null) {
		 lines.add(line);
		}

		in.close();

		assertTrue(lines.size() > 0);
	}
}
