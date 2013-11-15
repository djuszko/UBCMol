import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JFormattedTextField;

import javax.swing.JPanel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Render implements ActionListener, PropertyChangeListener, ChangeListener {

	private HTMLBuilder builder;
	private int id;
	private MainPanel mainPanel;
	private JPanel mainRender;

	private JButton wireframeButton, spacefillButton, backboneButton,
	ribbonsButton, strandsButton, cartoonsButton, hbondsButton,
	ssbondsButton, meshRibbonButton, traceButton, rocketsButton, antialiasButton;

	private JFormattedTextField wireframeField, spacefillField, backboneField,
	ribbonsField, strandsField, cartoonsField, hbondsField, traceField, rocketsField,
	ssbondsField, meshRibbonField, zoomField;
	private NumberFormat numberFormat = NumberFormat.getNumberInstance();

	private boolean backboneOn, wireframeOn, spacefillOn, ribbonsOn, strandsOn,
	cartoonsOn, hbondsOn, ssbondsOn, meshRibbonOn, traceOn, rocketsOn, antialiasOn;

	public Render(HTMLBuilder builder, MainPanel mainPanel, int id) {

		this.builder = builder;
		this.id = id;
		this.mainPanel = mainPanel;

		JPanel renderPanel = new JPanel();
		renderPanel.setLayout(new BoxLayout(renderPanel, BoxLayout.Y_AXIS));

		/*
		 * Wireframe button. 
		 * Refers to the bonds drawn between the atoms. A boolean value of ON draws the selected bonds as lines.
		 */
		JPanel renderSubPanel = new JPanel();
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));

		wireframeButton = new JButton("Wireframe On");
		wireframeButton.addActionListener(this);
		wireframeButton.setActionCommand("wireframeButton");
		wireframeOn = false;
		renderSubPanel.add(wireframeButton);

		wireframeField = new JFormattedTextField(numberFormat);
		wireframeField.setValue(750);
		wireframeField.setColumns(4);
		wireframeField.addPropertyChangeListener("value", this);

		wireframeField.setMaximumSize(new Dimension(100, 20));
		renderSubPanel.add(wireframeField);
		renderPanel.add(renderSubPanel);

		/*
		 * Spacefill button.
		 * Renders selected atoms as shaded spheres.
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));
		
		spacefillButton = new JButton("Spacefill On");
		spacefillButton.addActionListener(this);
		spacefillButton.setActionCommand("spacefillButton");
		spacefillOn = false;
		renderSubPanel.add(spacefillButton);

		spacefillField = new JFormattedTextField(numberFormat);
		spacefillField.setValue(749);
		spacefillField.setColumns(4);
		spacefillField.addPropertyChangeListener("value", this);
		spacefillField.setMaximumSize(new Dimension(100, 20));

		renderSubPanel.add(spacefillField);
		renderPanel.add(renderSubPanel);


		/*
		 * backbone button
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));
		
		backboneButton = new JButton("Backbone On");
		backboneButton.addActionListener(this);
		backboneButton.setActionCommand("backboneButton");
		backboneOn = false;
		renderSubPanel.add(backboneButton);

		backboneField = new JFormattedTextField(numberFormat);
		backboneField.setValue(499);
		backboneField.setColumns(4);
		backboneField.addPropertyChangeListener("value", this);

		backboneField.setMaximumSize(new Dimension(100, 20));
		renderSubPanel.add(backboneField);
		renderPanel.add(renderSubPanel);

		/*
		 * ribbons button
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));
		
		
		ribbonsButton = new JButton("Ribbons On");
		ribbonsButton.addActionListener(this);
		ribbonsButton.setActionCommand("ribbonsButton");
		ribbonsOn = false;
		renderSubPanel.add(ribbonsButton);

		ribbonsField = new JFormattedTextField(numberFormat);
		ribbonsField.setValue(499);
		ribbonsField.setColumns(4);
		ribbonsField.addPropertyChangeListener("value", this);
		ribbonsField.setActionCommand("Ribbons");
		ribbonsField.setMaximumSize(new Dimension(100, 20));
		renderSubPanel.add(ribbonsField);
		renderPanel.add(renderSubPanel);

		/*
		 * strands button
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));

		strandsButton = new JButton("Strands On");
		strandsButton.addActionListener(this);
		strandsButton.setActionCommand("strandsButton");
		strandsOn = false;
		renderSubPanel.add(strandsButton);

		strandsField = new JFormattedTextField(numberFormat);
		strandsField.setValue(499);
		strandsField.setColumns(4);
		strandsField.addPropertyChangeListener("value", this);

		strandsField.setMaximumSize(new Dimension(100, 20));
		renderSubPanel.add(strandsField);
		renderPanel.add(renderSubPanel);

		/*
		 * cartoons button
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));

		cartoonsButton = new JButton("Cartoons On");
		cartoonsButton.addActionListener(this);
		cartoonsButton.setActionCommand("cartoonsButton");
		cartoonsOn = false;
		renderSubPanel.add(cartoonsButton);

		cartoonsField = new JFormattedTextField(numberFormat);
		cartoonsField.setValue(499);
		cartoonsField.setColumns(4);
		cartoonsField.addPropertyChangeListener("value", this);

		cartoonsField.setMaximumSize(new Dimension(100, 20));
		renderSubPanel.add(cartoonsField);
		renderPanel.add(renderSubPanel);

		/*
		 * hbonds button
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));

		hbondsButton = new JButton("HBonds On");
		hbondsButton.addActionListener(this);
		hbondsButton.setActionCommand("hbondsButton");
		hbondsOn = false;
		renderSubPanel.add(hbondsButton);

		hbondsField = new JFormattedTextField(numberFormat);
		hbondsField.setValue(750);
		hbondsField.setColumns(4);
		hbondsField.addPropertyChangeListener("value", this);

		hbondsField.setMaximumSize(new Dimension(100, 20));
		renderSubPanel.add(hbondsField);
		renderPanel.add(renderSubPanel);

		/*
		 * ssbonds button
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));
		
		ssbondsButton = new JButton("SSBonds On");
		ssbondsButton.addActionListener(this);
		ssbondsButton.setActionCommand("ssbondsButton");
		ssbondsOn = false;
		renderSubPanel.add(ssbondsButton);

		ssbondsField = new JFormattedTextField(numberFormat);
		ssbondsField.setValue(750);
		ssbondsField.setColumns(4);
		ssbondsField.addPropertyChangeListener("value", this);

		ssbondsField.setMaximumSize(new Dimension(100, 20));
		renderSubPanel.add(ssbondsField);
		renderPanel.add(renderSubPanel);

		mainRender = new JPanel();
		mainRender.setLayout(new BorderLayout());
		mainRender.add(renderPanel, BorderLayout.WEST);


		/*
		 *  Meshribbon Button
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));
		
		meshRibbonButton = new JButton("Meshribbon On");
		meshRibbonButton.addActionListener(this);
		meshRibbonButton.setActionCommand("meshRibbonButton");
		meshRibbonOn = false;
		renderSubPanel.add(meshRibbonButton);

		meshRibbonField = new JFormattedTextField(numberFormat);
		meshRibbonField.setValue(749);
		meshRibbonField.setColumns(4);
		meshRibbonField.addPropertyChangeListener("value", this);
		meshRibbonField.setMaximumSize(new Dimension(100, 20));

		renderSubPanel.add(meshRibbonField);
		renderPanel.add(renderSubPanel);

		/*
		 *  Trace Button
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));

		traceButton = new JButton("Trace On");
		traceButton.addActionListener(this);
		traceButton.setActionCommand("traceButton");
		traceOn = false;
		renderSubPanel.add(traceButton);

		traceField = new JFormattedTextField(numberFormat);
		traceField.setValue(749);
		traceField.setColumns(4);
		traceField.addPropertyChangeListener("value", this);
		traceField.setMaximumSize(new Dimension(100, 20));

		renderSubPanel.add(traceField);
		renderPanel.add(renderSubPanel);
		
		/*
		 *  Rockets Button
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));

		rocketsButton = new JButton("Rockets On");
		rocketsButton.addActionListener(this);
		rocketsButton.setActionCommand("rocketsButton");
		rocketsOn = false;
		renderSubPanel.add(rocketsButton);

		rocketsField = new JFormattedTextField(numberFormat);
		rocketsField.setValue(749);
		rocketsField.setColumns(4);
		rocketsField.addPropertyChangeListener("value", this);
		rocketsField.setMaximumSize(new Dimension(100, 20));

		renderSubPanel.add(rocketsField);
		renderPanel.add(renderSubPanel);

		/*
		 * Anti-Alias Button
		 */
		
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));

		antialiasButton = new JButton("Anti-Aliasing On");
		antialiasButton.addActionListener(this);
		antialiasButton.setActionCommand("antialiasButton");
		antialiasOn = false;
		renderSubPanel.add(antialiasButton);
		
		renderPanel.add(renderSubPanel);
		
		/*
		 * ZOOM!!!!!
		 */
		renderSubPanel = new JPanel();	 	
		renderSubPanel.setLayout(new BoxLayout(renderSubPanel, BoxLayout.X_AXIS));
		
		// slider
		JSlider zoomSlider = new JSlider(JSlider.HORIZONTAL, 50, 200, 100);
		zoomSlider.addChangeListener(this);
		
		zoomSlider.setMajorTickSpacing(50);
		zoomSlider.setMinorTickSpacing(5);
		zoomSlider.setPaintTicks(true);
		zoomSlider.setPaintLabels(true);
		
		// field
		zoomField = new JFormattedTextField(numberFormat);
		zoomField.setValue(100);
		zoomField.setColumns(4);
		zoomField.addPropertyChangeListener("value", this);
		zoomField.setMaximumSize(new Dimension(100, 20));
		
		renderSubPanel.add(zoomField);
		renderSubPanel.add(zoomSlider);
		renderPanel.add(renderSubPanel);
		
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == backboneButton) {
			if (!backboneOn) {
				// mainPanel.getScriptPanel().getScriptArea().append("backbone on"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("backbone on" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel()
				.runScript("backbone on" + ";", false);
				backboneOn = true;
				backboneButton.setText("Backbone Off");
			} else {
				// mainPanel.getScriptPanel().getScriptArea().append("backbone off"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("backbone off" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("backbone off" + ";",
						false);
				backboneOn = false;
				backboneButton.setText("Backbone On");
			}

		} else if (source == wireframeButton) {
			if (!wireframeOn) {
				// mainPanel.getScriptPanel().getScriptArea().append("wireframe on"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("wireframe on" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("wireframe on" + ";",
						false);
				wireframeOn = true;
				wireframeButton.setText("Wireframe Off");
			} else {
				// mainPanel.getScriptPanel().getScriptArea().append("wireframe off"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("wireframe off" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("wireframe off" + ";",
						false);
				wireframeOn = false;
				wireframeButton.setText("Wireframe On");
			}

		} else if (source == spacefillButton) {
			if (!spacefillOn) {
				// mainPanel.getScriptPanel().getScriptArea().append("spacefill on"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("spacefill on" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("spacefill on" + ";",
						false);
				spacefillOn = true;
				spacefillButton.setText("Spacefill Off");
			} else {
				// mainPanel.getScriptPanel().getScriptArea().append("spacefill off"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("spacefill off" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("spacefill off" + ";",
						false);
				spacefillOn = false;
				spacefillButton.setText("Spacefill On");
			}

		} else if (source == ribbonsButton) {
			if (!ribbonsOn) {
				// mainPanel.getScriptPanel().getScriptArea().append("ribbons on"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("ribbons on" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("ribbons on" + ";", false);
				ribbonsOn = true;
				ribbonsButton.setText("Ribbons Off");
				mainPanel.getJmolColor().setRibbonsEnabled(true);
			} else {
				// mainPanel.getScriptPanel().getScriptArea().append("ribbons off"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("ribbons off" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel()
				.runScript("ribbons off" + ";", false);
				ribbonsOn = false;
				ribbonsButton.setText("Ribbons On");
				mainPanel.getJmolColor().setRibbonsEnabled(false);
			}

		} else if (source == strandsButton) {
			if (!strandsOn) {
				// mainPanel.getScriptPanel().getScriptArea().append("strands on"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("strands on" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("strands on" + ";", false);
				strandsOn = true;
				strandsButton.setText("Strands Off");
				mainPanel.getJmolColor().setStrandsEnabled(true);
			} else {
				// mainPanel.getScriptPanel().getScriptArea().append("strands off"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("strands off" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel()
				.runScript("strands off" + ";", false);
				strandsOn = false;
				strandsButton.setText("Strands On");
				mainPanel.getJmolColor().setStrandsEnabled(false);
			}

		} else if (source == cartoonsButton) {
			if (!cartoonsOn) {
				// mainPanel.getScriptPanel().getScriptArea().append("cartoons on"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("cartoons on" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel()
				.runScript("cartoons on" + ";", false);
				cartoonsOn = true;
				cartoonsButton.setText("Cartoons Off");
				mainPanel.getJmolColor().setCartoonsEnabled(true);
			} else {
				// mainPanel.getScriptPanel().getScriptArea().append("cartoons off"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("cartoons off" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("cartoons off" + ";",
						false);
				cartoonsOn = false;
				cartoonsButton.setText("Cartoons On");
				mainPanel.getJmolColor().setCartoonsEnabled(false);
			}

		} else if (source == hbondsButton) {
			if (!hbondsOn) {

				// mainPanel.getScriptPanel().getScriptArea().append("cartoons on"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("select all" + ";\n" + "hbonds calculate"
						+ ";\n" + "hbonds on" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());

				// mainPanel.getScriptPanel().getScriptArea().setCaretPosition(29);
				mainPanel.getScriptPanel().runScript(
						"select all; hbonds calculate; hbonds on;", false);
				// mainPanel.getScriptPanel().getScriptArea().insert("hbonds on"+";\n",
				// mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
				// mainPanel.getScriptPanel().runScript("hbonds on"+";", true);
				hbondsOn = true;
				hbondsButton.setText("HBonds Off");
				mainPanel.getJmolColor().setHBondsEnabled(true);

			} else {
				// mainPanel.getScriptPanel().getScriptArea().append("cartoons off"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("hbonds off" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("hbonds off" + ";", false);
				hbondsOn = false;
				hbondsButton.setText("HBonds On");
				mainPanel.getJmolColor().setHBondsEnabled(false);
			}

		} else if (source == ssbondsButton) {
			if (!ssbondsOn) {
				// mainPanel.getScriptPanel().getScriptArea().append("cartoons on"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("ssbonds on" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("ssbonds on" + ";", false);
				ssbondsOn = true;
				ssbondsButton.setText("SSBonds Off");
				mainPanel.getJmolColor().setSSBondsEnabled(true);
			} else {
				// mainPanel.getScriptPanel().getScriptArea().append("cartoons off"+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("ssbonds off" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel()
				.runScript("ssbonds off" + ";", false);
				ssbondsOn = false;
				ssbondsButton.setText("SSBonds On");
				mainPanel.getJmolColor().setSSBondsEnabled(false);
			}

		}
		/*
		 * Meshribbon button
		 */
		else if (source == meshRibbonButton) {
			if (!meshRibbonOn) {
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("meshribbon on" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("meshribbon on" + ";",
						false);
				meshRibbonOn = true;
				meshRibbonButton.setText("Meshribbon Off");
			} else {
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("meshribbon off" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("meshribbon off" + ";",
						false);
				meshRibbonOn = false;
				meshRibbonButton.setText("Meshribbon On");
			}

		}
		/*
		 * Trace button
		 */
		else if (source == traceButton) {
			if (!traceOn) {
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("trace on" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("trace on" + ";",
						false);
				traceOn = true;
				traceButton.setText("Trace Off");
			} else {
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("trace off" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("trace off" + ";",
						false);
				traceOn = false;
				traceButton.setText("Trace On");
			}

		}
		/*
		 * Rockets button
		 */
		else if (source == rocketsButton) {
			if (!rocketsOn) {
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("rockets on" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("rockets on" + ";",
						false);
				rocketsOn = true;
				rocketsButton.setText("Rockets Off");
			} else {
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("rockets off" + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("rockets off" + ";",
						false);
				rocketsOn = false;
				rocketsButton.setText("Rockets On");
			}

		}
		/*
		 * Anti-Alias Button
		 */
		else if (source== antialiasButton) {
			if (!antialiasOn) {
				mainPanel.getScriptPanel().getScriptArea().insert("set antialiasDisplay on" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
				mainPanel.getScriptPanel().runScript("set antialiasDisplay on" + ";", false);
				antialiasOn = true;
				antialiasButton.setText("Anti-Alias Off");
			} else {
				mainPanel.getScriptPanel().getScriptArea().insert("set antialiasDisplay off" + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
				mainPanel.getScriptPanel().runScript("set antialiasDisplay off" + ";", false);
				antialiasOn = false;
				antialiasButton.setText("Anti-Alias On");
			}
		}
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
				// mainPanel.getScriptPanel().getScriptArea().append("wireframe "+num+";\n");
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("wireframe " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("wireframe " + num + ";",
						false);
			}

		} else if (source == spacefillField) {
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
						false);
			}

		} else if (source == backboneField) {

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
						false);
			}

		} else if (source == ribbonsField) {

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
						false);
			}

		} else if (source == strandsField) {

			int num = ((Number) strandsField.getValue()).intValue();

			if (num < 0) {
				strandsField.setValue(0);
				num = 0;
				send = false;
			} else if (num > 499) {
				strandsField.setValue(499);
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
						false);
			}

		} else if (source == cartoonsField) {

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
						false);
			}

		} else if (source == hbondsField) {

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
						false);
			}

		} else if (source == ssbondsField) {

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
						false);
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
				send = false;
			}
			if (send) {
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("meshribbon " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("meshribbon " + num + ";",
						false);
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
				send = false;
			}
			if (send) {
				mainPanel
				.getScriptPanel()
				.getScriptArea()
				.insert("trace " + num + ";\n",
						mainPanel.getScriptPanel().getScriptArea()
						.getCaretPosition());
				mainPanel.getScriptPanel().runScript("trace " + num + ";",
						false);
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
						false);
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
						false);
			}
		}

	}
	
	public void stateChanged(ChangeEvent e){
		JSlider source = (JSlider)e.getSource();
		// get zoom value
		int zoom = (int)source.getValue();
		// get current caret pos
		int current = mainPanel.getScriptPanel().getScriptArea().getCaretPosition();
		// run script
		mainPanel.getScriptPanel().getScriptArea().insert("zoom " + zoom + ";\n", mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().runScript("zoom " + zoom + ";", false);
		//set zoomField value to match new value
		zoomField.setValue(zoom);
		//select that script, delete it
		mainPanel.getScriptPanel().getScriptArea().setSelectionStart(current);
		mainPanel.getScriptPanel().getScriptArea().setSelectionEnd(mainPanel.getScriptPanel().getScriptArea().getCaretPosition());
		mainPanel.getScriptPanel().getScriptArea().setText(mainPanel.getScriptPanel().getScriptArea().getText().replace(mainPanel.getScriptPanel().getScriptArea().getSelectedText(), ""));
	}

	public JPanel getRenderPanel() {
		return mainRender;
	}
}
