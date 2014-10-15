/**
 * 
 */
package document.comparator.utils;

import java.io.BufferedReader;
import java.io.File;
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

import document.comparator.Config;
import document.comparator.io.FileWritter;
import document.comparator.utils.FileUtils.fileType;

/**
 * @author Mircea Sirghi
 *
 */
public class Document {
	protected PDDocument pdDoc;
	protected String filePath;
	private String extension;
	List<FilePage> pages = new LinkedList<FilePage>();
	
	public Document(String filePath)
	{
		this.filePath = filePath;
		extension = FileUtils.getFileExtension(filePath);
		switch(fileType.fromString(extension))
        {
        	case pdf: 
        		try {
        			pdDoc = PDDocument.load(filePath);
        			analyzePDF();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        		break;
        	case txt:        				
        		throw new NotImplementedException("TODO FileDocument construnctor txt");        		
        	case doc: 
        		throw new NotImplementedException("TODO FileDocument construnctor doc");
        	case docx:
        		throw new NotImplementedException("TODO FileDocument construnctor docx");
        }
	}
	
	public List<FilePage> getAllPages()
	{
		return pages;
	}
	
	public void close()
	{
		if(pdDoc != null)
		{
			try {
				pdDoc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<String> getLinesUsingKeyMapReplacement(List<String> keysMap)
	{
		List<String> lines = getLines();
		
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
		FileWritter template = new FileWritter(templatepath);
		
		template.write("***********************TemplateFile***********************");
		
		template.write("");
		
		for(int i = 0; i<pages.size();i++)
		{	
			template.write("->Page["+(i+1)+"]<-");
			
			List<String> lines = pages.get(i).getPageLines(); 
			
			for(int j = 0; j < lines.size(); j++)
			{
				template.write("->Page["+(i+1)+"]Line["+(j+1)+"]:["+lines.get(j)+"]");				
			}
			template.write("___________________________________________________________________________________________________________________");
		}
		template.close();
	}	
	
	private void analyzePDF()
	{
		for(int i = 0; i < pdDoc.getDocumentCatalog().getAllPages().size(); i++)
		{
			pages.add(new FilePage(pdDoc, i));
		}
	}	
	
	public List<String> getLines()
	{
		List<String> lines = new LinkedList<String>();
        
        switch(fileType.fromString(extension))
        {
        	case pdf: 
        		try {            		
        			PDFTextStripper stripper = new PDFTextStripper();	
    				stripper.setStartPage( 1 );
    				stripper.setEndPage(Integer.MAX_VALUE);
    				String x1= stripper.getText(pdDoc);
    				
    				lines = Arrays.asList(x1.split("\r\n"));				
    				pdDoc.close();
    			} catch (IOException e) {
    				
    				e.printStackTrace();
    			}
        		break;
        	case txt:        				
        		String line = "";
        		try {
        			BufferedReader in = new BufferedReader(new FileReader(filePath));
        			while ((line = in.readLine()) != null) {
        				lines.add(line);
        			}
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        		break;
        	case doc: 
        		throw new NotImplementedException("TODO doc reader");
        	case docx:
        		throw new NotImplementedException("TODO docx reader");
        }
        
        return lines;
	}
	
    public String generateDocValidationFile()
    {	
    	String validationFile = FileUtils.getValidationsFilePath(filePath);//FileUtils.getFileNameWithoutExtension(filePath) + "."+FileUtils.validationFileName+".txt";
    	
    	try {
    		
			createValidationFile(validationFile);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	
    	return FileUtils.getValidationsFilePath(filePath);
    }
}