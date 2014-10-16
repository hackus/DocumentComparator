/**
 * 
 */
package com.comparator.actions;

import java.util.List;

import com.comparator.document.DocumentControler;
import com.comparator.log.Log;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

/**
 * @author Mircea Sirghi
 *
 */
public class Comparator {
	
	public void compareByTemplate(String filePath, String templateFilePath, String keysMapFilePath)
	{	
//		String templatePath = templateFilePath;
//		String keysMapPath = keysMapFilePath;
		
		DocumentControler doc = new DocumentControler(filePath);
		DocumentControler template = new DocumentControler(templateFilePath);
		DocumentControler keysmap = new DocumentControler(keysMapFilePath);		
				
		String validationFilePath = doc.getDocumentView().generateDocValidationFile();
		
		DocumentControler validationDoc = new DocumentControler(validationFilePath);
		
		List<String> file1AsStringList = validationDoc.getDocumentView().getLines();		
		List<String> file2AsStringList = template.getDocumentView().getLinesUsingKeyMapReplacement(keysmap.getDocumentView().getLines());		
		Patch patch = DiffUtils.diff(file1AsStringList, file2AsStringList);

		printComparisonResults(patch);
	}
	
	public void compareByLines(String filePath1, String filePath2)
	{	
		DocumentControler doc1 = new DocumentControler(filePath1);
		DocumentControler doc2 = new DocumentControler(filePath2);
		
		List<String> file1AsStringList = doc1.getDocumentView().getLines();
		List<String> file2AsStringList = doc2.getDocumentView().getLines();
		
		Patch patch = DiffUtils.diff(file1AsStringList, file2AsStringList);

		printComparisonResults(patch);		
	}
	
	public void compareDocsByPages(String filePath1, String filePath2, String pagesMap)
	{
		if(pagesMap.equals(""))
		{
			DocumentControler doc1 = new DocumentControler(filePath1);
			DocumentControler doc2 = new DocumentControler(filePath2);
					
			Log.getInstance().write("First document ["+filePath1+"] pages count is ["+doc1.getDocumentView().getPages().size()+"] "
								+ "\r\n"
								+ "Second document ["+filePath2+"] pages cound is ["+doc2.getDocumentView().getPages().size()+"]");
			
			for(int i = 0; i<doc1.getDocumentView().getPages().size();i++)
			{
				Log.getInstance().write("");
				Log.getInstance().write("***********************Comparison results***********************");
				Log.getInstance().write("Compare page["+i+"] from file ["+filePath1+"] with page["+i+"] from file ["+filePath2+"].");				
				Log.getInstance().write("");
				
				Patch patch = DiffUtils.diff(doc1.getDocumentView().getPages().get(i).getLines(), doc2.getDocumentView().getPages().get(i).getLines());
				 
				printComparisonResults(patch);
			}
		}
	}
	
	public void printComparisonResults(Patch patch)
	{	
		for (Delta delta: patch.getDeltas()) {
			Log.getInstance().write(delta.toString().replace("lines: [", "lines:\r\n [").replace("] to [", "] \r\nto\r\n ["));
	    }
	}
}
