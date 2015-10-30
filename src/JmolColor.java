import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JmolColor extends JComponent implements ChangeListener, ActionListener{

	private HTMLBuilder builder;
	private int id;
	private MainPanel mainPanel;

	private JColorChooser colorChooser;
	private String command;
	private JPanel colorPanel, rasmolPanel, outerPanel;
	private JCheckBox strandsCheckBox, ribbonsCheckBox, cartoonsCheckBox, hbondsCheckBox, ssbondsCheckBox, backgroundCheckBox, labelsCheckBox;
	private JButton strandsButton, ribbonsButton, cartoonsButton, hbondsButton, ssbondsButton, backgroundButton, cpkButton, aminoButton, shapelyButton, groupButton, structureButton, chainButton, chargeButton;

	public JmolColor(HTMLBuilder builder, MainPanel mainPanel, int id, String command){

		this.builder = builder;
		this.id = id;
		this.mainPanel = mainPanel;
		this.command = command;
		
		//Create COLOR CHOOSER
		colorChooser = new JColorChooser();
		AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
		colorChooser.removeChooserPanel(panels[2]); 
		colorChooser.removeChooserPanel(panels[1]); 
		colorChooser.setPreviewPanel(new JPanel());
		colorChooser.getSelectionModel().addChangeListener(this); 

		//Create COLOR PANEL sub-panel
		colorPanel = new JPanel();
		colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));

		//Create RIBBONS check box and add it to CHOICE PANEL
		ribbonsCheckBox = new JCheckBox("Ribbons");
		ribbonsCheckBox.setSelected(false);
		ribbonsCheckBox.addActionListener(this);		
		ribbonsCheckBox.setActionCommand("ribbons");
		ribbonsCheckBox.setEnabled(false);

		colorPanel.add(ribbonsCheckBox);

		//Create STRANDS check box and add it to CHOICE PANEL		
		strandsCheckBox = new JCheckBox("Strands");
		strandsCheckBox.setSelected(false);
		strandsCheckBox.addActionListener(this);		
		strandsCheckBox.setActionCommand("strands");
		strandsCheckBox.setEnabled(false);

		colorPanel.add(strandsCheckBox);

		//Create CARTOONS check box and add it to CHOICE PANEL	
		cartoonsCheckBox = new JCheckBox("Cartoons");
		cartoonsCheckBox.setSelected(false);
		cartoonsCheckBox.addActionListener(this);		
		cartoonsCheckBox.setActionCommand("cartoons");
		cartoonsCheckBox.setEnabled(false);

		colorPanel.add(cartoonsCheckBox);

		//Create HBONDS check box and add it to CHOICE PANEL	
		hbondsCheckBox = new JCheckBox("Hbonds");
		hbondsCheckBox.setSelected(false);
		hbondsCheckBox.addActionListener(this);		
		hbondsCheckBox.setActionCommand("hbonds");
		hbondsCheckBox.setEnabled(false);

		colorPanel.add(hbondsCheckBox);

		//Create SSBONDS check box and add it to CHOICE PANEL	
		ssbondsCheckBox = new JCheckBox("SSbonds");
		ssbondsCheckBox.setSelected(false);
		ssbondsCheckBox.addActionListener(this);		
		ssbondsCheckBox.setActionCommand("ssbonds");
		ssbondsCheckBox.setEnabled(false);

		colorPanel.add(ssbondsCheckBox);

		//Create BACKGROUND check box and add it to CHOICE PANEL	
		backgroundCheckBox = new JCheckBox("Background");
		backgroundCheckBox.setSelected(false);
		backgroundCheckBox.addActionListener(this);		
		backgroundCheckBox.setActionCommand("background");

		colorPanel.add(backgroundCheckBox);

		//Create LABELS check box and add it to CHOICE PANEL	
		labelsCheckBox = new JCheckBox("Labels");
		labelsCheckBox.setSelected(false);
		labelsCheckBox.addActionListener(this);
		labelsCheckBox.setActionCommand("labels");

		colorPanel.add(labelsCheckBox);
	}

	public void stateChanged(ChangeEvent e) {
		Color newColor = colorChooser.getColor();

		mainPanel.getScriptPanel().getScriptArea().insert(command+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().runScript(command+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];", false);

		strandsCheckBox.setSelected(false); 
		ribbonsCheckBox.setSelected(false);  
		cartoonsCheckBox.setSelected(false); 
		hbondsCheckBox.setSelected(false); 
		ssbondsCheckBox.setSelected(false); 
		backgroundCheckBox.setSelected(false); 
		labelsCheckBox.setSelected(false);
		command = "color";
	}

	public JColorChooser getChooser(){
		return colorChooser;
	}

	public JPanel getColorPanel(){
		return colorPanel;
	}
	
	public JPanel getRasmolPanel(){
		return rasmolPanel;
	}

	public void actionPerformed(ActionEvent e){
		System.out.println(colorPanel.getComponentCount());
		System.out.println(e.getActionCommand());
		command = "color "+e.getActionCommand();

	}

	public void setStrandsEnabled(boolean b){
		strandsCheckBox.setEnabled(b);
	}
	public void setRibbonsEnabled(boolean b){
		ribbonsCheckBox.setEnabled(b);
	}
	public void setCartoonsEnabled(boolean b){
		cartoonsCheckBox.setEnabled(b);
	}
	public void setHBondsEnabled(boolean b){
		hbondsCheckBox.setEnabled(b);
	}
	public void setSSBondsEnabled(boolean b){
		ssbondsCheckBox.setEnabled(b);
	}
}

