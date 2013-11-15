
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Toolkit;
import java.io.*;
import javax.swing.*;
import javax.swing.JTextArea;
import java.util.StringTokenizer;
import java.util.ArrayList;


import java.awt.Color;

import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.text.NumberFormat;
import org.jmol.adapter.smarter.SmarterJmolAdapter;
import org.jmol.api.JmolAdapter;
import org.jmol.api.JmolViewer;

public class HTMLBuilder extends JFrame implements ActionListener{

private int frameWidth, frameHeight;
private JmolViewer [] viewer = new JmolViewer [10];

private JTextArea [] scriptArea = new JTextArea [10];
private JTextField [] javascriptField = new JTextField[10];
private JPanel javascriptPanel;
private JButton [] testScriptButton = new JButton [10];
private JButton [] resetButton = new JButton [10];
private JButton [] printScriptButton = new JButton [10];
private JPanel [] toolPanel = new JPanel[10];

private MainPanel mainPanel;

private JPanel eastPanel;
private JScrollPane mainScroll;
private File [] pdbFiles =  new File[10];
private String [] pdbString =  new String[10];


private FileDrop [] fileDrop =  new FileDrop[10];

private JPanel framePanel;
private JFrame frame;
private int id = 0;
private Toolkit toolkit;
private Dimension screenSize;
private JFileChooser fileChooser;
private JMenuBar menuBar;
private JMenu editMenu, fileMenu;
private JMenuItem addItem, loadScript, saveScript;

private ScriptPanel scriptPanel;

private Border blackline;
private JPanel jPanel;
private String version = "Version 1.4";

public static void main(String[] argv) {
  
	HTMLBuilder builder = new HTMLBuilder();
    
  }


public HTMLBuilder(){

	frame = new JFrame("UBCMol " + version);
	frame.addWindowListener(new ApplicationCloser());
	toolkit = Toolkit.getDefaultToolkit();
	screenSize = toolkit.getScreenSize();
	frameWidth = screenSize.width;
	frameHeight = screenSize.height*3/4;
	frame.setSize(frameWidth,frameHeight );
   
	frame.setJMenuBar(createMenu());
	fileChooser = new JFileChooser();
	
	framePanel = new JPanel();
	framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.Y_AXIS));
	
	mainPanel = new MainPanel(this, id);
	
	framePanel.add(mainPanel.getPanel());
	mainScroll = new JScrollPane(framePanel);
	
	frame.getContentPane().add(mainScroll);
	//frame.getContentPane().add(framePanel);

	frame.setVisible(true);
		

}

public JMenuBar createMenu(){
		menuBar = new JMenuBar();
		editMenu = new JMenu("Edit");
		fileMenu = new JMenu("File");
		
       
		addItem = new JMenuItem("Add New Molecule Panel");
		addItem.setActionCommand("addItem");
		addItem.addActionListener(this);
		addItem.setEnabled(false);
		
		loadScript = new JMenuItem("Load Script");
		loadScript.addActionListener(this);
		loadScript.setActionCommand("loadScript");
		loadScript.setEnabled(true); 

		saveScript = new JMenuItem("Save Script");
		saveScript.addActionListener(this);
		saveScript.setActionCommand("saveScript");
		saveScript.setEnabled(true);
		
		fileMenu.add(loadScript);
		fileMenu.add(saveScript);
		editMenu.add(addItem);

		
		
		
		//menuBar.add(fileMenu);
		menuBar.add(editMenu);


return menuBar;
}


public void setEditEnable(boolean t){
	addItem.setEnabled(t);
}

public MainPanel getMainPanel(){
	return mainPanel;
}


public String [] getAtomsNames(){
	return elementNames;
}
public String [] getAtomsStrings(){
	return elementStrings;
}
public String [] getAminoNames(){
	return aminoNames;
}

public JTextArea getScriptArea(int id){
	return scriptArea[id];
}


//from script panel
public void saveScript(String str){
	fileChooser.setSelectedFile(new File(".txt"));
	int returnVal = fileChooser.showSaveDialog(HTMLBuilder.this);
	
    if (returnVal == JFileChooser.APPROVE_OPTION) {
	
        File file = fileChooser.getSelectedFile();
		try{
			 Writer output = new BufferedWriter(new FileWriter(file));
		output.write(str);
		//html = new HTML(file, id, mainTitle, titleField, scriptArea, pdbFiles, scriptField, wireFrameTool, spaceFillTool);
		//html.print();
		output.close();
		
		}catch (IOException ee){
			ee.printStackTrace();
		}
	}
}

