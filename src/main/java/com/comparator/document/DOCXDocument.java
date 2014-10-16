/**
 * 
 */
package com.comparator.document;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 * @author misirghi
 *
 */
public class DOCXDocument extends DocumentModel {

	public DOCXDocument(String filePath) {
		super(filePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processDocument() {
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
 
			XWPFDocument document = new XWPFDocument(fis);
 
			List<XWPFParagraph> paragraphs = document.getParagraphs();
			
			System.out.println("Total no of paragraph "+paragraphs.size());
			for (XWPFParagraph para : paragraphs) {
				pages.add(new DocumentPage(para.getText()));
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
