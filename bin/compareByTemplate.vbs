Dim text


Dim comparisonType : comparisonType = "template"
Dim fileName : fileName = "example_ToCompareWith.pdf"
Dim templateName : templateName = "example.template.txt"
Dim mapName : mapName = "example.map.txt"

Const ForReading = 1, ForWriting = 2, ForAppending = 8

text = CompareByTemplate(comparisonType, fileName, templateName, mapName)
Msgbox text

Function CompareByTemplate(ByVal executionType, ByVal docName, ByVal templateName, ByVal keysMap)	
	Dim WshShell
	Set WshShell = WScript.CreateObject("WScript.Shell")
	Dim strRun : strRun = "cmd /c java -cp Comparator-0.0.1-jar-with-dependencies.jar document.comparator.MainApp -type " &_
						  executionType &_	
						  " -doc1 " &_
						  docName &_
						  " -tmpl " &_
						  templateName &_
						  " -map " &_
						  keysMap
						  
	'Call WScript.Echo(strRun)
	Return = WshShell.Run(strRun, 0, true)
	'Set oExec = WshShell.Exec(strRun)
	
	'Do While oExec.Status = 0
	'	WScript.Sleep 1000
	'Loop
	
	Set fso = CreateObject("Scripting.FileSystemObject")
	Set f = fso.OpenTextFile("ComparisonDetailsLog.txt", ForReading, false)
	
	Dim strFound : strFound = ""
	If not f.atendofstream Then
		strFound = f.ReadAll
	Else 
		strFound = "No data found!"
	End If
	
	CompareByTemplate = strFound
	
	f.Close
	Set WshShell = nothing
End Function

