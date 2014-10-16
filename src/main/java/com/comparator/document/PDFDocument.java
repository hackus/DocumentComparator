/**
 * 
 */
package com.comparator.document;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * @author misirghi
 *
 */
public class PDFDocument extends DocumentModel{
	//private PDDocument pdDoc;
	
	public PDFDocument(String filePath) {
		super(filePath);
	}

	@Override
	protected void processDocument() {
		
		try {
			
			PDDocument pdDoc = PDDocument.load(filePath);
			PDFTextStripper stripper = new PDFTextStripper();
			
			for(int i = 0; i < pdDoc.getDocumentCatalog().getAllPages().size(); i++)
			{
				stripper.setStartPage(i);
			    stripper.setEndPage(i+1);  
			    
				pages.add(new DocumentPage(stripper.getText(pdDoc)));
			}
			pdDoc.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
