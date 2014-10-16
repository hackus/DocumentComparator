/**
 * 
 */
package com.comparator.document;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;

import com.comparator.document.DocumentUtils.fileType;

/**
 * @author misirghi
 *
 */
public abstract class DocumentModel {
		
	protected String filePath;
	protected List<DocumentPage> pages = new LinkedList<DocumentPage>();
	
	public DocumentModel(String filePath)
	{
		this.filePath = filePath;
		processDocument();
	}

	public List<DocumentPage> getPages() {
		return pages;
	}
	
	public String getFilePath() {
		return filePath;
	}

	protected abstract void processDocument();
	
	public List<String> getAllLines() {
		List<String> lines = new LinkedList<String>();
		for(DocumentPage page : pages){
			lines.addAll(page.getLines());
		}
		return lines;	
	}
}
