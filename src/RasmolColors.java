import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RasmolColors implements ActionListener{

	private HTMLBuilder builder;
	private int id;
	private MainPanel mainPanel;

	private JColorChooser colorChooser;
	private String command;
	private JPanel choicePanel;
	private JButton strandsButton, ribbonsButton, cartoonsButton, hbondsButton, ssbondsButton, backgroundButton, cpkButton, aminoButton, shapelyButton, groupButton, structureButton, chainButton, chargeButton;

	public RasmolColors(HTMLBuilder builder, MainPanel mainPanel, int id){

		this.builder = builder;
		this.id = id;
		this.mainPanel = mainPanel;

		//Create CHOICE sub-panel
		choicePanel = new JPanel();
		choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.Y_AXIS));
		
		//Create CPK button and add it to CHOICE
		cpkButton = new JButton("CPK");
		cpkButton.setSelected(false);
		cpkButton.addActionListener(this);		
		cpkButton.setActionCommand("cpk");

		choicePanel.add(cpkButton);

		//Create AMINO button and add it to CHOICE
		aminoButton = new JButton("Amino");
		aminoButton.setSelected(false);
		aminoButton.addActionListener(this);		
		aminoButton.setActionCommand("amino");

		choicePanel.add(aminoButton);
		
		//Create SHAPELY button and add it to CHOICE
		shapelyButton = new JButton("Shapely");
		shapelyButton.setSelected(false);
		shapelyButton.addActionListener(this);		
		shapelyButton.setActionCommand("shapely");

		choicePanel.add(shapelyButton);

		//Create GROUP button and add it to CHOICE
		groupButton = new JButton("Group");
		groupButton.setSelected(false);
		groupButton.addActionListener(this);		
		groupButton.setActionCommand("group");

		choicePanel.add(groupButton);

		//Create STRUCTURE button and add it to CHOICE
		structureButton = new JButton("Structure");
		structureButton.setSelected(false);
		structureButton.addActionListener(this);		
		structureButton.setActionCommand("structure");

		choicePanel.add(structureButton);

		//Create CHAIN button and add it to CHOICE
		chainButton = new JButton("Chain");
		chainButton.setSelected(false);
		chainButton.addActionListener(this);		
		chainButton.setActionCommand("chain");

		choicePanel.add(chainButton);

		//Create CHARGE button and add it to CHOICE
		chargeButton = new JButton("Charge");
		chargeButton.setSelected(false);
		chargeButton.addActionListener(this);
		chargeButton.setActionCommand("formalCharge");

		choicePanel.add(chargeButton);
	}

	public JPanel getChoicePanel(){
		return choicePanel;
	}

	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("cpk") ||
				e.getActionCommand().equals("amino") ||
				e.getActionCommand().equals("shapely") ||
				e.getActionCommand().equals("group") ||
				e.getActionCommand().equals("structure") ||
				e.getActionCommand().equals("chain") ||
				e.getActionCommand().equals("formalCharge")){

			mainPanel.getScriptPanel().getScriptArea().insert("color "+e.getActionCommand()+";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color "+e.getActionCommand()+";", false);

		}
	}
}

