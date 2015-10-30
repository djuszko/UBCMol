/////////////////////////////////////////////////////////////////////////////////////////////
//   This is the VISUAL OPTIONS tab                                                        //
/////////////////////////////////////////////////////////////////////////////////////////////
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;

import javax.swing.JFormattedTextField;

import javax.swing.JPanel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Render implements ActionListener, PropertyChangeListener, ChangeListener {

	private HTMLBuilder builder;
	private int id;
	private MainPanel mainPanel;
	private JPanel jMolOptions, colorPanel, rasmolPanel, outerPanel, outerColor;
	private String command, checkBoxCommand ;

	private JButton wireframeButtonOn, wireframeButtonOff, spacefillButtonOn, spacefillButtonOff, backboneButtonOn, backboneButtonOff,
	ribbonsButtonOn, ribbonsButtonOff, strandsButtonOn, strandsButtonOff, cartoonsButtonOn, cartoonsButtonOff, hbondsButtonOn, hbondsButtonOff,
	ssbondsButtonOn, ssbondsButtonOff, meshRibbonButtonOn, meshRibbonButtonOff, traceButtonOn, traceButtonOff, rocketsButtonOn, rocketsButtonOff,
	cpkButton, aminoButton, shapelyButton, groupButton, structureButton, chainButton, chargeButton, wireframeButton, spacefillButton, backboneButton,
	strandsWidthButton, strandsNumberButton, cartoonsButton, hbondsButton, ssbondsButton, rocketsButton, traceButton, meshRibbonButton, ribbonsButton;

	private JFormattedTextField wireframeField, spacefillField, backboneField,
	ribbonsField, strandsWidthField, strandsNumberField, cartoonsField, hbondsField, traceField, rocketsField,
	ssbondsField, meshRibbonField, zoomField;
	private NumberFormat numberFormat = NumberFormat.getNumberInstance();
	
	private JCheckBox strandsCheckBox, ribbonsCheckBox, cartoonsCheckBox, hbondsCheckBox, ssbondsCheckBox, backgroundCheckBox, labelsCheckBox;
	
	private JColorChooser colorChooser;

	public Render(HTMLBuilder builder, MainPanel mainPanel, int id) {

		this.builder = builder;
		this.id = id;
		this.mainPanel = mainPanel;
		this.command = command;
		command = "color";
		
		//Create outer panel		
		outerPanel = new JPanel();
		outerPanel.setLayout(new BorderLayout());
		
		//Create CENTER panel
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());

		//Create RENDER Panel
		JPanel renderPanel = new JPanel();
		renderPanel.setLayout(new BoxLayout(renderPanel, BoxLayout.Y_AXIS));
		renderPanel.add(new JLabel("Render Options:"));
		renderPanel.setPreferredSize(new Dimension(305, 0));
		renderPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		/*
		 * Wireframe section
		 * Refers to the bonds drawn between the atoms. A boolean value of ON draws the selected bonds as lines.
		 */
		JPanel renderSubPanel = new JPanel();
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));

		renderSubPanel.add(new JLabel("Wireframe")).setPreferredSize(new Dimension(71,0));
		
		wireframeButtonOn = new JButton("ON");
		wireframeButtonOn.addActionListener(this);
		wireframeButtonOn.setActionCommand("wireframeOn");
		renderSubPanel.add(wireframeButtonOn);
		
		wireframeButtonOff = new JButton("OFF");
		wireframeButtonOff.addActionListener(this);
		wireframeButtonOff.setActionCommand("wireframeOff");
		renderSubPanel.add(wireframeButtonOff);

		wireframeField = new JFormattedTextField(numberFormat);
		wireframeField.setValue(50);
		wireframeField.setColumns(4);
		wireframeField.addPropertyChangeListener("value", this);
		
		wireframeButton = new JButton("Value:");
		wireframeButton.addActionListener(this);
		wireframeButton.setActionCommand("wireframeButton");
		renderSubPanel.add(wireframeButton);

		wireframeField.setMaximumSize(new Dimension(100, 20));
		renderSubPanel.add(wireframeField);
		renderPanel.add(renderSubPanel);

		/*
		 * Spacefill section
		 * Renders selected atoms as shaded spheres.
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));
		
		renderSubPanel.add(new JLabel("Spacefill")).setPreferredSize(new Dimension(74,0));
		
		spacefillButtonOn = new JButton("ON");
		spacefillButtonOn.addActionListener(this);
		spacefillButtonOn.setActionCommand("spacefillButton");
		renderSubPanel.add(spacefillButtonOn);
		
		spacefillButtonOff = new JButton("OFF");
		spacefillButtonOff.addActionListener(this);
		spacefillButtonOff.setActionCommand("spacefillOff");
		renderSubPanel.add(spacefillButtonOff);

		spacefillField = new JFormattedTextField(numberFormat);
		spacefillField.setValue(300);
		spacefillField.setColumns(4);
		spacefillField.addPropertyChangeListener("value", this);
		spacefillField.setMaximumSize(new Dimension(100, 20));
		
		spacefillButton = new JButton("Value:");
		spacefillButton.addActionListener(this);
		spacefillButton.setActionCommand("spacefillButton");
		renderSubPanel.add(spacefillButton);

		renderSubPanel.add(spacefillField);
		renderPanel.add(renderSubPanel);


		/*
		 * Backbone section
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));
		
		renderSubPanel.add(new JLabel("Backbone")).setPreferredSize(new Dimension(72,0));
		
		backboneButtonOn = new JButton("ON");
		backboneButtonOn.addActionListener(this);
		backboneButtonOn.setActionCommand("backboneButton");
		renderSubPanel.add(backboneButtonOn);
		
		backboneButtonOff = new JButton("OFF");
		backboneButtonOff.addActionListener(this);
		backboneButtonOff.setActionCommand("backboneOff");
		renderSubPanel.add(backboneButtonOff);

		backboneField = new JFormattedTextField(numberFormat);
		backboneField.setValue(50);
		backboneField.setColumns(4);
		backboneField.addPropertyChangeListener("value", this);
		
		backboneButton = new JButton("Value:");
		backboneButton.addActionListener(this);
		backboneButton.setActionCommand("backboneButton");
		renderSubPanel.add(backboneButton);

		backboneField.setMaximumSize(new Dimension(100, 20));
		renderSubPanel.add(backboneField);
		renderPanel.add(renderSubPanel);
		
		/*
		 *  Trace section
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));

		renderSubPanel.add(new JLabel("Trace")).setPreferredSize(new Dimension(76,0));
		
		traceButtonOn = new JButton("ON");
		traceButtonOn.addActionListener(this);
		traceButtonOn.setActionCommand("traceButton");
		renderSubPanel.add(traceButtonOn);
		
		traceButtonOff = new JButton("OFF");
		traceButtonOff.addActionListener(this);
		traceButtonOff.setActionCommand("traceOff");
		renderSubPanel.add(traceButtonOff);

		traceField = new JFormattedTextField(numberFormat);
		traceField.setValue(100);
		traceField.setColumns(4);
		traceField.addPropertyChangeListener("value", this);
		traceField.setMaximumSize(new Dimension(120, 20));
		
		traceButton = new JButton("Value:");
		traceButton.addActionListener(this);
		traceButton.setActionCommand("traceButton");
		renderSubPanel.add(traceButton);

		renderSubPanel.add(traceField);
		renderPanel.add(renderSubPanel);
		
		/*
		 * hbonds section
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));

		renderSubPanel.add(new JLabel("hbonds")).setPreferredSize(new Dimension(75,0));
		
		hbondsButtonOn = new JButton("ON");
		hbondsButtonOn.addActionListener(this);
		hbondsButtonOn.setActionCommand("hbondsButton");
		renderSubPanel.add(hbondsButtonOn);
		
		hbondsButtonOff = new JButton("OFF");
		hbondsButtonOff.addActionListener(this);
		hbondsButtonOff.setActionCommand("hbondsOff");
		renderSubPanel.add(hbondsButtonOff);

		hbondsField = new JFormattedTextField(numberFormat);
		hbondsField.setValue(20);
		hbondsField.setColumns(4);
		hbondsField.addPropertyChangeListener("value", this);
		
		hbondsButton = new JButton("Value:");
		hbondsButton.addActionListener(this);
		hbondsButton.setActionCommand("hbondsButton");
		renderSubPanel.add(hbondsButton);

		hbondsField.setMaximumSize(new Dimension(100, 20));
		renderSubPanel.add(hbondsField);
		renderPanel.add(renderSubPanel);
		
		/*
		 * ssbonds section
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));
		
		renderSubPanel.add(new JLabel("ssbonds")).setPreferredSize(new Dimension(74,0));
		
		ssbondsButtonOn = new JButton("ON");
		ssbondsButtonOn.addActionListener(this);
		ssbondsButtonOn.setActionCommand("ssbondsButton");
		renderSubPanel.add(ssbondsButtonOn);
		
		ssbondsButtonOff = new JButton("OFF");
		ssbondsButtonOff.addActionListener(this);
		ssbondsButtonOff.setActionCommand("ssbondsOff");
		renderSubPanel.add(ssbondsButtonOff);

		ssbondsField = new JFormattedTextField(numberFormat);
		ssbondsField.setValue(50);
		ssbondsField.setColumns(4);
		ssbondsField.addPropertyChangeListener("value", this);
		
		ssbondsButton = new JButton("Value:");
		ssbondsButton.addActionListener(this);
		ssbondsButton.setActionCommand("ssbondsButton");
		renderSubPanel.add(ssbondsButton);

		ssbondsField.setMaximumSize(new Dimension(100, 20));
		renderSubPanel.add(ssbondsField);
		renderPanel.add(renderSubPanel);

		/*
		 * ribbons section
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));
		
		renderSubPanel.add(new JLabel("Ribbons")).setPreferredSize(new Dimension(75,0));
		
		ribbonsButtonOn = new JButton("ON");
		ribbonsButtonOn.addActionListener(this);
		ribbonsButtonOn.setActionCommand("ribbonsButton");
		renderSubPanel.add(ribbonsButtonOn);
		
		ribbonsButtonOff = new JButton("OFF");
		ribbonsButtonOff.addActionListener(this);
		ribbonsButtonOff.setActionCommand("ribbonsOff");
		renderSubPanel.add(ribbonsButtonOff);

		ribbonsField = new JFormattedTextField(numberFormat);
		ribbonsField.setValue(300);
		ribbonsField.setColumns(4);
		ribbonsField.addPropertyChangeListener("value", this);
		
		ribbonsButton = new JButton("Value:");
		ribbonsButton.addActionListener(this);
		ribbonsButton.setActionCommand("ribbonsButton");
		renderSubPanel.add(ribbonsButton);
		
		ribbonsField.setMaximumSize(new Dimension(100, 20));
		renderSubPanel.add(ribbonsField);
		renderPanel.add(renderSubPanel);

		/*
		 * cartoons section
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));

		renderSubPanel.add(new JLabel("Cartoons")).setPreferredSize(new Dimension(73,0));
		
		cartoonsButtonOn = new JButton("ON");
		cartoonsButtonOn.addActionListener(this);
		cartoonsButtonOn.setActionCommand("cartoonsButton");
		renderSubPanel.add(cartoonsButtonOn);
		
		cartoonsButtonOff = new JButton("OFF");
		cartoonsButtonOff.addActionListener(this);
		cartoonsButtonOff.setActionCommand("cartoonsOff");
		renderSubPanel.add(cartoonsButtonOff);

		cartoonsField = new JFormattedTextField(numberFormat);
		cartoonsField.setValue(300);
		cartoonsField.setColumns(4);
		cartoonsField.addPropertyChangeListener("value", this);
		
		cartoonsButton = new JButton("Value:");
		cartoonsButton.addActionListener(this);
		cartoonsButton.setActionCommand("cartoonsButton");
		renderSubPanel.add(cartoonsButton);

		cartoonsField.setMaximumSize(new Dimension(100, 20));
		renderSubPanel.add(cartoonsField);
		renderPanel.add(renderSubPanel);
		
		/*
		 *  Meshribbon section
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));
		
		renderSubPanel.add(new JLabel("Meshribbon")).setPreferredSize(new Dimension(70,0));
		
		meshRibbonButtonOn = new JButton("ON");
		meshRibbonButtonOn.addActionListener(this);
		meshRibbonButtonOn.setActionCommand("meshRibbonButton");
		renderSubPanel.add(meshRibbonButtonOn);
		
		meshRibbonButtonOff = new JButton("OFF");
		meshRibbonButtonOff.addActionListener(this);
		meshRibbonButtonOff.setActionCommand("meshRibbonOff");
		renderSubPanel.add(meshRibbonButtonOff);

		meshRibbonField = new JFormattedTextField(numberFormat);
		meshRibbonField.setValue(300);
		meshRibbonField.setColumns(4);
		meshRibbonField.addPropertyChangeListener("value", this);
		meshRibbonField.setMaximumSize(new Dimension(100, 20));
		
		meshRibbonButton = new JButton("Value:");
		meshRibbonButton.addActionListener(this);
		meshRibbonButton.setActionCommand("meshRibbonButton");
		renderSubPanel.add(meshRibbonButton);

		renderSubPanel.add(meshRibbonField);
		renderPanel.add(renderSubPanel);
		
		/*
		 *  Rockets section
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));

		renderSubPanel.add(new JLabel("Rockets")).setPreferredSize(new Dimension(75,0));
		
		rocketsButtonOn = new JButton("ON");
		rocketsButtonOn.addActionListener(this);
		rocketsButtonOn.setActionCommand("rocketsButton");
		renderSubPanel.add(rocketsButtonOn);
		
		rocketsButtonOff = new JButton("OFF");
		rocketsButtonOff.addActionListener(this);
		rocketsButtonOff.setActionCommand("rocketsOff");
		renderSubPanel.add(rocketsButtonOff);

		rocketsField = new JFormattedTextField(numberFormat);
		rocketsField.setValue(300);
		rocketsField.setColumns(4);
		rocketsField.addPropertyChangeListener("value", this);
		rocketsField.setMaximumSize(new Dimension(100, 20));
		
		rocketsButton = new JButton("Value:");
		rocketsButton.addActionListener(this);
		rocketsButton.setActionCommand("rocketsButton");
		renderSubPanel.add(rocketsButton);

		renderSubPanel.add(rocketsField);
		renderPanel.add(renderSubPanel);
		
		/*
		 * strands section
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));

		renderSubPanel.add(new JLabel("Strands        ")).setPreferredSize(new Dimension(300,0));
		
		strandsButtonOn = new JButton("ON");
		strandsButtonOn.addActionListener(this);
		strandsButtonOn.setActionCommand("strandsButton");
		renderSubPanel.add(strandsButtonOn);
		
		strandsButtonOff = new JButton("OFF");
		strandsButtonOff.addActionListener(this);
		strandsButtonOff.setActionCommand("ribbonOff");
		renderSubPanel.add(strandsButtonOff);
		
		renderSubPanel.add(new JLabel("                                             ")).setPreferredSize(new Dimension(300,0));
		
		renderPanel.add(renderSubPanel);
		
		//strands width
		renderSubPanel = new JPanel();
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));
		
		renderSubPanel.add(new JLabel("Strands Width:")).setPreferredSize(new Dimension(196,0));
		
		strandsWidthButton = new JButton("Value:");
		strandsWidthButton.addActionListener(this);
		strandsWidthButton.setActionCommand("strandsWidthButton");
		renderSubPanel.add(strandsWidthButton);
		
		strandsWidthField = new JFormattedTextField(numberFormat);
		strandsWidthField.setValue(300);
		strandsWidthField.setColumns(4);
		strandsWidthField.addPropertyChangeListener("value", this);
		strandsWidthField.setMaximumSize(new Dimension(130, 20));
		renderSubPanel.add(strandsWidthField);
		
		renderPanel.add(renderSubPanel);
		
		//strands number
		renderSubPanel = new JPanel();
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));
		
		renderSubPanel.add(new JLabel("Strands Number:")).setPreferredSize(new Dimension(190,0));
		
		strandsNumberButton = new JButton("Value:");
		strandsNumberButton.addActionListener(this);
		strandsNumberButton.setActionCommand("strandsNumberButton");
		renderSubPanel.add(strandsNumberButton);
		
		strandsNumberField = new JFormattedTextField(numberFormat);
		strandsNumberField.setValue(4);
		strandsNumberField.setColumns(4);
		strandsNumberField.addPropertyChangeListener("value", this);
		strandsNumberField.setMaximumSize(new Dimension(130, 20));
		renderSubPanel.add(strandsNumberField);
		
		renderPanel.add(renderSubPanel);
		
		//Add RENDER PANEL to CENTER PANEL
		centerPanel.add(renderPanel, BorderLayout.WEST);

		//////////////////////////////////////////////////
		// Former RasmolColors.java buttons merged here //
		//////////////////////////////////////////////////
		
		//Create RASMOL sub-panel and give it a border
		rasmolPanel = new JPanel();
		rasmolPanel.setLayout(new BoxLayout(rasmolPanel, BoxLayout.Y_AXIS));
		rasmolPanel.add(new JLabel("Color Schemes:"));
		rasmolPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//Create CPK button and add it to CHOICE
		cpkButton = new JButton("CPK");
		cpkButton.setSelected(false);
		cpkButton.addActionListener(this);		
		cpkButton.setActionCommand("cpk");

		rasmolPanel.add(cpkButton);

		//Create AMINO button and add it to CHOICE
		aminoButton = new JButton("Amino");
		aminoButton.setSelected(false);
		aminoButton.addActionListener(this);		
		aminoButton.setActionCommand("amino");

		rasmolPanel.add(aminoButton);
		
		//Create SHAPELY button and add it to CHOICE
		shapelyButton = new JButton("Shapely");
		shapelyButton.setSelected(false);
		shapelyButton.addActionListener(this);		
		shapelyButton.setActionCommand("shapely");

		rasmolPanel.add(shapelyButton);

		//Create GROUP button and add it to CHOICE
		groupButton = new JButton("Group");
		groupButton.setSelected(false);
		groupButton.addActionListener(this);		
		groupButton.setActionCommand("group");

		rasmolPanel.add(groupButton);

		//Create STRUCTURE button and add it to CHOICE
		structureButton = new JButton("Structure");
		structureButton.setSelected(false);
		structureButton.addActionListener(this);		
		structureButton.setActionCommand("structure");

		rasmolPanel.add(structureButton);

		//Create CHAIN button and add it to CHOICE
		chainButton = new JButton("Chain");
		chainButton.setSelected(false);
		chainButton.addActionListener(this);		
		chainButton.setActionCommand("chain");

		rasmolPanel.add(chainButton);

		//Create CHARGE button and add it to CHOICE
		chargeButton = new JButton("Charge");
		chargeButton.setSelected(false);
		chargeButton.addActionListener(this);
		chargeButton.setActionCommand("formalCharge");

		rasmolPanel.add(chargeButton);
		
		//Add RASMOL PANEL to CENTER PANEL
		centerPanel.add(rasmolPanel, BorderLayout.CENTER);
		
		//Add CENTER PANEL to CENTER of OUTER PANEL
		outerPanel.add(centerPanel, BorderLayout.CENTER);
		
		//////////////////////////////////////////////////
		// Former JmolColor.java buttons merged here 	//
		//////////////////////////////////////////////////
		
		//Create OUTER COLOR PANEL
		outerColor = new JPanel(); 
		//colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
		
		//Create COLOR CHOOSER
		colorChooser = new JColorChooser();
		AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
		colorChooser.removeChooserPanel(panels[4]); 
		colorChooser.removeChooserPanel(panels[3]); 
		colorChooser.removeChooserPanel(panels[2]); 
		colorChooser.removeChooserPanel(panels[1]); 
		colorChooser.setPreviewPanel(new JPanel());
		colorChooser.getSelectionModel().addChangeListener(this); 
		colorChooser.setPreferredSize(new Dimension(425,170));
		colorChooser.setMinimumSize(new Dimension(0,0));
		outerColor.add(colorChooser);

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
		
		outerColor.add(colorPanel);
		outerPanel.add(outerColor, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		///////////////////////////////////
		// RENDER BUTTON ON/OFF HANDLERS //
		///////////////////////////////////
		if (source == wireframeButtonOn) {
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("wireframe on" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("wireframe on" + ";", true);
		} else if (source == wireframeButtonOff){
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("wireframe off" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("wireframe off" + ";", true);
		} if (source == spacefillButtonOn) {
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("spacefill on" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("spacefill on" + ";", true);
		} else if (source == spacefillButtonOff){
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("spacefill off" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("spacefill off" + ";", true);
		} if (source == backboneButtonOn) {
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("backbone on" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("backbone on" + ";", true);
		} else if (source == backboneButtonOff){
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("backbone off" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("backbone off" + ";", true);
		} if (source == ribbonsButtonOn) {
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("ribbons on" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("ribbons on" + ";", true);
			setRibbonsEnabled(true);
		} else if (source == ribbonsButtonOff){
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("ribbons off" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("ribbons off" + ";", true);
			ribbonsCheckBox.setSelected(false);
			setRibbonsEnabled(false);
		} if (source == strandsButtonOn) {
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("strands on" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("strands on" + ";", true);
			setStrandsEnabled(true);
		} else if (source == strandsButtonOff){
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("strands off" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("strands off" + ";", true);
			strandsCheckBox.setSelected(false);
			setStrandsEnabled(false);
		} if (source == cartoonsButtonOn) {
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("cartoons on" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("cartoons on" + ";", true);
			setCartoonsEnabled(true);
		} else if (source == cartoonsButtonOff){
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("cartoons off" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("cartoons off" + ";", true);
			cartoonsCheckBox.setSelected(false);
			setCartoonsEnabled(false);
		} if (source == hbondsButtonOn) {
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("hbonds calculate" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("hbonds calculate" + ";", true);
			setHBondsEnabled(true);
		} else if (source == hbondsButtonOff){
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("hbonds off" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("hbonds off" + ";", true);
			hbondsCheckBox.setSelected(false);
			setHBondsEnabled(false);
		} if (source == ssbondsButtonOn) {
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("ssbonds on" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("ssbonds on" + ";", true);
			setSSBondsEnabled(true);
		} else if (source == ssbondsButtonOff){
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("ssbonds off" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("ssbonds off" + ";", true);
			ssbondsCheckBox.setSelected(false);
			setSSBondsEnabled(false);
		} if (source == meshRibbonButtonOn) {
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("meshribbon on" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("meshribbon on" + ";", true);
		} else if (source == meshRibbonButtonOff){
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("meshribbon off" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("meshribbon off" + ";", true);
		} if (source == traceButtonOn) {
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("trace on" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("trace on" + ";", true);
		} else if (source == traceButtonOff){
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("trace off" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("trace off" + ";", true);
		} if (source == rocketsButtonOn) {
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("rockets on" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("rockets on" + ";", true);
		} else if (source == rocketsButtonOff){
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("rockets off" + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel()
			.runScript("rockets off" + ";", true);
		} else if (source == wireframeButton) {
			int num = ((Number) wireframeField.getValue()).intValue();
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("wireframe " + num + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel().runScript("wireframe " + num + ";",
					true);
		} else if (source == spacefillButton) {
			int num = ((Number) spacefillField.getValue()).intValue();
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("spacefill " + num + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel().runScript("spacefill " + num + ";",
					true);
		} else if (source == backboneButton) {
			int num = ((Number) backboneField.getValue()).intValue();
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("backbone " + num + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel().runScript("backbone " + num + ";",
					true);
		} else if (source == ribbonsButton) {
			int num = ((Number) ribbonsField.getValue()).intValue();
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("ribbons " + num + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel().runScript("ribbons " + num + ";",
					true);
		} else if (source == strandsWidthButton) {
			int num = ((Number) strandsWidthField.getValue()).intValue();
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("strands " + num + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel().runScript("strands " + num + ";",
					true);
		} else if (source == strandsNumberButton) {
			int num = ((Number) strandsNumberField.getValue()).intValue();
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("set strands " + num + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel().runScript("strands " + num + ";",
					true);
		} else if (source == cartoonsButton) {
			int num = ((Number) cartoonsField.getValue()).intValue();
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("cartoons " + num + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel().runScript("cartoons " + num + ";",
					true);
		} else if (source == hbondsButton) {
			int num = ((Number) hbondsField.getValue()).intValue();
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("hbonds " + num + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel().runScript("hbonds " + num + ";",
					true);
		} else if (source == ssbondsButton) {
			int num = ((Number) ssbondsField.getValue()).intValue();
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("ssbonds " + num + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel().runScript("ssbonds " + num + ";",
					true);
		} else if (source == meshRibbonButton) {
			int num = ((Number) meshRibbonField.getValue()).intValue();
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("meshRibbon " + num + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel().runScript("meshRibbon " + num + ";",
					true);
		} else if (source == traceButton) {
			int num = ((Number) traceField.getValue()).intValue();
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("trace " + num + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel().runScript("trace " + num + ";",
					true);
		} else if (source == rocketsButton) {
			int num = ((Number) rocketsField.getValue()).intValue();
			mainPanel
			.getScriptPanel()
			.getScriptArea()
			.insert("rockets " + num + ";\n",
					mainPanel.getScriptPanel().getScriptArea()
					.getCaretPosition());
			mainPanel.getScriptPanel().runScript("rockets " + num + ";",
					true);
		}
		
		////////////////////////////
		// RASMOL BUTTON HANDLERS //
		////////////////////////////
		
		if(e.getActionCommand().equals("cpk") && hbondsCheckBox.isSelected()){
			mainPanel.getScriptPanel().getScriptArea().insert("color hbonds cpk;\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color hbonds cpk;", true);
		} else if (ribbonsCheckBox.isSelected() && (e.getActionCommand().equals("cpk") ||
				e.getActionCommand().equals("amino") ||
				e.getActionCommand().equals("shapely") ||
				e.getActionCommand().equals("group") ||
				e.getActionCommand().equals("structure") ||
				e.getActionCommand().equals("chain") ||
				e.getActionCommand().equals("formalCharge"))){
			mainPanel.getScriptPanel().getScriptArea().insert("color ribbons "+e.getActionCommand()+";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color ribbons "+e.getActionCommand()+";\n", true);
		} else if (cartoonsCheckBox.isSelected() && (e.getActionCommand().equals("cpk") ||
				e.getActionCommand().equals("amino") ||
				e.getActionCommand().equals("shapely") ||
				e.getActionCommand().equals("group") ||
				e.getActionCommand().equals("structure") ||
				e.getActionCommand().equals("chain") ||
				e.getActionCommand().equals("formalCharge"))){
			mainPanel.getScriptPanel().getScriptArea().insert("color cartoons "+e.getActionCommand()+";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color cartoons "+e.getActionCommand()+";\n", true);
		} else if (strandsCheckBox.isSelected() && (e.getActionCommand().equals("cpk") ||
				e.getActionCommand().equals("amino") ||
				e.getActionCommand().equals("shapely") ||
				e.getActionCommand().equals("group") ||
				e.getActionCommand().equals("structure") ||
				e.getActionCommand().equals("chain") ||
				e.getActionCommand().equals("formalCharge"))){
			mainPanel.getScriptPanel().getScriptArea().insert("color strands "+e.getActionCommand()+";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color strands "+e.getActionCommand()+";\n", true);
		} else if(e.getActionCommand().equals("cpk") ||
				e.getActionCommand().equals("amino") ||
				e.getActionCommand().equals("shapely") ||
				e.getActionCommand().equals("group") ||
				e.getActionCommand().equals("structure") ||
				e.getActionCommand().equals("chain") ||
				e.getActionCommand().equals("formalCharge")){

			mainPanel.getScriptPanel().getScriptArea().insert("color "+e.getActionCommand()+";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color "+e.getActionCommand()+";", true);
		}
		
		/////////////////////////
		// JMOL COLOR HANDLERS //
		/////////////////////////	
		
		System.out.println(colorPanel.getComponentCount());
		System.out.println(e.getActionCommand());
	}

	public void propertyChange(PropertyChangeEvent e) {

		Object source = e.getSource();

		boolean send = true;
		if (source == wireframeField) {
			int num = ((Number) wireframeField.getValue()).intValue();

			if (num < 0) {
				wireframeField.setValue(0);
				num = 0;
				send = false;
			} else if (num > 750) {
				wireframeField.setValue(750);
				num = 750;
				send = false;
			}
			if (send) {
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("wireframe " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("wireframe " + num + ";",
						true);
			}
		} 
		else if (source == spacefillField) {
			int num = ((Number) spacefillField.getValue()).intValue();

			if (num < 0) {
				spacefillField.setValue(0);
				num = 0;
				send = false;
			} else if (num > 749) {
				spacefillField.setValue(749);
				num = 749;
				send = false;
			}
			if (send) {
				// mainPanel.getScriptPanel().getScriptArea().append("spacefill "+num+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("spacefill " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("spacefill " + num + ";",
						true);
			}

		} 
		else if (source == backboneField) {

			int num = ((Number) backboneField.getValue()).intValue();

			if (num < 0) {
				backboneField.setValue(0);
				num = 0;
				send = false;
			} else if (num > 499) {
				backboneField.setValue(499);
				num = 499;
				send = false;
			}
			if (send) {
				// mainPanel.getScriptPanel().getScriptArea().append("backbone "+num+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("backbone " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("backbone " + num + ";",
						true);
			}

		} 
		else if (source == ribbonsField) {

			int num = ((Number) ribbonsField.getValue()).intValue();

			if (num < 0) {
				ribbonsField.setValue(0);
				num = 0;
				send = false;
			} else if (num > 499) {
				ribbonsField.setValue(499);
				num = 499;
				send = false;
			}
			if (send) {
				// mainPanel.getScriptPanel().getScriptArea().append("ribbons "+num+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("ribbons " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("ribbons " + num + ";",
						true);
			}

		} 
		else if (source == strandsNumberField) {

			int num = ((Number) strandsNumberField.getValue()).intValue();

			if (num < 0) {
				strandsNumberField.setValue(0);
				num = 0;
				send = false;
			} else if (num > 499) {
				strandsNumberField.setValue(499);
				num = 499;
				send = false;
			}
			if (send) {
				// mainPanel.getScriptPanel().getScriptArea().append("strands "+num+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("set strands " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("set strands " + num + ";",
						true);
			}

		} else if (source == strandsWidthField) {

			int num = ((Number) strandsWidthField.getValue()).intValue();

			if (num < 0) {
				strandsWidthField.setValue(0);
				num = 0;
				send = false;
			} else if (num > 499) {
				strandsWidthField.setValue(499);
				num = 499;
				send = false;
			}
			if (send) {
				// mainPanel.getScriptPanel().getScriptArea().append("strands "+num+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("strands " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("strands " + num + ";",
						true);
			}

		} 
		else if (source == cartoonsField) {

			int num = ((Number) cartoonsField.getValue()).intValue();

			if (num < 0) {
				cartoonsField.setValue(0);
				num = 0;
				send = false;
			} else if (num > 499) {
				cartoonsField.setValue(499);
				num = 499;
				send = false;
			}
			if (send) {
				// mainPanel.getScriptPanel().getScriptArea().append("cartoons "+num+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("cartoons " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("cartoons " + num + ";",
						true);
			}

		} 
		else if (source == hbondsField) {

			int num = ((Number) hbondsField.getValue()).intValue();

			if (num < 0) {
				hbondsField.setValue(0);
				num = 0;
				send = false;
			} else if (num > 750) {
				hbondsField.setValue(750);
				num = 750;
				send = false;
			}
			if (send) {
				// mainPanel.getScriptPanel().getScriptArea().append("cartoons "+num+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("hbonds " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("hbonds " + num + ";",
						true);
			}

		}
		else if (source == ssbondsField) {

			int num = ((Number) ssbondsField.getValue()).intValue();

			if (num < 0) {
				ssbondsField.setValue(0);
				num = 0;
				send = false;
			} else if (num > 750) {
				ssbondsField.setValue(750);
				num = 750;
				send = false;
			}
			if (send) {
				// mainPanel.getScriptPanel().getScriptArea().append("cartoons "+num+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("ssbonds " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("ssbonds " + num + ";",
						true);
			}

		} else if (source == meshRibbonField) {
			int num = ((Number) meshRibbonField.getValue()).intValue();

			if (num < 0) {
				meshRibbonField.setValue(0);
				num = 0;
				send = false;
			} else if (num > 749) {
				meshRibbonField.setValue(749);
				num = 749;
				send = true;
			}
			if (send) {
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("meshribbon " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("meshribbon " + num + ";",
						true);
			}
		} else if (source == traceField) {
			int num = ((Number) traceField.getValue()).intValue();

			if (num < 0) {
				traceField.setValue(0);
				num = 0;
				send = false;
			} else if (num > 749) {
				traceField.setValue(749);
				num = 749;
				send = true;
			}
			if (send) {
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("trace " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("trace " + num + ";",
						true);
			}
		} else if (source == rocketsField) {
			int num = ((Number) rocketsField.getValue()).intValue();

			if (num < 0) {
				rocketsField.setValue(0);
				num = 0;
				send = false;
			} else if (num > 749) {
				rocketsField.setValue(749);
				num = 749;
				send = false;
			}
			if (send) {
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("rockets " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("rockets " + num + ";",
						true);
			}
		} else if (source == zoomField) {
			int num = ((Number) zoomField.getValue()).intValue();

			if (num < 0) {
				zoomField.setValue(0);
				num = 0;
				send = false;
			}
			if (send) {
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("zoom " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("zoom " + num + ";",
						true);
			}
		}

	}
	
	public void stateChanged(ChangeEvent e){
		///////////////////////////
		// RENDER STATE HANDLERS //
		///////////////////////////		
		Color newColor = colorChooser.getColor();
		
		if(ribbonsCheckBox.isSelected() == true){
			mainPanel.getScriptPanel().getScriptArea().insert("color ribbons "+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color ribbons "+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];", true);
		} else if(strandsCheckBox.isSelected() == true){
			mainPanel.getScriptPanel().getScriptArea().insert("color strands"+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color strands"+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];", true);
		} else if(hbondsCheckBox.isSelected() == true){
			mainPanel.getScriptPanel().getScriptArea().insert("color hbonds "+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color hbonds "+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];", true);
		} else if(ssbondsCheckBox.isSelected() == true){
			mainPanel.getScriptPanel().getScriptArea().insert("color ssbonds "+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color ssbonds "+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];", true);
		} else if(backgroundCheckBox.isSelected() == true){
			mainPanel.getScriptPanel().getScriptArea().insert("color background "+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color background "+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];", true);
		} else if(labelsCheckBox.isSelected() == true){
			mainPanel.getScriptPanel().getScriptArea().insert("color labels "+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color labels "+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];", true);
		} else if(cartoonsCheckBox.isSelected() == true){
			mainPanel.getScriptPanel().getScriptArea().insert("color cartoons "+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color cartoons "+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];", true);
		} else{
			mainPanel.getScriptPanel().getScriptArea().insert(command+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript(command+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];", true);
		}
			/*
		}
				e.getActionCommand().equals("strands") ||
				e.getActionCommand().equals("cartoons") ||
				e.getActionCommand().equals("hbonds") ||
				e.getActionCommand().equals("ssbonds") ||
				e.getActionCommand().equals("background") ||
				e.getActionCommand().equals("formallabels")){
			
			mainPanel.getScriptPanel().getScriptArea().insert("color "+e.getActionCommand()+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
			mainPanel.getScriptPanel().runScript("color "+e.getActionCommand()+" ["+newColor.getRed() +","+newColor.getGreen()+","+newColor.getBlue()+"];", false);
		}*/	

		///////////////////////////////
		// JMOL COLOR STATE HANDLERS //
		///////////////////////////////
		strandsCheckBox.setSelected(false); 
		ribbonsCheckBox.setSelected(false);  
		cartoonsCheckBox.setSelected(false); 
		hbondsCheckBox.setSelected(false); 
		ssbondsCheckBox.setSelected(false); 
		backgroundCheckBox.setSelected(false); 
		labelsCheckBox.setSelected(false);
		command = "color";
	}

	public JPanel getRenderPanel() {
		return outerPanel;
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
