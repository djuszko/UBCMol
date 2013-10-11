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

public class Picking implements ActionListener {

	private HTMLBuilder builder;
	private int id;
	private MainPanel mainPanel;

	private JPanel panel;
	private JScrollPane infoScroll;

	private JButton selectAtom, selectMolecule, selectElement, selectChain,	selectGroup, off, center, identity, label, distance, angle,	torsion, monitor;
	private JTextArea atomInfoArea;

	public Picking(HTMLBuilder builder, MainPanel mainPanel, int id) {

		this.builder = builder;
		this.id = id;
		this.mainPanel = mainPanel;

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Create SELECT sub-panel
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
		subPanel.add(new JLabel("Select:"));

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

		// Create CHAIN button and add it to SELECT
		selectChain = new JButton("Chain");
		selectChain.addActionListener(this);
		selectChain.setActionCommand("chain");
		subPanel.add(selectChain);

		// Create GROUP button and add it to SELECT
		selectGroup = new JButton("Group");
		selectGroup.addActionListener(this);
		selectGroup.setActionCommand("group");
		subPanel.add(selectGroup);

		// Add SELECT sub-panel to tool window
		panel.add(subPanel, BorderLayout.WEST);

		// Create PICKING sub-panel
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
		subPanel.add(new JLabel("Picking:"));

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
		center = new JButton("Center (1)");
		center.addActionListener(this);
		center.setActionCommand("center");
		subPanel.add(center);

		// Create IDENTITY button and add it to PICKING
		identity = new JButton("Identity (1)");
		identity.addActionListener(this);
		identity.setActionCommand("ident");
		subPanel.add(identity);

		// Create LABEL button and add it to PICKING
		label = new JButton("Label (1)");
		label.addActionListener(this);
		label.setActionCommand("label");
		subPanel.add(label);

		// Create DISTANCE button and add it to PICKING
		distance = new JButton("Distance (2)");
		distance.addActionListener(this);
		distance.setActionCommand("distance");
		subPanel.add(distance);

		// Create ANGLE button and add it to PICKING
		angle = new JButton("Angle (3)");
		angle.addActionListener(this);
		angle.setActionCommand("angle");
		subPanel.add(angle);

		// Create TORSION button and add it to PICKING
		torsion = new JButton("Torsion (4)");
		torsion.addActionListener(this);
		torsion.setActionCommand("torsion");
		subPanel.add(torsion);

		// Add PICKING sub-panel to tool window
		panel.add(subPanel, BorderLayout.CENTER);

		Border blackline = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Label");

		// Create TEXT AREA sub-panel
		subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));

		atomInfoArea = new JTextArea();
		atomInfoArea.setLineWrap(true);
		atomInfoArea.setEnabled(true);

		infoScroll = new JScrollPane(atomInfoArea);
		infoScroll.setMaximumSize(new Dimension(400, 200));
		infoScroll.setMinimumSize(new Dimension(400, 200));
		infoScroll.setPreferredSize(new Dimension(400, 200));

		// infoScroll.setMaximumSize(new Dimension(400,200));
		// subPanel.add(infoScroll);
		// subPanel.setBorder(blackline);
		// subPanel.setMaximumSize(new Dimension(400,200));
		panel.add(infoScroll, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == monitor){
			atomInfoArea.append("Select two atoms to measure and display the distance between them.\n");
		} else if (source == center){
			atomInfoArea.append("Select an atom about which to center structure position and rotation.\n");
		} else if (source == identity){
			atomInfoArea.append("Select an atom to identify it.\n");
		} else if (source == label){
			atomInfoArea.append("Select an atom to display an atom label.\n");
		} else if (source == distance){
			atomInfoArea.append("Select two atoms to measure the distance between them.\n");
		} else if (source == angle){
			atomInfoArea.append("Select three atoms sequentially to measure the angle among them.\n");
		} else if (source == torsion){
			atomInfoArea.append("Select four atoms sequentially to measure the torsion angle among them.\n");
		} else {
			atomInfoArea.append(e.getActionCommand() + ":\n");
		}
		
		
		//atomInfoArea.append(e.getActionCommand() + ":\n");
		mainPanel.getScriptPanel().runScript("set picking " + e.getActionCommand() + ";", true);
		
	}

	public JPanel getPickingPanel() {
		return panel;
	}

	public void setAtomInfoText(String s) {
		atomInfoArea.append(s + "\n");
		// selectButton.setEnabled(true);
		// centreButton.setEnabled(true);
	}
}
