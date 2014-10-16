/**
 * 
 */
package com.comparator.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import com.comparator.Config;
import com.comparator.document.DocumentUtils.fileType;
import com.comparator.document.io.DocumentWritter;

/**
 * @author Mircea Sirghi
 *
 */
public class DocumentView {	
	DocumentModel document;
	
	public DocumentView(DocumentModel document)
	{
		this.document = document;
	}
	
	public List<DocumentPage> getPages()
	{
		return document.getPages();
	}
	
	public List<String> getLines(){
		return document.getAllLines();
	}
	
	public List<String> getLinesUsingKeyMapReplacement(List<String> keysMap)
	{
		List<String> lines = document.getAllLines();
		
		for(int i=0; i<lines.size(); i++)
		{	
			if(lines.get(i).contains("<key_"))
			{
				for(int j=0; j < keysMap.size(); j++)
				{
					String[] strAux = keysMap.get(j).split(Config.keyMapSplit); 
					
					if(lines.get(i).contains(strAux[0]))
					{
						lines.set(i, lines.get(i).replaceAll(strAux[0], strAux[1]));
					}
				}
			}
		}
		
		return lines;
	}
	
	public void createValidationFile(String templatepath) throws FileNotFoundException, UnsupportedEncodingException
	{
		DocumentWritter template = new DocumentWritter(templatepath);
		
		template.write("***********************TemplateFile***********************");
		
		template.write("");
		
		for(int i = 0; i<document.pages.size();i++)
		{	
			template.write("->Page["+(i+1)+"]<-");
			
			List<String> lines = document.pages.get(i).getLines(); 
			
			for(int j = 0; j < lines.size(); j++)
			{
				template.write("->Page["+(i+1)+"]Line["+(j+1)+"]:["+lines.get(j)+"]");				
			}
			template.write("___________________________________________________________________________________________________________________");
		}
		template.close();
	}
	
    public String generateDocValidationFile()
    {	
    	String validationFile = DocumentUtils.getValidationsFilePath(document.getFilePath());//FileUtils.getFileNameWithoutExtension(filePath) + "."+FileUtils.validationFileName+".txt";
    	
    	try {
    		
			createValidationFile(validationFile);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	
    	return DocumentUtils.getValidationsFilePath(document.filePath);
    }
}