/**
 * 
 */
package com.comparator.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;

import com.comparator.Config;

/**
 * @author Mircea Sirghi
 *
 */
public class Log {
	static Log instance = null;
	
	PrintWriter writer;
	
	private Log() throws IOException
	{
		
		//String filePathString = Config.getJarPath() + "\\ComparisonDetailsLog.txt";
		String filePathString = "ComparisonDetailsLog.txt";
		
		
		
		File f = new File(filePathString);
		if(!f.exists())
		{	
			f.createNewFile();
		}
		
		//JOptionPane.showMessageDialog(null,f.getAbsolutePath());
		writer = new PrintWriter(filePathString, "UTF-8");
	}
	
	private Log(File logFile) throws FileNotFoundException, UnsupportedEncodingException
	{
		JOptionPane.showMessageDialog(null,logFile.getAbsolutePath());
		writer = new PrintWriter(logFile, "UTF-8");
	}
	
	public static Log getInstance() 
	{
		if(instance == null)
		{
			try {
				instance = new Log();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
		
		return instance;
	}
	
	public static Log getInstance(File logFile)
	{
		if(instance == null)
		{
			try {
				instance = new Log(logFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return instance;
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