public String loadScript(){
	fileChooser.setSelectedFile(new File(".txt"));
	int returnVal = fileChooser.showOpenDialog(HTMLBuilder.this);
	String str = "";
    if (returnVal == JFileChooser.APPROVE_OPTION) {
	
        File file = fileChooser.getSelectedFile();
       
		try{
			BufferedReader input =  new BufferedReader(new FileReader(file));
		      try {
		        String line = null; //not declared within while loop
		        while (( line = input.readLine()) != null){
		          str += line+"\n";
		          //contents.append(System.getProperty("line.separator"));
		        }
		      }
		      finally {
		        input.close();
		      }
		    }
		    catch (IOException ex){
		      ex.printStackTrace();
		    }
    }
    return str;
	
}
//from MainPanel

public int getFrameWidth(){
	return frameWidth;
}


public void actionPerformed(ActionEvent e) {
	Object source = e.getSource();
	
    if ("saveScript".equals(e.getActionCommand())) {

			fileChooser.setSelectedFile(new File(".txt"));
			int returnVal = fileChooser.showSaveDialog(HTMLBuilder.this);
			
            if (returnVal == JFileChooser.APPROVE_OPTION) {
			
                File file = fileChooser.getSelectedFile();
				try{
					 Writer output = new BufferedWriter(new FileWriter(file));
				output.write("testing");
				//html = new HTML(file, id, mainTitle, titleField, scriptArea, pdbFiles, scriptField, wireFrameTool, spaceFillTool);
				//html.print();
				output.close();
				
				}catch (IOException ee){
					ee.printStackTrace();
				}
			}
	
    }else if ("addItem".equals(e.getActionCommand())) {
		System.out.println("Add item works");
		++id;
		mainPanel = new MainPanel(this, id);
		framePanel.add(mainPanel.getPanel());
		frame.pack();
		
	}else if ("removeItem".equals(e.getActionCommand())) {
		System.out.println("Remove item works");
	
		
	}        
}


  final static String strXyzHOH = 
    "3\n" +
    "water\n" +
    "O  0.0 0.0 0.0\n" +
    "H  0.76923955 -0.59357141 0.0\n" +
    "H -0.76923955 -0.59357141 0.0\n";

  final static String strScript = "delay; move 360 0 0 0 0 0 0 0 4;";

final static String [] elementStrings = {
   "H", "He","Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca", "Sc", "Ti", 
   "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", 
   "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", 
   "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po",
   "At", "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md", "No",
   "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds", "Rg", "Uub", "Uut", "Uuq", "Uup", "Uuh", "Uus", "Uuo"
  };

final static String [] elementNames = {"Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon", "Nitrogen",
	"Oxygen", "Fluorine", "Neon", "Sodium", "Magnesium", "Aluminum", "Silicon", "Phosphorus", "Sulfur", "Chlorine",
	"Argon", "Potassium", "Calcium", "Scandium", "Titanium", "Vanadium", "Chromium", "Manganese", "Iron", "Cobalt", "Nickel", "Copper", "Zinc",
	"Gallium", "Germanium", "Arsenic", "Selenium", "Bromine", "Krypton", "Rubidium", "Strontium", "Yttrium", "Zirconium", "Niobium",
	"Molybdenum", "Technetium", "Ruthenium", "Rhodium", "Palladium", "Silver", "Cadmium", "Indium", "Tin", "Antimony", "Tellurium", "Iodine",
	"Xenon", "Cesium", "Barium", "Lanthanum", "Cerium", "Praseodymium", "Neodymium", "Promethium", "Samarium", "Europium", "Gadolinium", "Terbium",
	"Dysprosium", "Holmium", "Erbium", "Thulium", "Ytterbium", "Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium", "Osmium", "Iridium", "Platinum",
	"Gold", "Mercury", "Thallium", "Lead", "Bismuth", "Polonium", "Astatine", "Radon", "Francium", "Radium", "Actinium", "Thorium", "Protactinium",
	"Uranium", "Neptunium", "Plutonium", "Americium", "Curium", "Berkelium", "Californium", "Einsteinium", "Fermium", "Mendelevium", "Nobelium",
	"Lawrencium", "Rutherfordium", "Dubnium", "Seaborgium", "Bohrium", "Hassium", "Meitnerium", "Darmstadtium", "Roentgenium", "Ununbium",
	"Ununtrium", "Ununquadium", "Ununpentium", "Ununhexium", "Ununseptium", "Ununoctium"
	  };

final static String [] aminoNames = {"Ala", "Cys", "Asp", "Glu", "Phe", "Gly", "His", "Ile", "Lys", "Leu", "Met", "Asn", "Pyl", "Pro", "Gln", "Arg", "Ser", "Thr", "Sec", "Val", "Trp", "Tyr"};

  static class ApplicationCloser extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
      System.exit(0);
    }
  }


  
  
}