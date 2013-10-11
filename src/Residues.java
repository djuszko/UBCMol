import java.awt.BorderLayout;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Residues implements ActionListener {

	private HTMLBuilder builder;
	private int id;
	private MainPanel mainPanel;
	private JButton acidicButton, basicButton, neutralButton, aromaticButton, polarButton, hydroButton, aminoButton, cyclicButton, acyclicButton, aliphaticButton,
		surfaceButton, buriedButton, turnButton, sheetButton, helixButton, terButton, nucleotideAButton, nucleotideCButton, nucleotideGButton, nucleotideIButton,
		nucleotideTButton, nucleotideUButton, nucleotideDAButton, nucleotideDCButton, nucleotideDGButton, nucleotideDIButton, nucleotideDTButton, nucleotideDUButton,
		DNAButton, nucleicButton, purineButton, pyrimidineButton, RNAButton;

	private int aminoCounter = 0;

	private JPanel residuesPanel, residuesSubPanel;

	public Residues(HTMLBuilder builder, MainPanel mainPanel, int id) {

		this.mainPanel = mainPanel;
		this.id = id;
		this.builder = builder;
		residuesPanel = new JPanel();
		residuesPanel.setLayout(new BorderLayout());

		//Create SETS sub-panel
		residuesSubPanel = new JPanel();
		residuesSubPanel.setLayout(new BoxLayout(residuesSubPanel, BoxLayout.Y_AXIS));
		residuesSubPanel.add(new JLabel("Sets:"));

		//Create ACIDIC button and add it to SETS
		acidicButton = new JButton("acidic");
		acidicButton.setSelected(false);
		acidicButton.addActionListener(this);
		acidicButton.setActionCommand("acidic");
		residuesSubPanel.add(acidicButton);

		//Create BASIC button and add it to SETS
		basicButton = new JButton("basic");
		basicButton.setSelected(false);
		basicButton.addActionListener(this);
		basicButton.setActionCommand("basic");
		residuesSubPanel.add(basicButton);

		//Create NEUTRAL button and add it to SETS
		neutralButton = new JButton("neutral");
		neutralButton.setSelected(false);
		neutralButton.addActionListener(this);
		neutralButton.setActionCommand("neutral");
		residuesSubPanel.add(neutralButton);

		//Create CYCLIC button and add it to SETS
		cyclicButton = new JButton("cyclic");
		cyclicButton.setSelected(false);
		cyclicButton.addActionListener(this);
		cyclicButton.setActionCommand("cyclic");
		residuesSubPanel.add(cyclicButton);
		
		//Create ACYCLIC button and add it to SETS
		acyclicButton = new JButton("acyclic");
		acyclicButton.setSelected(false);
		acyclicButton.addActionListener(this);
		acyclicButton.setActionCommand("acyclic");
		residuesSubPanel.add(acyclicButton);
		
		//Create ALIPHATIC button and add it to SETS
		aliphaticButton = new JButton("aliphatic");
		aliphaticButton.setSelected(false);
		aliphaticButton.addActionListener(this);
		aliphaticButton.setActionCommand("aliphatic");
		residuesSubPanel.add(aliphaticButton);
		
		//Create AROMATIC button and add it to SETS
		aromaticButton = new JButton("aromatic");
		aromaticButton.setSelected(false);
		aromaticButton.addActionListener(this);
		aromaticButton.setActionCommand("aromatic");
		residuesSubPanel.add(aromaticButton);

		//Create SURFACE button and add it to SETS
		surfaceButton = new JButton("surface");
		surfaceButton.setSelected(false);
		surfaceButton.addActionListener(this);
		surfaceButton.setActionCommand("surface");
		residuesSubPanel.add(surfaceButton);
		
		//Create BURIED button and add it to SETS
		buriedButton = new JButton("buried");
		buriedButton.setSelected(false);
		buriedButton.addActionListener(this);
		buriedButton.setActionCommand("aliphatic");
		residuesSubPanel.add(buriedButton);
		
		//Create POLAR button and add it to SETS
		polarButton = new JButton("polar");
		polarButton.setSelected(false);
		polarButton.addActionListener(this);
		polarButton.setActionCommand("polar");
		residuesSubPanel.add(polarButton);

		//Create HYDROPHOBIC button and add it to SETS
		hydroButton = new JButton("hydrophobic");
		hydroButton.setSelected(false);
		hydroButton.addActionListener(this);
		hydroButton.setActionCommand("hydrophobic");
		residuesSubPanel.add(hydroButton);

		//Add SETS sub-panel to tool window
		residuesPanel.add(residuesSubPanel, BorderLayout.WEST);

		
		//Create CENTER sub-panel (includes AMINO ACIDS, SELECT, and NUCLEOTIDES columns)
		residuesSubPanel = new JPanel();
		residuesSubPanel.setLayout(new BoxLayout(residuesSubPanel, BoxLayout.Y_AXIS));

		//Create buttons for all amino acids and add them to AMINO ACIDS	
		boolean createAmino = true;
		for (int i = 0; i < mainPanel.getAminoCount().length; i++) {
			if (mainPanel.getAminoCount()[i] != 0) {
				
				//If any AMINO ACIDS exist,
				//Create AMINO ACIDS column
				if(createAmino){
					residuesSubPanel.add(new JLabel("Amino Acids:"));
					createAmino = false;
				}
				
				aminoButton = new JButton(builder.getAminoNames()[i] + " ("+ mainPanel.getAminoCount()[i] + ")");
				aminoButton.setSelected(false);
				aminoButton.addActionListener(this);
				aminoButton.setActionCommand("aminoCheck" + i);
				aminoCounter++;
				residuesSubPanel.add(aminoButton);
			}
		}

		
		//Create NUCLEOTIDES column
		residuesSubPanel.setLayout(new BoxLayout(residuesSubPanel, BoxLayout.Y_AXIS));
		residuesSubPanel.add(new JLabel("Nucleotides:"));
		
		//Create A button and add it to NUCLEOTIDES
		nucleotideAButton = new JButton("A");
		nucleotideAButton.setSelected(false);
		nucleotideAButton.addActionListener(this);
		nucleotideAButton.setActionCommand("A");
		residuesSubPanel.add(nucleotideAButton);
		
		//Create C button and add it to NUCLEOTIDES
		nucleotideCButton = new JButton("C");
		nucleotideCButton.setSelected(false);
		nucleotideCButton.addActionListener(this);
		nucleotideCButton.setActionCommand("C");
		residuesSubPanel.add(nucleotideCButton);
		
		//Create G button and add it to NUCLEOTIDES
		nucleotideGButton = new JButton("G");
		nucleotideGButton.setSelected(false);
		nucleotideGButton.addActionListener(this);
		nucleotideGButton.setActionCommand("G");
		residuesSubPanel.add(nucleotideGButton);
		
		//Create I button and add it to NUCLEOTIDES
		nucleotideIButton = new JButton("I");
		nucleotideIButton.setSelected(false);
		nucleotideIButton.addActionListener(this);
		nucleotideIButton.setActionCommand("I");
		residuesSubPanel.add(nucleotideIButton);
		
		//Create T button and add it to NUCLEOTIDES
		nucleotideTButton = new JButton("T");
		nucleotideTButton.setSelected(false);
		nucleotideTButton.addActionListener(this);
		nucleotideTButton.setActionCommand("T");
		residuesSubPanel.add(nucleotideTButton);
		
		//Create U button and add it to NUCLEOTIDES
		nucleotideUButton = new JButton("U");
		nucleotideUButton.setSelected(false);
		nucleotideUButton.addActionListener(this);
		nucleotideUButton.setActionCommand("U");
		residuesSubPanel.add(nucleotideUButton);
		
		//Create DA button and add it to NUCLEOTIDES
		nucleotideDAButton = new JButton("DA");
		nucleotideDAButton.setSelected(false);
		nucleotideDAButton.addActionListener(this);
		nucleotideDAButton.setActionCommand("DA");
		residuesSubPanel.add(nucleotideDAButton);
		
		//Create DC button and add it to NUCLEOTIDES
		nucleotideDCButton = new JButton("DC");
		nucleotideDCButton.setSelected(false);
		nucleotideDCButton.addActionListener(this);
		nucleotideDCButton.setActionCommand("DC");
		residuesSubPanel.add(nucleotideDCButton);
		
		//Create DG button and add it to NUCLEOTIDES
		nucleotideDGButton = new JButton("DG");
		nucleotideDGButton.setSelected(false);
		nucleotideDGButton.addActionListener(this);
		nucleotideDGButton.setActionCommand("DG");
		residuesSubPanel.add(nucleotideDGButton);
		
		//Create DI button and add it to NUCLEOTIDES
		nucleotideDIButton = new JButton("DI");
		nucleotideDIButton.setSelected(false);
		nucleotideDIButton.addActionListener(this);
		nucleotideDIButton.setActionCommand("DI");
		residuesSubPanel.add(nucleotideDIButton);
		
		//Create DT button and add it to NUCLEOTIDES
		nucleotideDTButton = new JButton("DT");
		nucleotideDTButton.setSelected(false);
		nucleotideDTButton.addActionListener(this);
		nucleotideDTButton.setActionCommand("DT");
		residuesSubPanel.add(nucleotideDTButton);
		
		//Create DU button and add it to NUCLEOTIDES
		nucleotideDUButton = new JButton("DU");
		nucleotideDUButton.setSelected(false);
		nucleotideDUButton.addActionListener(this);
		nucleotideDUButton.setActionCommand("DU");
		residuesSubPanel.add(nucleotideDUButton);		
		
		//Create SELECT column
		residuesSubPanel.setLayout(new BoxLayout(residuesSubPanel, BoxLayout.Y_AXIS));
		residuesSubPanel.add(new JLabel("Select:"));
		
		//Create DNA button and add it to SELECT
		DNAButton = new JButton("DNA");
		DNAButton.setSelected(false);
		DNAButton.addActionListener(this);
		DNAButton.setActionCommand("DNA");
		residuesSubPanel.add(DNAButton);
		
		//Create nucleic button and add it to SELECT
		nucleicButton = new JButton("nucleic");
		nucleicButton.setSelected(false);
		nucleicButton.addActionListener(this);
		nucleicButton.setActionCommand("nucleic");
		residuesSubPanel.add(nucleicButton);
		
		//Create purine button and add it to SELECT
		purineButton = new JButton("purine");
		purineButton.setSelected(false);
		purineButton.addActionListener(this);
		purineButton.setActionCommand("purine");
		residuesSubPanel.add(purineButton);
		
		//Create pyrimidine button and add it to SELECT
		pyrimidineButton = new JButton("pyrimidine");
		pyrimidineButton.setSelected(false);
		pyrimidineButton.addActionListener(this);
		pyrimidineButton.setActionCommand("pyrimidine");
		residuesSubPanel.add(pyrimidineButton);
		
		//Create RNA button and add it to SELECT
		RNAButton = new JButton("RNA");
		RNAButton.setSelected(false);
		RNAButton.addActionListener(this);
		RNAButton.setActionCommand("RNA");
		residuesSubPanel.add(RNAButton);
		
		//Add CENTER sub-panel to tool window
		residuesPanel.add(residuesSubPanel, BorderLayout.CENTER);
			
		
		//Create CHAINS sub-panel
		residuesSubPanel = new JPanel();
		residuesSubPanel.setLayout(new BoxLayout(residuesSubPanel, BoxLayout.Y_AXIS));
		residuesSubPanel.add(new JLabel("Chains:"));
		
		//If any exist,
		//Create PROTEINS button and add it to CHAINS
		if(mainPanel.getFoundTER()){
 			terButton = new JButton("Proteins");
 			terButton.setSelected(false);
 			terButton.addActionListener(this);
 			terButton.setActionCommand("protein");
 			residuesSubPanel.add(terButton);	
 		}
		
		//If any exist,
		//Create HELIXES button and add it to CHAINS
 	   if(mainPanel.getFoundHELIX()){
 			helixButton = new JButton("Helix");
 			helixButton.setSelected(false);
 			helixButton.addActionListener(this);
 			helixButton.setActionCommand("helix");
 			residuesSubPanel.add(helixButton);	
 		}
 	   
		//If any exist,
		//Create SHEETS button and add it to CHAINS
 		if(mainPanel.getFoundSHEET()){
 			sheetButton = new JButton("Sheet");
 			sheetButton.setSelected(false);
 			sheetButton.addActionListener(this);
 			sheetButton.setActionCommand("sheet");
 			residuesSubPanel.add(sheetButton);	
 		}
 		
		//If any exist,
		//Create TURNS button and add it to CHAINS
 		if(mainPanel.getFoundTURN()){
 			turnButton = new JButton("Turn");
 			turnButton.setSelected(false);
 			turnButton.addActionListener(this);
 			turnButton.setActionCommand("turn");
 			residuesSubPanel.add(turnButton);	
 		}
 		
		//Add CHAINS sub-panel to tool window
 		residuesPanel.add(residuesSubPanel, BorderLayout.EAST);
	}

	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == terButton){
			mainPanel.getScriptPanel().getScriptArea().insert("select protein"+";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select protein"+";", false);
		}else if (source == helixButton){
			mainPanel.getScriptPanel().getScriptArea().insert("select helix"+";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select helix"+";", false);
		}else if (source == sheetButton){
			mainPanel.getScriptPanel().getScriptArea().insert("select sheet"+";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select sheet"+";", false);
		}else if (source == turnButton){
			mainPanel.getScriptPanel().getScriptArea().insert("select turn"+";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select turn"+";", false);
		}else if (source == acidicButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select acidic" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select acidic" + ";", false);
		} else if (source == basicButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select basic" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select basic" + ";", false);
		} else if (source == neutralButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select neutral" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select neutral" + ";", false);
		} else if (source == aromaticButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select aromatic" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select aromatic" + ";", false);
		} else if (source == polarButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select polar" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select polar" + ";", false);
		} else if (source == hydroButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select hydrophobic" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select hydrophobic" + ";",false);
		} else if (source == cyclicButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select cyclic" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select cyclic" + ";",false);
		} else if (source == acyclicButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select acyclic" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select acyclic" + ";",false);
		} else if (source == aliphaticButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select aliphatic" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select aliphatic" + ";",false);
		} else if (source == surfaceButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select surface" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select surface" + ";",false);
		} else if (source == buriedButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select buried" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select buried" + ";",false);
		} else if (source == nucleotideAButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select A" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select A" + ";",false);
		} else if (source == nucleotideCButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select C" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select C" + ";",false);
		} else if (source == nucleotideGButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select G" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select G" + ";",false);
		} else if (source == nucleotideIButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select I" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select I" + ";",false);
		} else if (source == nucleotideTButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select T" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select T" + ";",false);
		} else if (source == nucleotideUButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select U" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select U" + ";",false);
		} else if (source == nucleotideDAButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select DA" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select DA" + ";",false);
		} else if (source == nucleotideDCButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select DC" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select DC" + ";",false);
		} else if (source == nucleotideDGButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select DG" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select DG" + ";",false);
		} else if (source == nucleotideDIButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select DI" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select DI" + ";",false);
		} else if (source == nucleotideDTButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select DT" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select DT" + ";",false);
		} else if (source == nucleotideDUButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select DU" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select DU" + ";",false);
		} else if (source == DNAButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select DNA" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select DNA" + ";",false);
		} else if (source == nucleicButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select nucleic" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select nucleic" + ";",false);
		} else if (source == purineButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select purine" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select purine" + ";",false);
		} else if (source == pyrimidineButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select pyrimidine" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select pyrimidine" + ";",false);
		} else if (source == RNAButton) {
			mainPanel.getScriptPanel().getScriptArea().insert("select RNA" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select RNA" + ";",false);
		} 
		else {
			for (int i = 0; i < aminoCounter; i++) {
				if (("aminoCheck" + i).equals(e.getActionCommand())) {
					AbstractButton abstractButton = (AbstractButton) e.getSource();
					String text = abstractButton.getText();

					int s = text.indexOf(" ");
					if (s != -1)
						text = text.substring(0, s);

					System.out.println(text);
					System.out.println(abstractButton.getModel().isSelected());
					if (text == "All") {
						mainPanel.getScriptPanel().getScriptArea().insert("select All" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
						mainPanel.getScriptPanel().runScript("select All" + ";", false);
					} else {
						mainPanel.getScriptPanel().getScriptArea().insert("select " + text + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
						mainPanel.getScriptPanel().runScript("select " + text + ";", false);
					}
				}
			}
		}
	}

	public JPanel getPanel() {
		return residuesPanel;
	}
}
