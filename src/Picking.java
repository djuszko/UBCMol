import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JLabel;



import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;



public class Picking implements ActionListener{

	private HTMLBuilder builder;
	private int id;
	private MainPanel mainPanel;
	
	private JPanel panel;
	private JScrollPane infoScroll;
	
	private JButton selectAtom, selectMolecule, selectElement, selectChain, selectGroup, off, center, identity, label, distance, angle, torsion, monitor;
	private JTextArea atomInfoArea;
	
	public Picking (HTMLBuilder builder, MainPanel mainPanel, int id){
		
		this.builder = builder;
		this.id = id;
		this.mainPanel = mainPanel;
		
		panel = new JPanel();
 		panel.setLayout(new BorderLayout());
 	     
 	     JPanel subPanel = new JPanel();
 	    subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
 	   
 	   subPanel.add(new JLabel("Select:"));
 	   
 	   selectAtom = new JButton("Atom");
 	   selectAtom.addActionListener(this);
 	   selectAtom.setActionCommand("set picking atom");
 	   
		subPanel.add(selectAtom);
		
		selectMolecule = new JButton("Molecule");
	
		selectMolecule.addActionListener(this);
		selectMolecule.setActionCommand("set picking molecule");
	 	   
	 	subPanel.add(selectMolecule);

	 	selectElement = new JButton("Element");
	 
	 	selectElement.addActionListener(this);
	 	selectElement.setActionCommand("set picking element");
	 	   
	 	subPanel.add(selectElement);
	 	
	 	selectChain = new JButton("Chain");
	 	
	 	selectChain.addActionListener(this);
	 	selectChain.setActionCommand("set picking chain");
	 	   
	 	subPanel.add(selectChain);	 	
	 	
	 	selectGroup = new JButton("Group");
	
	 	selectGroup.addActionListener(this);
	 	selectGroup.setActionCommand("set picking group");
	 	   
	 	subPanel.add(selectGroup);
	 	
	 	
 		panel.add(subPanel, BorderLayout.WEST);
 		
 		subPanel = new JPanel();
 	    subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
 	    subPanel.add(new JLabel("Picking:"));
 	   
 	    off = new JButton("Off");
	 	off.addActionListener(this);
	 	off.setActionCommand("set picking off");
 	    
	 	subPanel.add(off);
 		
	 	monitor = new JButton("Monitor");
	 	monitor.addActionListener(this);
	 	monitor.setActionCommand("set picking monitor");
	 	subPanel.add(monitor);
	 	
	 	center = new JButton("Center (1)");
	 	center.addActionListener(this);
	 	center.setActionCommand("set picking center");
 	    
	 	subPanel.add(center);
	 	
	 	identity = new JButton("Identity (1)");
	 	identity.addActionListener(this);
	 	identity.setActionCommand("set picking ident");
 	    
	 	subPanel.add(identity);
	 	
	 	label = new JButton("Label (1)");
	 	label.addActionListener(this);
	 	label.setActionCommand("set picking label");
	 	subPanel.add(label);
	 	
	 	distance = new JButton("Distance (2)");
	 	distance.addActionListener(this);
	 	distance.setActionCommand("set picking distance");
	 	subPanel.add(distance);
	 	
	 	angle = new JButton("Angle (3)");
	 	angle.addActionListener(this);
	 	angle.setActionCommand("set picking angle");
	 	subPanel.add(angle);
	 	
	 	torsion = new JButton("Torsion (4)");
	 	torsion.addActionListener(this);
	 	torsion.setActionCommand("set picking torsion");
	 	subPanel.add(torsion);
	 	
	 	
	 	
	 	
	 	
	 	
	 	panel.add(subPanel, BorderLayout.CENTER);
	 	
	 	 Border blackline = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Label");
	 	 
	 	subPanel = new JPanel();
 	    subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
 	   
 	    
 	    
 	    atomInfoArea = new JTextArea();
		
 	    atomInfoArea.setLineWrap(true);
		atomInfoArea.setEnabled(true);
		
 	    //subPanel.add(atomInfoArea);
 	    infoScroll = new JScrollPane(atomInfoArea);
 	    infoScroll.setMaximumSize(new Dimension(400,200));
 	    infoScroll.setMinimumSize(new Dimension(400,200));
 	    infoScroll.setPreferredSize(new Dimension(400,200));
 	   
 	  
 	 
 	  // infoScroll.setMaximumSize(new Dimension(400,200));
 	    //subPanel.add(infoScroll);
 	   //subPanel.setBorder(blackline);
 	   //subPanel.setMaximumSize(new Dimension(400,200));
 	   panel.add(infoScroll, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e){
		
		
				atomInfoArea.append(e.getActionCommand()+":\n");
				//mainPanel.getScriptPanel().getScriptArea().append("select protein"+";\n");
				//mainPanel.getScriptPanel().getScriptArea().insert(e.getActionCommand()+";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
				mainPanel.getScriptPanel().runScript(e.getActionCommand()+";", true);
			
				
			
		
	}
	public JPanel getPickingPanel(){
		return panel;
	}
	
	public void setAtomInfoText(String s){
		atomInfoArea.append(s+"\n");
		//selectButton.setEnabled(true);
		//centreButton.setEnabled(true);
	}
}
