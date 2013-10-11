import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
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
public class JmolColor extends JComponent implements ChangeListener, ActionListener{

	private HTMLBuilder builder;
	private int id;
	private MainPanel mainPanel;
	
	private JColorChooser colorChooser;
	private String command;
	private JPanel choicePanel;
	private JCheckBox strandsCheckBox, ribbonsCheckBox, cartoonsCheckBox, hbondsCheckBox, ssbondsCheckBox, backgroundCheckBox;
	
	public JmolColor(HTMLBuilder builder, MainPanel mainPanel, int id, String command){
		
		this.builder = builder;
		this.id = id;
		this.mainPanel = mainPanel;
		this.command = command;
		colorChooser = new JColorChooser();
		 AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
		 colorChooser.removeChooserPanel(panels[2]); 
		 colorChooser.removeChooserPanel(panels[1]); 
		colorChooser.setPreviewPanel(new JPanel());
		colorChooser.getSelectionModel().addChangeListener(this);
		
		//make choice panel
		choicePanel = new JPanel();
		choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.Y_AXIS));
		
		ribbonsCheckBox = new JCheckBox("Ribbons");
		ribbonsCheckBox.setSelected(false);
		ribbonsCheckBox.addActionListener(this);		
		ribbonsCheckBox.setActionCommand("ribbons");
		ribbonsCheckBox.setEnabled(false);
		choicePanel.add(ribbonsCheckBox);
		
		strandsCheckBox = new JCheckBox("Strands");
		strandsCheckBox.setSelected(false);
		strandsCheckBox.addActionListener(this);		
		strandsCheckBox.setActionCommand("strands");
		strandsCheckBox.setEnabled(false);
		
		choicePanel.add(strandsCheckBox);
		
		cartoonsCheckBox = new JCheckBox("Cartoons");
		cartoonsCheckBox.setSelected(false);
		cartoonsCheckBox.addActionListener(this);		
		cartoonsCheckBox.setActionCommand("cartoons");
		cartoonsCheckBox.setEnabled(false);
		
		choicePanel.add(cartoonsCheckBox);
		
		hbondsCheckBox = new JCheckBox("Hbonds");
		hbondsCheckBox.setSelected(false);
		hbondsCheckBox.addActionListener(this);		
		hbondsCheckBox.setActionCommand("hbonds");
		hbondsCheckBox.setEnabled(false);
		
		choicePanel.add(hbondsCheckBox);
		
		ssbondsCheckBox = new JCheckBox("SSbonds");
		ssbondsCheckBox.setSelected(false);
		ssbondsCheckBox.addActionListener(this);		
		ssbondsCheckBox.setActionCommand("ssbonds");
		ssbondsCheckBox.setEnabled(false);
		
		choicePanel.add(ssbondsCheckBox);
		
		backgroundCheckBox = new JCheckBox("Background");
		backgroundCheckBox.setSelected(false);
		backgroundCheckBox.addActionListener(this);		
		backgroundCheckBox.setActionCommand("background");
		
		choicePanel.add(backgroundCheckBox);
	
		
	}
	
	public void stateChanged(ChangeEvent e) {
		
	

			Color newColor = colorChooser.getColor();
			
			//mainPanel.getScriptPanel().getScriptArea().append("color ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];\n");
			mainPanel.getScriptPanel().getScriptArea().insert(command+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript(command+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];", false);
			
			strandsCheckBox.setSelected(false); 
			ribbonsCheckBox.setSelected(false);  
			cartoonsCheckBox.setSelected(false); 
			hbondsCheckBox.setSelected(false); 
			ssbondsCheckBox.setSelected(false); 
			backgroundCheckBox.setSelected(false); 
			command = "color";
	}
	
	public JColorChooser getChooser(){
		return colorChooser;
	}
	
	public JPanel getChoicePanel(){
		return choicePanel;
	}

	public void actionPerformed(ActionEvent e){
		
		System.out.println(choicePanel.getComponentCount());
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

