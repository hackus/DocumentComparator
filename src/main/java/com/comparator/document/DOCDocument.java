package com.comparator.document;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class DOCDocument extends DocumentModel{

	public DOCDocument(String filePath) {
		super(filePath);
	}

	@Override
	protected void processDocument() {
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
 
			HWPFDocument doc = new HWPFDocument(fis);
 
			WordExtractor we = new WordExtractor(doc);
 
			String[] paragraphs = we.getParagraphText();
					
			pages.add(new DocumentPage(paragraphs));			
			
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
