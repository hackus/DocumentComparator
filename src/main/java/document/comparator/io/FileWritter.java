/**
 * 
 */
package document.comparator.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author Mircea Sirghi
 *
 */
public class FileWritter {	
	PrintWriter writer;	
	
	public FileWritter(String fileName) throws FileNotFoundException, UnsupportedEncodingException
	{
		writer = new PrintWriter(fileName, "UTF-8");		
	}
	
	public void write(String str)
	{
		writer.println(str);
	}
	
	public void close()
	{
		writer.close();
	}
}
