DocumentComparator
==================

Contact details:<br>
mail: hakkuss@yahoo.com<br>
<a href="http://www.linkedin.com/pub/mircea-sirghi/32/6b5/700/" target="_blank">Find me on linkedin.</a>

Description: 
	This tool is able to find differences between different versions of the same document based on the mapping file that can be automatically edited. 
	It is useful when testing an application that generates files like contracts and one might be interested that the file generation process remains
	stable between releases. 

Supported file extensions:
	pdf

How to

	Make sure you have jre(from 1.5 and above) installed .
	
		Windows:
		
			1. Generate the template 
			 
				a) Edit the following line from "generateTemplate.vbs" with the actual file name: 
				
					Dim filename : filename = "<fileName>.pdf"
				b) Execute "generateTemplate.vbs", it will automatically generate the "<fileName>.template.txt" file with the text content from the pdf file.
				
			2. Create the map file
					 
				a) Create the <fileName>.map.txt
				
			 	b) Define keys for each dinamically changed string values from the pdf file. 
						-dinamically changed values are those that vary in different versions of the "<fileName>.pdf" file.
					
						for example company name, dates, any other identifiable changeable elements that may appear
						
						replace the changeable values inside the "<fileName>.template.txt" file with the related keys
						
				Example of map file: 
					<key_CompanyName>:_:ENTCS
					<key_MyUserName>:_:myuserid@mydept.myinst.myedu
					<key_CoUserName>:_:couserid@codept.coinst.coedu							
												
			4. When the map file is ready and the values in the template file are replaced with the keys the tool is ready to validate any documents of the same type.   
					 
			5. Compare files
				
				a) Edit the following lines from "compareByTemplate.vbs" with the actual file name of the fail to be compared and the file names for the map and template file.  
					
					Dim fileName : fileName = "<fileToCompareWith>.pdf"
					Dim templateName : templateName = "<fileName>.template.txt"
					Dim mapName : mapName = "<fileName>.map.txt"
					
				
				b) execute "compareByTemplate.vbs", it will automatically display a message box with the differences or "No data found!" if the files are identical.
				
				Note: the differences would be available in: "ComparisonDetailsLog.txt"	
					
		Console: 
			
			1. Generate the template 
			
				a) Edit the <filename> in the command below and execute it cmd: 
				
					java -cp Comparator-0.0.1-jar-with-dependencies.jar document.comparator.MainApp -type generate_template -doc1 <fileName>.pdf
					
				b) Execute the command in cmd, it will automatically generate the "<fileName>.template.txt" file with the text content from the pdf file.
				
			2. Create the map file
					 
				a) Create the <fileName>.map.txt
				
			 	b) Define keys for each dinamically changed string values from the pdf file. 
						-dinamically changed values are those that vary in different versions of the "<fileName>.pdf" file.
					
						for example company name, dates, any other identifiable changeable elements that may appear
						
						replace the changeable values inside the "<fileName>.template.txt" file with the related keys
						
				Example of map file: 
					
					
					<h3><key_CompanyName>:_:ENTCS</h3>
					<h3><key_MyUserName>:_:myuserid@mydept.myinst.myedu</h3>
					<h3><key_CoUserName>:_:couserid@codept.coinst.coedu</h3>
												
												
			4. When the map file is ready and the values in the template file are replaced with the keys the tool is ready to validate any documents of the same type.   
					 
			5. Compare files
				
				a) Edit the <fileToCompareWith> and the <fileName> in the command below:
					
					java -cp Comparator-0.0.1-jar-with-dependencies.jar document.comparator.MainApp -type template -doc1 <fileToCompareWith>.pdf -tmpl <fileName>.template.txt -map <fileName>.map.txt
				
				b) execute "compareByTemplate.vbs", it will automatically display a message box with the differences or "No data found!" if the files are identical.
				
				Note: the differences would be available in: "ComparisonDetailsLog.txt"
				
	
		You can find me on linkedin: 


				
