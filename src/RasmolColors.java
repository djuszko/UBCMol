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
/**
 * @author nmorin
 *
 */
public class RasmolColors implements ActionListener{

	private HTMLBuilder builder;
	private int id;
	private MainPanel mainPanel;
	
	private JColorChooser colorChooser;
	private String command;
	private JPanel choicePanel;
	private JButton strandsButton, ribbonsButton, cartoonsButton, hbondsButton, ssbondsButton, backgroundButton, cpkButton, aminoButton, shapelyButton, groupButton, structureButton, chainButton;
	
	public RasmolColors(HTMLBuilder builder, MainPanel mainPanel, int id){
		
		this.builder = builder;
		this.id = id;
		this.mainPanel = mainPanel;
		
		//make choice panel
		choicePanel = new JPanel();
		choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.Y_AXIS));
		
		
	
		cpkButton = new JButton("CPK");
		cpkButton.setSelected(false);
		cpkButton.addActionListener(this);		
		cpkButton.setActionCommand("cpk");
		
		choicePanel.add(cpkButton);
		
		aminoButton = new JButton("Amino");
		aminoButton.setSelected(false);
		aminoButton.addActionListener(this);		
		aminoButton.setActionCommand("amino");
		
		choicePanel.add(aminoButton);
		
		shapelyButton = new JButton("Shapely");
		shapelyButton.setSelected(false);
		shapelyButton.addActionListener(this);		
		shapelyButton.setActionCommand("shapely");
		
		choicePanel.add(shapelyButton);
		
		groupButton = new JButton("Group");
		groupButton.setSelected(false);
		groupButton.addActionListener(this);		
		groupButton.setActionCommand("group");
		
		choicePanel.add(groupButton);
		
		structureButton = new JButton("Structure");
		structureButton.setSelected(false);
		structureButton.addActionListener(this);		
		structureButton.setActionCommand("structure");
		
		choicePanel.add(structureButton);
		
		chainButton = new JButton("Chain");
		chainButton.setSelected(false);
		chainButton.addActionListener(this);		
		chainButton.setActionCommand("chain");
		
		choicePanel.add(chainButton);
	
		
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
		   e.getActionCommand().equals("chain")){
			
			mainPanel.getScriptPanel().getScriptArea().insert("color "+e.getActionCommand()+";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color "+e.getActionCommand()+";", false);
			
		}
	}
}

