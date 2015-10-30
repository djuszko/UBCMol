/////////////////////////////////////////////////////////////////////////////////////////////
//   This is the SELECTION OPTIONS tab                                                     //
/////////////////////////////////////////////////////////////////////////////////////////////
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jmol.api.JmolCallbackListener;
import org.jmol.constant.EnumCallback;

public class Atoms implements ActionListener, PropertyChangeListener, JmolCallbackListener {

	private MainPanel mainPanel;
	private HTMLBuilder builder;
	private int id;
	private JPanel atomPanel, outerPanel, pickingPanel, subsubPanel, fillerPanel;
	private int atomCounter;
	private JButton selectAtom, selectMolecule, selectElement, selectChain,	selectGroup, selectVisible, selectStructure, off, center, identity, label, distance, angle,	torsion, monitor, withinButton, atomNo;
	private JFormattedTextField withinField, withinField2, atomNoField;
	private JScrollPane infoScroll;
	private AtomInfoArea AIA;
	
	private NumberFormat numberFormat = NumberFormat.getNumberInstance();

	public Atoms(HTMLBuilder builder, MainPanel mainPanel, int id) {

		this.mainPanel = mainPanel;
		this.id = id;
		this.builder = builder;
		
		//Create OUTER PANEL
		outerPanel = new JPanel();
		outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));
		outerPanel.setPreferredSize(new Dimension(100,0));
		
		atomPanel = new JPanel();
		atomPanel.setLayout(new BorderLayout());

		//Create ELEMENTS sub-panel
		JPanel atomSubPanel = new JPanel();
		atomSubPanel.setLayout(new BoxLayout(atomSubPanel, BoxLayout.Y_AXIS));
		atomSubPanel.add(new JLabel("Elements:"));
		atomSubPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			//Create ALL button and add it to ELEMENTS
			JButton atomButton;
			atomButton = new JButton("All");
			atomButton.setSelected(false);
			atomButton.addActionListener(this);
	
			//Create buttons for all elements and add them to ELEMENTS
			atomButton.setActionCommand("atomCheck" + atomCounter);
			++atomCounter;
			atomSubPanel.add(atomButton);
			for (int i = 0; i < mainPanel.getAtomsCount().length; i++) {
				if (mainPanel.getAtomsCount()[i] != 0) {
					atomButton = new JButton(builder.getAtomsNames()[i] + " ("+ mainPanel.getAtomsCount()[i] + ")");
					atomButton.setSelected(false);
					atomButton.addActionListener(this);
	
					atomButton.setActionCommand("atomCheck" + atomCounter);
					++atomCounter;
					atomSubPanel.add(atomButton);
	
				}
			}
		//Add ELEMENTS sub-panel to tool window
		atomPanel.add(atomSubPanel, BorderLayout.WEST);

		
		//If any HETERO elements exist,
		//Create HETERO sub-panel
		mainPanel.getHeteroList().trimToSize();
		if (mainPanel.getHeteroList().size() > 0) {
			JButton heteroButton;
			atomSubPanel = new JPanel();
			atomSubPanel.setLayout(new BoxLayout(atomSubPanel, BoxLayout.Y_AXIS));
			atomSubPanel.add(new JLabel("Hetero:"));
			atomSubPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			for (int i = 0; i < mainPanel.getHeteroList().size(); i++) {
				heteroButton = new JButton((String) mainPanel.getHeteroList().get(i) + " (" + mainPanel.getHeteroListNum()[i] + ")");
				heteroButton.setSelected(false);
				heteroButton.addActionListener(this);
				heteroButton.setActionCommand("heteroCheck" + i);
				atomSubPanel.add(heteroButton);
			}
			//Add HETERO sub-panel to tool window
			atomPanel.add(atomSubPanel, BorderLayout.CENTER);
		}

		//Create OTHER SETS sub-panel
		atomSubPanel = new JPanel();
		atomSubPanel.setLayout(new BoxLayout(atomSubPanel, BoxLayout.Y_AXIS));
		atomSubPanel.add(new JLabel("Other Sets:"));
		atomSubPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			//Create SIDECHAIN button and add it to OTHER SETS
			atomButton = new JButton("sidechain");
			atomButton.setSelected(false);
			atomButton.addActionListener(this);
			atomButton.setActionCommand("atomCheck" + atomCounter);
			++atomCounter;
			atomSubPanel.add(atomButton);
	
			//Create BACKBONE button and add it to OTHER SETS
			atomButton = new JButton("backbone");
			atomButton.setSelected(false);
			atomButton.addActionListener(this);
			atomButton.setActionCommand("atomCheck" + atomCounter);
			++atomCounter;
			atomSubPanel.add(atomButton);
			
			//Create HETERO button and add it to OTHER SETS
			atomButton = new JButton("hetero");
			atomButton.setSelected(false);
			atomButton.addActionListener(this);
			atomButton.setActionCommand("atomCheck" + atomCounter);
			++atomCounter;
			atomSubPanel.add(atomButton);
	
			//Create LIGAND button and add it to OTHER SETS
			atomButton = new JButton("ligand");
			atomButton.setSelected(false);
			atomButton.addActionListener(this);
			atomButton.setActionCommand("atomCheck" + atomCounter);
			++atomCounter;
			atomSubPanel.add(atomButton);
	
			//Create IONS button and add it to OTHER SETS
			atomButton = new JButton("ions");
			atomButton.setSelected(false);
			atomButton.addActionListener(this);
			atomButton.setActionCommand("atomCheck" + atomCounter);
			++atomCounter;
			atomSubPanel.add(atomButton);
	
			//Create WATER button and add it to OTHER SETS
			atomButton = new JButton("water");
			atomButton.setSelected(false);
			atomButton.addActionListener(this);
			atomButton.setActionCommand("atomCheck" + atomCounter);
			++atomCounter;
			atomSubPanel.add(atomButton);
			
			//Create CARBOHYDRATE button and add it to OTHER SETS
			atomButton = new JButton("carbohydrate");
			atomButton.setSelected(false);
			atomButton.addActionListener(this);
			atomButton.setActionCommand("atomCheck" + atomCounter);
			++atomCounter;
			atomSubPanel.add(atomButton);
			
			//Create SPINE button and add it to OTHER SETS
			atomButton = new JButton("spine");
			atomButton.setSelected(false);
			atomButton.addActionListener(this);
			atomButton.setActionCommand("atomCheck" + atomCounter);
			++atomCounter;
			atomSubPanel.add(atomButton);
			
			// Create VISIBLE button and add it to SELECT
			selectVisible = new JButton("visible");
			selectVisible.addActionListener(this);
			atomSubPanel.add(selectVisible);

		//Add OTHER SETS sub-panel to tool window
		atomPanel.add(atomSubPanel, BorderLayout.EAST);
		
		//Add ATOM PANEL to OUTER PANEL
		outerPanel.add(atomPanel);
		
		/////////////////////////////////////////////
		// Former Picking.java buttons merged here //
		/////////////////////////////////////////////
		
		// Create PICKING Panel
		pickingPanel = new JPanel();
		pickingPanel.setLayout(new BorderLayout());
		
		// Create SELECT sub-panel
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
		subPanel.add(new JLabel("Select:"));
		subPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

			// Create ATOM button and add it to SELECT
			selectAtom = new JButton("Atom");
			selectAtom.addActionListener(this);
			selectAtom.setActionCommand("atom");
			subPanel.add(selectAtom);
	
			// Create MOLECULE button and add it to SELECT
			selectMolecule = new JButton("Molecule");
			selectMolecule.addActionListener(this);
			selectMolecule.setActionCommand("molecule");
			subPanel.add(selectMolecule);
	
			// Create ELEMENT button and add it to SELECT
			selectElement = new JButton("Element");
			selectElement.addActionListener(this);
			selectElement.setActionCommand("element");
			subPanel.add(selectElement);
			
			// Create GROUP button and add it to SELECT
			selectGroup = new JButton("Group");
			selectGroup.addActionListener(this);
			selectGroup.setActionCommand("group");
			subPanel.add(selectGroup);
			
			// Create STRUCTURE button and add it to SELECT
			selectStructure = new JButton("Structure");
			selectStructure.addActionListener(this);
			selectStructure.setActionCommand("structure");
			subPanel.add(selectStructure);
	
			// Create CHAIN button and add it to SELECT
			selectChain = new JButton("Chain");
			selectChain.addActionListener(this);
			selectChain.setActionCommand("chain");
			subPanel.add(selectChain);

		// Add SELECT sub-panel to tool window
		pickingPanel.add(subPanel, BorderLayout.WEST);
		subPanel.setPreferredSize(new Dimension(150, 0));

		// Create PICKING sub-panel
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
		subPanel.add(new JLabel("Picking:"));
		subPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

			// Create OFF button and add it to PICKING
			off = new JButton("Off");
			off.addActionListener(this);
			off.setActionCommand("off");
			subPanel.add(off);
	
			// Create MONITOR button and add it to PICKING
			monitor = new JButton("Monitor");
			monitor.addActionListener(this);
			monitor.setActionCommand("monitor");
			subPanel.add(monitor);
	
			// Create CENTER button and add it to PICKING
			center = new JButton("Center");
			center.addActionListener(this);
			center.setActionCommand("center");
			subPanel.add(center);
	
			// Create IDENTITY button and add it to PICKING
			identity = new JButton("Identity");
			identity.addActionListener(this);
			identity.setActionCommand("ident");
			subPanel.add(identity);
	
			// Create LABEL button and add it to PICKING
			label = new JButton("Label");
			label.addActionListener(this);
			label.setActionCommand("label");
			subPanel.add(label);
	
			// Create DISTANCE button and add it to PICKING
			distance = new JButton("Distance");
			distance.addActionListener(this);
			distance.setActionCommand("distance");
			subPanel.add(distance);
	
			// Create ANGLE button and add it to PICKING
			angle = new JButton("Angle");
			angle.addActionListener(this);
			angle.setActionCommand("angle");
			subPanel.add(angle);
	
			// Create TORSION button and add it to PICKING
			torsion = new JButton("Torsion");
			torsion.addActionListener(this);
			torsion.setActionCommand("torsion");
			subPanel.add(torsion);

		// Add PICKING sub-panel to tool window
		pickingPanel.add(subPanel, BorderLayout.CENTER);
		
		
		// Create OTHER sub-panel
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
		subPanel.add(new JLabel("Other:"));
		subPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
			// Create Atom # field and add it to OTHER
			subsubPanel = new JPanel();
			subsubPanel.setLayout(new BoxLayout(subsubPanel, BoxLayout.X_AXIS));
			
			JLabel atomNo = new JLabel("Select Atom # ");
			subsubPanel.add(atomNo);
			
			atomNoField = new JFormattedTextField(numberFormat);
			atomNoField.setValue(0);
			atomNoField.setColumns(4);
			atomNoField.addPropertyChangeListener("value", this);
			atomNoField.setMaximumSize(new Dimension(50, 20));
			subsubPanel.add(atomNoField);
			
			subPanel.add(subsubPanel);
			subsubPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
			
			// Create select within fields and add them to OTHER
			subsubPanel = new JPanel();
			subsubPanel.setLayout(new BoxLayout(subsubPanel, BoxLayout.X_AXIS));
			
			JLabel withinLabel = new JLabel("Select within ");
			subsubPanel.add(withinLabel);
			
			withinField = new JFormattedTextField(numberFormat);
			withinField.setValue(0);
			withinField.setColumns(4);
			withinField.addPropertyChangeListener("value", this);
			withinField.setMaximumSize(new Dimension(150, 20));
			subsubPanel.add(withinField);
			
			subPanel.add(subsubPanel);
			
			subsubPanel = new JPanel();
			subsubPanel.setLayout(new BoxLayout(subsubPanel, BoxLayout.X_AXIS));
			
			JLabel withinLabel2 = new JLabel("of atomno=");
			subsubPanel.add(withinLabel2);
			
			withinField2 = new JFormattedTextField(numberFormat);
			withinField2.setValue(0);
			withinField2.setColumns(4);
			withinField2.addPropertyChangeListener("value", this);
			withinField2.setMaximumSize(new Dimension(150, 20));
			subsubPanel.add(withinField2);
			
			subPanel.add(subsubPanel);
		
		// Add OTHER sub-panel to tool window
		pickingPanel.add(subPanel, BorderLayout.EAST);

		// Create TEXT AREA sub-panel
		JScrollPane infoArea = new JScrollPane();
		AIA = new AtomInfoArea(mainPanel.getViewer(), infoArea, this);;
		mainPanel.getViewer().setJmolCallbackListener(AIA);
		
		
		// Add PICKING PANEL and TEXT AREA to OUTER PANEL
		outerPanel.add(pickingPanel);
		outerPanel.add(AIA.getScrollPane(), BorderLayout.SOUTH);
		//outerPanel.add(infoScroll, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/////////////////////////////////////
		// ACTION HANLDERS FROM ATOMS.JAVA //
		/////////////////////////////////////
		
		for (int i = 0; i < atomCounter; i++) {

			if (("atomCheck" + i).equals(e.getActionCommand())) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				String text = abstractButton.getText();

				int s = text.indexOf(" ");
				if (s != -1)
					text = text.substring(0, s);
				System.out.println(text);
				System.out.println(abstractButton.getModel().isSelected());
				
				if (text == "All") {
					mainPanel.getScriptPanel().getScriptArea().insert("select All" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
					mainPanel.getScriptPanel().runScript("select All;", true);
				} else {
					mainPanel.getScriptPanel().getScriptArea().insert("select " + text + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
					mainPanel.getScriptPanel().runScript("select " + text + ";", true);
				}
			}
		}
		
		for (int i = 0; i < mainPanel.getHeteroList().size(); i++) {
			if (("heteroCheck" + i).equals(e.getActionCommand())) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				String text = abstractButton.getText();
				int s = text.indexOf(" ");
				if (s != -1)text = text.substring(0, s);
				mainPanel.getScriptPanel().getScriptArea().insert("select " + text + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
				mainPanel.getScriptPanel().runScript("select " + text + ";", true);
			}
		}
		
		///////////////////////////////////////
		// ACTION HANDLERS FROM PICKING.JAVA //
		///////////////////////////////////////
		Object source = e.getSource();
		
		//Action handlers for SELECT column
		
		//These statements set the string 'callback' to the appropriate string, which notifyCallback in AtomInfo uses
		//to run the appropriate commands
		if(source == selectAtom){
			AIA.setCallbackString("ATOM");
		} else if(source == selectMolecule){
			AIA.setCallbackString("MOLECULE");
		} else if(source == selectElement){
			AIA.setCallbackString("ELEMENT");
		} else if(source == selectChain){
			AIA.setCallbackString("CHAIN");
		} else if(source == selectGroup){
			AIA.setCallbackString("GROUP");
		} else if(source == selectStructure){
			AIA.setCallbackString("STRUCTURE");
		} else if(source == label){
			AIA.setCallbackString("LABEL");
		} else if(source == distance){
			AIA.setCallbackString("DISTANCE");
		} else if(source == angle){
			AIA.setCallbackString("ANGLE");
		} else if(source == torsion){
			AIA.setCallbackString("TORSION");
		}
		
		//action handler for VISIBLE button
		if(source == selectVisible){
			mainPanel.getScriptPanel().getScriptArea().insert("select visible;\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("select visible;\n", true);
		}
		
		//If source is any button from PICKING column
		if(source == monitor || source == center || source == identity || source == distance || source == angle || source == torsion || source == off){
			mainPanel.getScriptPanel().getScriptArea().insert("set picking " + e.getActionCommand() + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("set picking " + e.getActionCommand() + ";", true);
		}
		
		if (source == monitor){
			AIA.addText("Click two atoms to measure and display the distance between them.\n");
		} else if (source == center){
			AIA.addText("Click an atom about which to center structure position and rotation.\n");
		} else if (source == identity){
			AIA.addText("Click an atom to identify it.\n");
		} else if (source == label){
			AIA.addText("Click an atom to display an atom label.\n");
		} else if (source == distance){
			AIA.addText("Click two atoms to measure the distance between them.\n");
		} else if (source == angle){
			AIA.addText("Click three atoms sequentially to measure the angle among them.\n");
		} else if (source == torsion){
			AIA.addText("Click four atoms sequentially to measure the torsion angle among them.\n");
		} else if(source == selectAtom){
			AIA.addText("Click on atom to select it.\n");
		} else if(source == selectMolecule){
			AIA.addText("Click on a molecule to select it.\n");
		} else if(source == selectElement){
			AIA.addText("Click on an atom to select all atoms of that element.\n");
		} else if(source == selectChain){
			AIA.addText("Click on an atom to select that atom's protein chain.\n");
		} else if(source == selectGroup){
			AIA.addText("Click on an atom to select that atom's group.\n");
		} else if(source == selectVisible){
			//no text given
		} else if(source == selectStructure){
			AIA.addText("Click on an atom to select that atom's structural unit.\n");
		}
	}
	
	//These are used by notifyCallback to execute the proper commands
	protected void selectAtomNo(int n){
		mainPanel.getScriptPanel().getScriptArea().insert("select atomno=" + n + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().runScript("select atomno=" + n + ";",true);
	}
	
	protected void selectMolecule(int n){
		mainPanel.getScriptPanel().getScriptArea().insert("select within(molecule,atomno=" + n + ");\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().runScript("select within(molecule, atomno=" + n + ");",true);
	}
	
	protected void selectElement(int n){
		mainPanel.getScriptPanel().getScriptArea().insert("select within(element,atomno=" + n + ");\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().runScript("select within(element, atomno=" + n + ");",true);
	}
	
	protected void selectChain(int n){
		mainPanel.getScriptPanel().getScriptArea().insert("select within(chain,atomno=" + n + ");\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().runScript("select within(chain, atomno=" + n + ");",true);
	}
	
	
	protected void selectGroup(int n){
		mainPanel.getScriptPanel().getScriptArea().insert("select within(group,atomno=" + n + ");\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().runScript("select within(group, atomno=" + n + ");",true);
	}
	
	protected void selectStructure(int n){
		mainPanel.getScriptPanel().getScriptArea().insert("select within(structure,atomno=" + n + ");\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().runScript("select within(structure, atomno=" + n + ");",true);
	}
	
	protected void selectLabel(int n){
		mainPanel.getScriptPanel().getScriptArea().insert("select atomno=" + n + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().runScript("select atomno=" + n + ";",true);
		mainPanel.getScriptPanel().getScriptArea().insert("label [%[group]]%[sequence]:%[chain].%[atomName] #%[atomNo];\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().runScript("label [%[group]]%[sequence]:%[chain].%[atomName] #%[atomNo];",true);
	}
	
	protected void selectDistance(int x, int y){
		mainPanel.getScriptPanel().getScriptArea().insert("measure " + x + " " + y + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().runScript("measure " + x + " " + y + ";\n",true);
	}
	
	protected void selectAngle(int x, int y, int z){
		mainPanel.getScriptPanel().getScriptArea().insert("measure " + x + " " + y + " " + z + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().runScript("measure " + x + " " + y + " " + z + ";\n",true);
	}
	
	protected void selectTorsion(int x, int y, int z, int n){
		mainPanel.getScriptPanel().getScriptArea().insert("measure " + x + " " + y + " " + z + " " + n + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().runScript("measure " + x + " " + y + " " + z + " " + n + ";\n",true);
	}

	public JPanel getAtomsPanel() {
		return outerPanel;
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		Object source = e.getSource();

		boolean send = true;
		if (source == atomNoField) {
			int num = ((Number) atomNoField.getValue()).intValue();

			if (num < 0) {
				atomNoField.setValue(0);
				num = 0;
				send = false;
			}
			if (send) {
				mainPanel.getScriptPanel().getScriptArea().insert("select atomno=" + num + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
				mainPanel.getScriptPanel().runScript("select atomno=" + num + ";",
						true);
			}
		} else if (source == withinField2) {
			int range = ((Number) withinField.getValue()).intValue();
			int atomNo = ((Number) withinField2.getValue()).intValue();

			if (range < 0) {
				withinField.setValue(0);
				range = 0;
				send = false;
			}
			if (atomNo < 0) {
				withinField2.setValue(0);
				atomNo = 0;
				send = false;
			}
			if (send) {
				mainPanel.getScriptPanel().getScriptArea().insert("select within(" + range + ",atomno=" + atomNo + ");\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
				mainPanel.getScriptPanel().runScript("select(" + range + ",atomno=" + atomNo + ");",
						true);
			}
		}
		
	}
	  
	@Override
	public boolean notifyEnabled(EnumCallback arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCallbackFunction(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyCallback(EnumCallback arg0, Object[] arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
