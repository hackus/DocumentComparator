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
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

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
	
	public DocumentPage(String[] paragraphs){
		processStringArray(paragraphs);
	}
	
	public DocumentPage(List<XWPFParagraph> paragraphs){
		processDOCXParagraphs(paragraphs);
	}
	
	private void processDOCXParagraphs(List<XWPFParagraph> paragraphs)
	{
		lines=Lists.transform(paragraphs, new Function<XWPFParagraph,String>(){
			public String apply(XWPFParagraph input) {
				if(input!=null)
		            return input.getText();
		        else
		            return "";
			}
		});
	}
	
	private void processStringArray(String[] paragraphs){
		lines = Arrays.asList(paragraphs);
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
