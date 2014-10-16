package com.comparator;

import static org.kohsuke.args4j.ExampleMode.ALL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import com.comparator.actions.*;
import com.comparator.document.DocumentControler;
import com.comparator.document.DocumentUtils;
import com.comparator.log.Log;


/**
 * Hello world!
 * @author Mircea Sirghi
 */
public class MainApp 
{	
	@Option(name="-doc1",usage="File to compare")
	private String filePath1;
	
	@Option(name="-doc2",usage="File to compare with")
	private String filePath2;
	
	@Option(name="-tmpl",usage="Comparison template")
	private String templatePath;
	
	@Option(name="-map",usage="Comparison keys map")
	private String keysMap;
	
	@Option(name="-type",usage="Comparison types[template, lines, pages, generate_template]")
	private String compareType;
	
	@Option(name="-pgMap",usage="pages to compare")
	private String pagesMap = "";
	
//	@Option(name="-o",usage="output to this file",metaVar="OUTPUT")
//	private File out = new File(".");
	
	// receives other command line parameters than options
	@Argument
	private List<String> arguments = new ArrayList<String>();
	
	public static void main( String[] args )
	{
		try {
			
//			args = new String[] { "-type", "template",
//					"-doc1", "example.pdf",
//					"-map", "example.map.txt",
//					"-tmpl", "example.template.txt"};  

//			args = new String[] { "-type", "generate_template",
//			"-doc1", "example.pdf"};
			
			 //-type template -doc1 example.pdf -tmpl example.template.txt -map example.map.txt
  		
			//java -cp Comparator-0.0.1-jar-with-dependencies.jar document.comparator.MainApp -type generate_template -doc1 example.pdf
			
			new MainApp().doMain(args);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
	public void doMain(String[] args) throws IOException {
		CmdLineParser parser = new CmdLineParser(this);
		
		// if you have a wider console, you could increase the value;
		// here 80 is also the default
		parser.setUsageWidth(Integer.MAX_VALUE);
		//parser.printUsage(System.err);
		
		
		
		//System.out.println("1");	
		try {
		    // parse the arguments.
		    parser.parseArgument(args);
		    //System.out.println("2");
		    // you can parse additional arguments if you want.
		    // parser.parseArgument("more","args");

		    // after parsing arguments, you should check
		    // if enough arguments are given.
		    //if( arguments.isEmpty() )
		    //    throw new CmdLineException(parser,"No argument is given");
		    
		    if(compareType == null)
		    {
		    	//System.out.println("3");
		    	throw new CmdLineException(parser,"Comparison type not defined.");
		    }
		    else 
		    {		    	
		    	//System.out.println("4");
		    	if((ComparisonType.fromString(compareType).equals(ComparisonType.byTemplate)		    			
		    		||
		    		ComparisonType.fromString(compareType).equals(ComparisonType.genTemplate))
		    			&& filePath1 == null)
		    	{
		    		//System.out.println("5");
			    	throw new CmdLineException(parser,"-doc1 param is undefined.");
		    	}
		    	if(ComparisonType.fromString(compareType).equals(ComparisonType.byTemplate)
		    			&& (templatePath == null))
		    	{
		    		//System.out.println("5");
			    	throw new CmdLineException(parser,"-tmpl param is undefined.");
		    	}
		    	if(ComparisonType.fromString(compareType).equals(ComparisonType.byTemplate)
		    			&& (keysMap == null))
		    	{
		    		//System.out.println("5");
			    	throw new CmdLineException(parser,"-map param is undefined.");
		    	}		    	
			    if(ComparisonType.fromString(compareType).equals(ComparisonType.byTemplate) && filePath1 == null)
			    {
			    	//System.out.println("6");
			    	throw new CmdLineException(parser,"-doc1 param is undefined.");
			    }
			    if((ComparisonType.fromString(compareType).equals(ComparisonType.byLines) 
			    	||
			    	ComparisonType.fromString(compareType).equals(ComparisonType.byPages))
			    	&& 
			    	(filePath1 == null || filePath2 == null))
			    {
			    	//System.out.println("7");
			    	throw new CmdLineException(parser,"-doc1 and -doc2 should be defined.");
			    }
			    if(ComparisonType.fromString(compareType).equals(ComparisonType.byPages)
			    	&& 
				 (pagesMap == null))
			    {
			    	//System.out.println("8");
			    	throw new CmdLineException(parser,"-pgMap should be defined.");
			    }
		    }
		    //System.out.println("9");
		    
			// this will redirect the output to the specified output
			//System.out.println(out);
			
			Log.getInstance();
			
			switch(ComparisonType.fromString(compareType))
			{
				case byTemplate:
				  
					compareByTemplate(filePath1, templatePath, keysMap);
			    	
					break;
				case byPages:
				  
					compareByPages(filePath1, filePath2, pagesMap);
			    	
					break;
					
				case byLines:
					
					break;
					
				case genTemplate:
					
					generateDocTemplate(filePath1);
					
					break;
				default:
					throw new CmdLineException(parser,"Unknown arguments");
			}	
		} catch( CmdLineException e ) {
		    // if there's a problem in the command line,
		    // you'll get this exception. this will report
		    // an error message.
		    System.err.println(e.getMessage());
		    System.err.println("java SampleMain [options...] arguments...");
		    // print the list of available options
		    parser.printUsage(System.err);
		    System.err.println();

		    // print option sample. This is useful some time
		    System.err.println(" Example: java Run:"+parser.printExample(ALL));

		    return;
		}
		finally 
		{
			Log.getInstance().close();
		}
	}
	
	public void compareByLines(String filePath1, String filePath2)
	{
		Log.getInstance().write("Compare document["+filePath1+"] with document["+filePath2+"]");;

		Comparator compareDocs = new Comparator();

		compareDocs.compareByLines(filePath1, filePath2);
	}

	public static void compareByTemplate(String filePath, String templateFilePath, String keysMapFilePath)
	{
		Comparator compareDocs = new Comparator();

		compareDocs.compareByTemplate(filePath, templateFilePath, keysMapFilePath);
	}

	public static void compareByPages(String filePath1, String filePath2, String pagesMap)
	{	
		Log.getInstance().write("Compare document["+filePath1+"] with document["+filePath2+"]");;

		Comparator compareDocs = new Comparator();

		compareDocs.compareDocsByPages(filePath1, filePath2, pagesMap);
	}

	public static void generateDocTemplate(String filePath)
	{	
		DocumentControler doc = new DocumentControler(filePath);

		String templateName = DocumentUtils.getTemplateFilePath(filePath);//FileUtils.getFileNameWithoutExtension(filePath) + ".template.txt";

		try {

			doc.getDocumentView().createValidationFile(templateName);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public enum ComparisonType {
//			byTemplate("template"),
//			byLines("lines"),
//			byPages("pages"),
//			genTemplate("generate_template");			
//
//			private String text;
//
//			ComparisonType(String text) {
//				this.text = text;
//			}
//
//			public String getText() {
//				return this.text;
//			}
//
//			public static ComparisonType fromString(String text) {			
//				if (text != null) 
//				{
//					for (ComparisonType b : ComparisonType.values()) 
//					{
//						if (text.equalsIgnoreCase(b.text)) 
//						{
//							return b;
//						}
//					}
//				}
//				return null;
//			}
//	}
}