
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
import java.awt.event.MouseListener;
import java.awt.Toolkit;
import java.io.*;

import javax.swing.*;

import java.util.StringTokenizer;
import java.util.ArrayList;


import java.awt.Color;

import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import java.text.NumberFormat;
import org.jmol.adapter.smarter.SmarterJmolAdapter;
import org.jmol.api.JmolAdapter;
import org.jmol.api.JmolViewer;
import org.openscience.jmol.app.jmolpanel.JmolPanel;



public class HTMLBuilder extends JFrame implements ActionListener{

	private int frameWidth, frameHeight;
	private JmolViewer viewer;

	private JTextArea [] scriptArea = new JTextArea [10];
	private JTextField [] javascriptField = new JTextField[10];
	private JPanel javascriptPanel;
	private JButton [] testScriptButton = new JButton [10];
	private JButton [] resetButton = new JButton [10];
	private JButton [] printScriptButton = new JButton [10];
	private JPanel [] toolPanel = new JPanel[10];
	private File pdbFile;

	private MainPanel mainPanel;

	private JPanel eastPanel;
	private JScrollPane mainScroll;
	private File [] pdbFiles =  new File[10];
	private String pdbString;


	private FileDrop [] fileDrop =  new FileDrop[10];

	private JPanel framePanel;
	private JFrame frame;
	private int id = 0;
	private Toolkit toolkit;
	private Dimension screenSize;
	private JFileChooser fileChooser;
	private JMenuBar menuBar;
	private JMenu editMenu, fileMenu;
	private JMenuItem fileOpen, fileSaveAs;

	private ScriptPanel scriptPanel;
	
	private Picking picking;
	private JmolPanel jmolPanel;

	private Border blackline;
	private JPanel jPanel;
	private String version = "Version 2.4";

	public static void main(String[] argv) {

		HTMLBuilder builder = new HTMLBuilder();

	}


	public HTMLBuilder(){
		//Display name + version number
		frame = new JFrame("UBCMol " + version);
		
		//Set default size and create frame
		frame.addWindowListener(new ApplicationCloser());
		toolkit = Toolkit.getDefaultToolkit();
		screenSize = toolkit.getScreenSize();
		frameWidth = 1250;
		frameHeight = 785;
		frame.setSize(frameWidth,frameHeight );

		frame.setJMenuBar(createMenu());
		fileChooser = new JFileChooser();

		framePanel = new JPanel();
		framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.Y_AXIS));

		mainPanel = new MainPanel(this, id);

		framePanel.add(mainPanel.getPanel());
		mainScroll = new JScrollPane(framePanel);

		frame.getContentPane().add(mainScroll);
		frame.setVisible(true);
	}

	public JMenuBar createMenu(){
		// Create MENU
		menuBar = new JMenuBar();

		// Add FILE menu option
		fileMenu = new JMenu("File");


		// Add OPEN menu option to FILE
		fileOpen = new JMenuItem("Open");
		fileOpen.setActionCommand("Open");
		fileOpen.addActionListener(this);
		fileOpen.setEnabled(true);
		
		// Add Save As... option to FILE
		fileSaveAs = new JMenuItem("Save As...");
		fileSaveAs.setActionCommand("saveAs");
		fileSaveAs.addActionListener(this);
		fileSaveAs.setEnabled(true);

		fileMenu.add(fileOpen);
		fileMenu.add(fileSaveAs);

		menuBar.add(fileMenu);
		return menuBar;
	}


	public void setEditEnable(boolean t){
		//addItem.setEnabled(t);
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

	//Save button implementation
	public void saveScript(String str){
		fileChooser.setSelectedFile(new File(".txt"));
		int returnVal = fileChooser.showSaveDialog(HTMLBuilder.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try{
				Writer output = new BufferedWriter(new FileWriter(file));
				output.write(str);
				output.close();

			}catch (IOException ee){
				ee.printStackTrace();
			}
		}
	}

	//Load button implementation
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
				
		picking = new Picking(this, mainPanel, id);
		scriptPanel = new ScriptPanel(this, mainPanel, id);
		scriptPanel.setAllEnabled(false);
		
		if ("Open".equals(e.getActionCommand())) {
			int returnVal = fileChooser.showOpenDialog(HTMLBuilder.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					pdbFile = file;
					FileInputStream fis = new FileInputStream(file);
					String filename = file.getName();
					String ext = filename.substring(
							filename.lastIndexOf('.') + 1,
							filename.length());

					int size = (int) (file.length());
					byte[] in = new byte[size];
					fis.read(in);
					pdbString = new String(in);
					mainPanel.getViewer().openStringInline(pdbString);

					mainPanel.loadTools(mainPanel.getViewer(), pdbString, ext, filename, false);
					mainPanel.getScriptPanel().setAllEnabled(true);
					mainPanel.getScriptPanel().setPDBFileName(filename);

				} catch (FileNotFoundException ff) {
					ff.printStackTrace();
				} catch (IOException ee) {
					ee.printStackTrace();
				}

			}

		}else if ("saveAs".equals(e.getActionCommand())){	
			JFileChooser FC=new JFileChooser("C:/");
			pngSaveFilter fileFilter = new pngSaveFilter();
			
			FC.addChoosableFileFilter(fileFilter);
			FC.addChoosableFileFilter(new jpgSaveFilter());			
			
			FC.setFileFilter(fileFilter);

			int retrival=FC.showSaveDialog(null);
			
			if (retrival == FC.APPROVE_OPTION) {
				String ext="";
			
				String extension=FC.getFileFilter().getDescription();
				if(extension.equals("*.jpg,*.JPG")){
					scriptPanel.runScript("write IMAGE JPG " + FC.getSelectedFile().getName() + ".jpg", true);
				}
				if(extension.equals("*.png,*.PNG")){ 
					scriptPanel.runScript("write IMAGE PNG " + FC.getSelectedFile().getName() + ".png", true);
				}
			}
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


	class jpgSaveFilter extends FileFilter{ 
		public boolean accept(File f){
			if (f.isDirectory()){
				return false;
			}
			String s = f.getName();
			return s.endsWith(".jpg")||s.endsWith(".JPG");
		}
		public String getDescription() {
			return "*.jpg,*.JPG";
		}
	}
		
	class pngSaveFilter extends FileFilter{ 
		public boolean accept(File f){
			if (f.isDirectory()){
				return false;
			}
			String s = f.getName();
			return s.endsWith(".png")||s.endsWith(".PNG");
		}
		public String getDescription() {
			return "*.png,*.PNG";
		}
	}
}