/**
 * 
 */
package com.comparator.document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author misirghi
 *
 */
public class TXTDocument extends DocumentModel{

	public TXTDocument(String filePath) {
		super(filePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processDocument() {		
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			pages.add(new DocumentPage(in));
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
