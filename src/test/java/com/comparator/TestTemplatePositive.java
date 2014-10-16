/**
 * 
 */
package com.comparator;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.comparator.document.DocumentControler;
import com.comparator.document.DocumentPage;

/**
 * @author misirghi
 *
 */
public class TestTemplatePositive {
	 @Test
	 public void testTemplatePositive() throws IOException {		 
		 String[] args = new String[] { "-type", "template",
					"-doc1", "example.pdf",
					"-map", "example.map.txt",
					"-tmpl", "example.templatePositive.txt"};  
		 
		 new MainApp().doMain(args);
		
		 List<String> lines = new LinkedList<String>();
		 BufferedReader in = new BufferedReader(new FileReader("ComparisonDetailsLog.txt"));
		 String line = "";
			
	 	 while ((line = in.readLine()) != null) {
		 	 lines.add(line);
		 }
	 	 
		 in.close();
		 
		 assertTrue(lines.size() == 0);
	 }
}
