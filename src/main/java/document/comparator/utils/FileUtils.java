/**
 * 
 */
package document.comparator.utils;

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

import document.comparator.log.Log;


/**
 * @author Mircea Sirghi
 *
 */
public class FileUtils {
	
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
    	
    	//String validationFile = tokens[0] + ".validation.txt";
		
		return tokens[0];
	}
	
	public static String getFileFolder(String fileName)
	{
//		String filePath = "";
//		try {
//			filePath = new File(".").getCanonicalPath();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		File file = new File(fileName);
		String dirPath = file.getAbsoluteFile().getParentFile().getAbsolutePath();
		
		return dirPath;
	}
	
	public static String getTemplateFilePath(String filePath)
	{
		String templatePath = getFileFolder(filePath) + "\\" + FileUtils.getFileNameWithoutExtension(filePath) + "."+templateFileName+".txt";
		
		//Log.getInstance().write("Template path is:["+templatePath+"]");
		
		return templatePath;
	}
	
	public static String getKeysMapFilePath(String filePath)
	{
		String keysMapPath = getFileFolder(filePath) + "\\" + FileUtils.getFileNameWithoutExtension(filePath) + "."+keysMapFileName+".txt";
		
		return keysMapPath;
	}
	
	public static String getValidationsFilePath(String filePath)
	{
		String validationFile = getFileFolder(filePath) + "\\" + FileUtils.getFileNameWithoutExtension(filePath) + "."+validationFileName+".txt";
		
    	return validationFile;
	}
	
//	public static List<String> fileToLines(String filePath)
//	{
//		List<String> lines = new LinkedList<String>();
//		
//		String extension = filePath.replaceAll("^.*\\.(.*)$", "$1");
//        
//        switch(fileType.fromString(extension))
//        {
//        	case pdf: 
//        		try {
//            		PDDocument pdDoc = PDDocument.load(filePath);
//        			PDFTextStripper stripper = new PDFTextStripper();	
//    				stripper.setStartPage( 1 );
//    				stripper.setEndPage(Integer.MAX_VALUE);
//    				String x1= stripper.getText(pdDoc);
//    				
//    				lines = Arrays.asList(x1.split("\r\n"));				
//    				pdDoc.close();
//    			} catch (IOException e) {
//    				
//    				e.printStackTrace();
//    			}
//        		break;
//        	case txt:        				
//        		String line = "";
//        		try {
//        			BufferedReader in = new BufferedReader(new FileReader(filePath));
//        			while ((line = in.readLine()) != null) {
//        				lines.add(line);
//        			}
//        		} catch (IOException e) {
//        			e.printStackTrace();
//        		}
//        	case doc: 
//        		throw new NotImplementedException("TODO doc reader");
//        	case docx:
//        		throw new NotImplementedException("TODO docx reader");
//        }
//        
//        return lines;
//	}

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
