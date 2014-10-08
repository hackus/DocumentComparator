
Const ForReading = 1, ForWriting = 2, ForAppending = 8

Dim text
Dim exeType : exeType = "generate_template"
Dim filename : filename = "example.pdf"

Call generateTemplate(exeType, filename)

Function generateTemplate(ByVal executionType, ByVal docName)	
	Dim WshShell
	'Set WshShell = WScript.CreateObject("WScript.Shell")
	Set WshShell = CreateObject("WScript.Shell")
	Dim strRun : strRun = "cmd /K java -cp Comparator-0.0.1-jar-with-dependencies.jar document.comparator.MainApp -type " &_
						  executionType &_	
						  " -doc1 " &_
						  docName						  
	Dim return
	'Call WScript.Echo(strRun)
	Call WshShell.Run(strRun, 0, true)
	
	Set WshShell = nothing
End Function