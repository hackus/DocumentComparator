/**
 * 
 */
package document.comparator;

import java.util.List;

import difflib.Delta;
import difflib.DiffAlgorithm;
import difflib.DiffUtils;
import difflib.Patch;
import document.comparator.log.Log;
import document.comparator.template.DocumentTemplate;
import document.comparator.utils.FileDocument;
import document.comparator.utils.FileUtils;

/**
 * @author Mircea Sirghi
 *
 */
public class Comparator {
	
	public void compareByTemplate(String filePath, String templateFilePath, String keysMapFilePath)
	{		
//		String templatePath = FileUtils.getTemplateFilePath(filePath);
//		String keysMapPath = FileUtils.getKeysMapFilePath(filePath);
//		
		
		String templatePath = templateFilePath;
		String keysMapPath = keysMapFilePath;
		
		
		
//		String templatePath = templateFilePath;
//		String keysMapPath = keysMapFilePath;
		
		FileDocument doc = new FileDocument(filePath);
		DocumentTemplate template = new DocumentTemplate(templatePath);
		FileDocument keysmap = new FileDocument(keysMapPath);
				
		String validationFilePath = doc.generateDocValidationFile();
		
		FileDocument validationDoc = new FileDocument(validationFilePath);
		
		List<String> file1AsStringList = validationDoc.getLines();
		
		List<String> file2AsStringList = template.getLinesUsingKeyMapReplacement(keysmap.getLines());
		
		Patch patch = DiffUtils.diff(file1AsStringList, file2AsStringList);

		printComparisonResults(patch);
		
		validationDoc.close();
		doc.close();
	}
	
	public void compareByLines(String filePath1, String filePath2)
	{	
		FileDocument doc1 = new FileDocument(filePath1);
		FileDocument doc2 = new FileDocument(filePath2);
		
		List<String> file1AsStringList = doc1.getLines();
		List<String> file2AsStringList = doc2.getLines();
		
		Patch patch = DiffUtils.diff(file1AsStringList, file2AsStringList);

		printComparisonResults(patch);
		
		doc1.close();
		doc2.close();
	}
	
	public void compareDocsByPages(String filePath1, String filePath2, String pagesMap)
	{
		if(pagesMap.equals(""))
		{
			FileDocument doc1 = new FileDocument(filePath1);
			FileDocument doc2 = new FileDocument(filePath2);
					
			Log.getInstance().write("First document ["+filePath1+"] pages count is ["+doc1.getAllPages().size()+"] "
								+ "\r\n"
								+ "Second document ["+filePath2+"] pages cound is ["+doc2.getAllPages().size()+"]");
			
			for(int i = 0; i<doc1.getAllPages().size();i++)
			{
				Log.getInstance().write("");
				Log.getInstance().write("***********************Comparison results***********************");
				Log.getInstance().write("Compare page["+i+"] from file ["+filePath1+"] with page["+i+"] from file ["+filePath2+"].");				
				Log.getInstance().write("");
				
				Patch patch = DiffUtils.diff(doc1.getAllPages().get(i).getPageLines(), doc2.getAllPages().get(i).getPageLines());
				 
				printComparisonResults(patch);
			}
			
			doc1.close();
			doc2.close();
		}
	}
	
	public void printComparisonResults(Patch patch)
	{	
		for (Delta delta: patch.getDeltas()) {
			Log.getInstance().write(delta.toString().replace("lines: [", "lines:\r\n [").replace("] to [", "] \r\nto\r\n ["));
	    }
	}
}
