/**
 * 
 */
package document.comparator.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * @author Mircea Sirghi
 *
 */
public class FilePage{
	
	List<String> lines = new LinkedList<String>();
	
	int pageIndex;
	
	public FilePage(PDDocument pdDoc, int pageIndex){
		processPDF(pdDoc, pageIndex);
	}
	
	public List<String> getPageLines()
	{
		return lines;
	}
	
	private void processPDF(PDDocument pdDoc, int pageIndex)
	{	
		PDFTextStripper stripper;
		try {
			stripper = new PDFTextStripper();
		
		    stripper.setStartPage(pageIndex);
		    stripper.setEndPage(pageIndex+1);    
		    
		    String strAux = stripper.getText(pdDoc);
		    //break up the file content returned as a string into individual lines
			
		    lines = Arrays.asList(strAux.split("\r\n"));
	    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
