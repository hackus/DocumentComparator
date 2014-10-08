/**
 * 
 */
package document.comparator.template;

import java.util.List;

import document.comparator.utils.FileDocument;

/**
 * @author Mircea Sirghi
 *
 */
public class DocumentTemplate extends FileDocument {
	
	public DocumentTemplate(String filepath) {
		super(filepath);		
	}

	public List<String> generateDocumentFromTemplate(String keyMapFilePath)
	{		
		FileDocument keysMap = new FileDocument(keyMapFilePath);
		
		List<String> lines = getLinesUsingKeyMapReplacement(keysMap.getLines());
		
		return lines;
	}
}
