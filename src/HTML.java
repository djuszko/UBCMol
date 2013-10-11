//
//  HTML.java
//  Test
//
//  Created by Nicolas Morin on 18/05/08.
//  Copyright 2008 __MyCompanyName__. All rights reserved.
//
import java.io.*;
import javax.swing.*;
public class HTML {
	private String header;
	private String mainTitle;
	private int id;
	private File out;
	
	public HTML(File file, int id, String mainTitle, JTextField[] subTitles, JTextArea [] subTexts, File [] files, JTextField [] scriptField, JCheckBox [] wireFrameTool, JCheckBox [] spaceFillTool){
	this.out = file;
	this.id = id;
	this.mainTitle = mainTitle;
	mainTitle = file.getName();
	mainTitle = mainTitle.substring(0,mainTitle.indexOf("."));
	System.out.println("Testing scripts etc...");
	for(int i = 0; i <= id; i++){
	System.out.println("sript "+i+" "+scriptField[i].getText());
	System.out.println("wire "+i+" "+wireFrameTool[i].isSelected());
	System.out.println("space "+i+" "+spaceFillTool[i].isSelected());
	}
	
	header = "<html>\n"+
			"<head>\n"+
			"<script src=\"jmol/jmol.js\" type=\"text/javascript\"></script>\n"+
			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\n"+
			"<meta name=\"Author\" content=\"W. Stephen McNeil\">\n"+
			"<meta name=\"Owner\" content=\"W. Stephen McNeil\">\n"+
			"<meta name=\"robots\" content=\"index, nofollow\">\n"+
			"<meta name=\"keywords\" content=\"mcneil, stephen mcneil, w. stephen mcneil, chemistry, Okanagan University College, electron transfer proteins, structure, rubredoxin, ferredoxin, cytochrome c, cupredoxin, plastocyanin, azurin, plantacyanin, basic blue protein, stellacyanin, Chime, MDL chime, University of British Columbia, University of British Columbia Okanagan, UBCO, UBC Okanagan, groovy\">\n"+
			"<meta name=\"copyright\" content=\"Copyright 2002 - 2006 W. Stephen McNeil\">\n"+
			"<title>\n"+
			mainTitle+"\n"+
			"</title>\n"+
			"</head>\n"+
			"<body bgcolor=\"#ffffff\">\n"+
			"<p>\n"+
			"<font size=+1><b>"+mainTitle+"</b></font>\n"+
			"</p>\n";
			
			for(int i = 0; i<= id; i++){
			header +=
			"<p> <b>"+subTitles[i].getText()+"</b><br>\n"+
			"<table>\n"+
			"<tr><td>\n"+
			"<script type=\"text/javascript\">\n"+
				"jmolInitialize(\"jmol/\")\n"+
				"jmolApplet(300, \"load files/"+ files[i].getName()  +"\")\n"+ 
				"jmolBr()\n"+
				"jmolCommandInput(\"execute\",30)\n"+
				"jmolBr()\n";
			if(wireFrameTool[i].isSelected()){
				header += 
					"function wireFrame"+i+"() {\n"+
						"\tvar oTextbox1 = document.getElementById(\"num\");"+
						"\tjmolScript(\"wireframe \"+oTextbox1.value,"+i+")\n"+
					"}\n";
			}
			if(spaceFillTool[i].isSelected()){
				header +=
					"function spaceFill"+i+"() {\n"+
						"\tvar oTextbox1 = document.getElementById(\"num\");\n"+
						"\tjmolScript(\"spacefill \"+oTextbox1.value,"+i+")\n"+
					"}\n";
			}
			if(scriptField[i] != null){
				header +=
					"function runScript"+i+"() {\n"+
							"jmolScript(\""+ scriptField[i].getText() +"\","+i+")\n"+

					"}\n";
			}
			header +=
			"</script>\n"+
			"</td>\n"+
				"\t<td> "+subTexts[i].getText()+
				  "<br>"+
				  "<br>\n"+
			"</td>"+  
			"</tr></table>";
			if(wireFrameTool[i].isSelected()){
				header +=
					"<input type=\"text\" size=\"12\" id=\"num\" />\n"+
					"<input type=\"button\" value=\"wireframe\" onClick=\"wireFrame"+i+"()\" />\n"+
					"<br />\n";
			}
			
			if(spaceFillTool[i].isSelected()){
				header +=
					"<input type=\"text\" size=\"12\" id=\"num\" />\n"+
					"<input type=\"button\" value=\"spacefill\" onClick=\"spaceFill"+i+"()\" />\n"+
					"<br />\n";
			}
			
			if(scriptField[i] != null){
				header +=
					"<input type=\"button\" value=\"run script\" onClick=\"runScript"+i+"()\" />\n";
			}
			header +=
				"<br>\n"+
				"<br>\n"+
				"<br>\n";
		}//end for loop
			header +=
			"<!--footer info--><p><i><font size=-1>This page is maintained and copyright by <a href=\"http://people.ok.ubc.ca/wsmcneil/\">W. Stephen McNeil</a> at <a href=\"http://www.ubc.ca/okanagan/\">UBC Okanagan</a>.<br>\n"+  
			"<script src=\"datemod.js\" language=\"JavaScript\"></script>\n"+
			"</font></i></p>\n"+
			"</body>\n"+
			"</html\n";

	}
	
	public void print(){
	try{
	System.out.println(header);
					
		byte[] b = header.getBytes();
		FileOutputStream fos = new FileOutputStream(out);
		fos.write(b);
	}catch(IOException e){
		e.printStackTrace();
	}
	}
}
