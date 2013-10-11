import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Atoms implements ActionListener {

	private MainPanel mainPanel;
	private HTMLBuilder builder;
	private int id;
	private JPanel atomPanel;
	private int atomCounter;

	public Atoms(HTMLBuilder builder, MainPanel mainPanel, int id) {

		this.mainPanel = mainPanel;
		this.id = id;
		this.builder = builder;
		atomPanel = new JPanel();
		atomPanel.setLayout(new BorderLayout());

		//Create ELEMENTS sub-panel
		JPanel atomSubPanel = new JPanel();
		atomSubPanel.setLayout(new BoxLayout(atomSubPanel, BoxLayout.Y_AXIS));
		atomSubPanel.add(new JLabel("Elements:"));

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

		//Add OTHER SETS sub-panel to tool window
		atomPanel.add(atomSubPanel, BorderLayout.EAST);
	}

	public void actionPerformed(ActionEvent e) {
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
					mainPanel.getScriptPanel().runScript("select All;", false);
				} else {
					mainPanel.getScriptPanel().getScriptArea().insert("select " + text + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
					mainPanel.getScriptPanel().runScript("select " + text + ";", false);
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
				mainPanel.getScriptPanel().runScript("select " + text + ";", false);
			}
		}
	}

	public JPanel getAtomsPanel() {
		return atomPanel;
	}
}
