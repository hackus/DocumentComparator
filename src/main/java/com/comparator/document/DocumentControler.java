/**
 * 
 */
package com.comparator.document;

import org.apache.commons.lang.NotImplementedException;

import com.comparator.document.DocumentUtils.fileType;

/**
 * @author misirghi
 *
 */
public class DocumentControler {
	private DocumentView documentView;
	private DocumentModel documentModel;
	
	public DocumentControler(String filePath)
	{	
		String extension = DocumentUtils.getFileExtension(filePath);
		switch(fileType.fromString(extension))
        {
        	case pdf: 
        		documentModel = new PDFDocument(filePath);
        		break;
        	case txt:        				
        		documentModel = new TXTDocument(filePath);   
        		break;
        	case doc: 
        		documentModel = new DOCDocument(filePath);
        		break;
        	case docx:
        		documentModel = new DOCXDocument(filePath);
        		break;
        }
		
		documentView = new DocumentView(documentModel);
	}

	public DocumentView getDocumentView() {
		return documentView;
	}
}
