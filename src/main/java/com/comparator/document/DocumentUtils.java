/**
 * 
 */
package com.comparator.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.comparator.log.Log;


/**
 * @author Mircea Sirghi
 *
 */
public class DocumentUtils {
	
	public static String templateFileName = "template";
	public static String keysMapFileName = "map";
	public static String validationFileName = "validation";
	
	public static String getFileExtension(String filePath)
	{
		String extension = filePath.replaceAll("^.*\\.(.*)$", "$1");
		
		return extension;
	}
	
	public static String getFileNameWithoutExtension(String filePath)
	{
		String fileNameAux = new File(filePath).getName();
    	
    	String[] tokens = fileNameAux.split("\\.(?=[^\\.]+$)");
    	
		return tokens[0];
	}
	
	
	public static String getFileName(String filePath)
	{
		String fileName = new File(filePath).getName();
		
		return fileName;
	}
	
	public static String getFileFolder(String fileName)
	{

		File file = new File(fileName);
		String dirPath = file.getAbsoluteFile().getParentFile().getAbsolutePath();
		
		return dirPath;
	}
	
	public static String getTemplateFilePath(String filePath)
	{
		String templatePath = getFileFolder(filePath) + "\\" + DocumentUtils.getFileNameWithoutExtension(filePath) + "."+templateFileName+".txt";
		
		return templatePath;
	}	
	
	public static String getKeysMapFilePath(String filePath)
	{
		String keysMapPath = getFileFolder(filePath) + "\\" + DocumentUtils.getFileNameWithoutExtension(filePath) + "."+keysMapFileName+".txt";
		
		return keysMapPath;
	}
	
	public static String getValidationsFilePath(String filePath)
	{
		String validationFile = getFileFolder(filePath) + "\\" + DocumentUtils.getFileNameWithoutExtension(filePath) + "."+validationFileName+".txt";
		
    	return validationFile;
	}

	public enum fileType {
		pdf("pdf"),
		txt("txt"),
		doc("doc"),
		docx("docx");

		private String text;

		fileType(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}

		public static fileType fromString(String text) {			
			if (text != null) 
			{
				for (fileType b : fileType.values()) 
				{
					if (text.equalsIgnoreCase(b.text)) 
					{
						return b;
					}
				}
			}
			return null;
		}
	}
}
