/**
 * 
 */
package com.comparator.document;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * @author Mircea Sirghi
 *
 */
public class DocumentPage{
	
	List<String> lines = new LinkedList<String>();
	
	int pageIndex;
	
	public DocumentPage(String page){
		processLines(page);
	}
	
	public DocumentPage(BufferedReader reader) throws IOException{
		processLines(reader);
	}
	
	private void processLines(String page){
		lines = Arrays.asList(page.split("\r\n"));
	}
	
	private void processLines(BufferedReader reader) throws IOException{
		String line = "";
		
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
	}
	
	public List<String> getLines()
	{
		return lines;
	}
}
