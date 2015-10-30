import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.jmol.adapter.smarter.SmarterJmolAdapter;
import org.jmol.api.JmolAdapter;
import org.jmol.api.JmolViewer;
import org.jmol.api.JmolStatusListener;
public class MainPanel implements MouseListener, PropertyChangeListener, ChangeListener, ActionListener {

	private HTMLBuilder builder;
	private int id;
	private JmolPanel jmolPanel;
	private JmolViewer viewer;
	private FileDrop fileDrop;
	private JmolStatusListener listener;
	
	private File pdbFile;
	private String pdbString;

	private JPanel panel, jPanel, javascriptPanel, toolPanel, panelColor, jMolOptions;
	private JTextField javascriptField;
	private ScriptPanel scriptPanel;

	private int[] aminoCount = new int[22];
	private int[] atomsCount = new int[118];

	private Border blackline;

	private boolean foundTER = false;
	private boolean foundHELIX = false;
	private boolean foundSHEET = false;
	private boolean foundTURN = false;

	private boolean isLoaded = false;

	private ArrayList heteroList;
	private int[] heteroListNum;
	private int heteroListNumPointer = 0;
	private JmolColor jmolColorChooser;
	private RasmolColors rasmolColors;
	private Picking picking;
	
	JButton antialiasButton;
	private boolean antialiasOn;
	JFormattedTextField zoomField;
	JSlider zoomSlider;
	
	private NumberFormat numberFormat = NumberFormat.getNumberInstance();


	public MainPanel(HTMLBuilder builder, int id) {
		this.builder = builder;
		this.id = id;
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		picking = new Picking(builder, this, id);
		scriptPanel = new ScriptPanel(builder, this, id);
		scriptPanel.setAllEnabled(false);

		jmolPanel = new JmolPanel(picking, scriptPanel);
		jmolPanel.addMouseListener(this);
		viewer = jmolPanel.getViewer();	
		
		jmolPanel.setMinimumSize(new Dimension(100,100));
		jmolPanel.setMaximumSize(new Dimension(1000,1000));
		jmolPanel.setPreferredSize(new Dimension(350, 450));

		fileDrop = new FileDrop(id, jmolPanel, new FileDrop.Listener() {

			public void filesDropped(java.io.File[] files, int q) {
				if (!isLoaded) {
					try {

						pdbFile = files[0];
						FileInputStream fis = new FileInputStream(files[0]);
						String filename = files[0].getName();
						String ext = filename.substring(
								filename.lastIndexOf('.') + 1,
								filename.length());

						int size = (int) (files[0].length());
						byte[] in = new byte[size];
						fis.read(in);
						pdbString = new String(in);
						viewer.openStringInline(pdbString);

						loadTools(viewer, pdbString, ext, filename, false);
						scriptPanel.setAllEnabled(true);
						scriptPanel.setPDBFileName(filename);

						isLoaded = true;

					} catch (FileNotFoundException ff) {
						ff.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Opps already loaded");
					if (showDialog()) {
						try {

							pdbFile = files[0];
							FileInputStream fis = new FileInputStream(files[0]);
							String filename = files[0].getName();
							String ext = filename.substring(
									filename.lastIndexOf('.') + 1,
									filename.length());

							int size = (int) (files[0].length());
							byte[] in = new byte[size];
							fis.read(in);
							pdbString = new String(in);
							// System.out.println(str);
							viewer.openStringInline(pdbString);

							loadTools(viewer, pdbString, ext, filename, true);
							scriptPanel.setAllEnabled(true);
							scriptPanel.setPDBFileName(filename);

							isLoaded = true;

						} catch (FileNotFoundException ff) {
							ff.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			} // end filesDropped

		});
		
		// Created jPanel to contain jMol viewer and give it a border
		// Add the viewer into jPanel
		jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
		jPanel.add(jmolPanel);
		Border blackline = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Jmol");
		jPanel.setBorder(blackline);

		// Created a Jmol Options panel to go below jMol viewer and added to jPanel
		jMolOptions = new JPanel();
		jPanel.add(jMolOptions);
		Border jMolOptionsBorder = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Jmol Options");
		jMolOptions.setBorder(jMolOptionsBorder);
		// Setting the height to 0 seems to keep it as small as it can be, which is good; don't want to take space from the viewer
		jMolOptions.setMinimumSize(new Dimension(280, 80));
		jMolOptions.setMaximumSize(new Dimension(1000, 120));
		jMolOptions.setPreferredSize(new Dimension(300, 100));
		
			// jMolOptions sub panel
			JPanel subpanel = new JPanel();
			subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.Y_AXIS));
			subpanel.setMinimumSize(new Dimension(270, 100));
			subpanel.setMaximumSize(new Dimension(350, 150));
			subpanel.setPreferredSize(new Dimension(270, 80));
		
			//Zoom Panel -- this is so the zoom field is next to the slider
			JPanel zoomSubpanel = new JPanel();
			zoomSubpanel.setLayout(new BoxLayout(zoomSubpanel, BoxLayout.X_AXIS));
			
			//Zoom Label
			zoomSubpanel.add(new JLabel("Zoom"));
			
			// Zoom Slider created and added into Jmol Options
			zoomSlider = new JSlider(JSlider.HORIZONTAL, 50, 200, 100);
			zoomSlider.addChangeListener(this);
			
			zoomSlider.setMajorTickSpacing(50);
			zoomSlider.setMinorTickSpacing(5);
			zoomSlider.setPaintTicks(true);
			zoomSlider.setPaintLabels(true);
			zoomSubpanel.add(zoomSlider);
			
			// Zoom Field created and added into JMol Options
			zoomField = new JFormattedTextField(numberFormat);
			zoomField.setValue(100);
			zoomField.setColumns(4);
			zoomField.addPropertyChangeListener("value", this);
			zoomField.setMaximumSize(new Dimension(100, 20));
			zoomSubpanel.add(zoomField);
			
			subpanel.add(zoomSubpanel);
			
			// Anti Alias button created and added into JMol Options
			antialiasButton = new JButton("Anti-Aliasing On");
			antialiasButton.addActionListener(this);
			antialiasButton.setActionCommand("antialiasButton");
			antialiasOn = false;
			subpanel.add(antialiasButton);
			
			jMolOptions.add(subpanel, BorderLayout.SOUTH);


		// Tool Panel created and given a border
		toolPanel = new JPanel();
		blackline = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Tools");
		toolPanel.setBorder(blackline);
		toolPanel.setMinimumSize(new Dimension(0,0));
		toolPanel.setPreferredSize(new Dimension(100,100));
		
		//splitPane created to allow for resizing of jMol viewer
		// Add both jPanel and Tool Panel into the split pane
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jPanel, toolPanel);
		Dimension minimumSize = new Dimension(270, 0);
		jPanel.setMinimumSize(minimumSize);
		
		//set scriptPanel sizes
		scriptPanel.getPanel().setMinimumSize(new Dimension(0,0));
		scriptPanel.getPanel().setMaximumSize(new Dimension(300,300));
		scriptPanel.getPanel().setPreferredSize(new Dimension(300,0));
		
		// splitPane added into panel in the CENTER
		// scriptPanel added on the RIGHT side
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(scriptPanel.getPanel(), BorderLayout.EAST);

		// JavaScript Panel added into panel on the BOTTOM, given a border
		javascriptPanel = new JPanel();
		blackline = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Java Script");
		javascriptPanel.setBorder(blackline);
		javascriptField = new JTextField(builder.getFrameWidth() / 15);
		javascriptField.setEnabled(false);
		javascriptPanel.add(javascriptField);
		panel.add(javascriptPanel, BorderLayout.SOUTH);
	}

	public boolean showDialog() {
		boolean load = false;
		final JOptionPane optionPane = new JOptionPane(
				"Dropping this file will overwrite any changes made!\n"
						+ "Do you want to continue?",
						JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);

		final JDialog dialog = new JDialog(builder, "Warning", true);
		dialog.setContentPane(optionPane);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {

			}
		});
		optionPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				String prop = e.getPropertyName();

				if (dialog.isVisible() && (e.getSource() == optionPane)
						&& (prop.equals(JOptionPane.VALUE_PROPERTY))) {
					dialog.setVisible(false);
				}
			}
		});
		dialog.pack();
		dialog.setVisible(true);

		int value = ((Integer) optionPane.getValue()).intValue();
		if (value == JOptionPane.YES_OPTION) {
			load = true;
		} else if (value == JOptionPane.NO_OPTION) {
			load = false;
		}
		return load;
	}

	public JPanel getPanel() {
		return panel;
	}

	public ScriptPanel getScriptPanel() {
		return scriptPanel;
	}

	public JTextField getJavaScriptField() {
		return javascriptField;
	}
	
	public JPanel getJmolOptions(){
		return jMolOptions;
	}

	public String printAtoms() {
		String atomList = "";
		for (int i = 0; i < atomsCount.length; i++) {
			if (atomsCount[i] != 0) {
				atomList += builder.elementStrings[i] + " - "
						+ builder.elementNames[i] + " (" + atomsCount[i] + ")";
			}
		}
		return atomList;
	}

	public void checkAmino(String a) {

		for (int i = 0; i < builder.aminoNames.length; i++) {
			if (a.equalsIgnoreCase(builder.aminoNames[i])) {
				aminoCount[i]++;
				break;
			}
		}
	}

	public void checkAtom(String a) {

		for (int i = 0; i < builder.elementStrings.length; i++) {
			if (a.equalsIgnoreCase(builder.elementStrings[i])) {
				atomsCount[i]++;
				break;
			}
		}
	}

	public int[] getAminoCount() {
		return aminoCount;
	}

	public int[] getAtomsCount() {
		return atomsCount;

	}

	public ArrayList getHeteroList() {
		return heteroList;
	}

	public int[] getHeteroListNum() {
		return heteroListNum;
	}

	public boolean getFoundTER() {
		return foundTER;
	}

	public boolean getFoundHELIX() {
		return foundHELIX;
	}

	public boolean getFoundSHEET() {
		return foundSHEET;
	}

	public boolean getFoundTURN() {
		return foundTURN;
	}

	// from ScriptPanel
	public void evalString(String s) {
		jmolPanel.setErrorCount(0);
		System.out.println("From evalString " + viewer.evalString(s));

	}

	public void reloadPDBString() {
		viewer.openStringInline(pdbString);
	}
	
	public JmolViewer getViewer(){
		return viewer;
	}

	public void loadTools(JmolViewer viewer, String pdb, String ext,
			String filename, boolean overwrite) {
		aminoCount = new int[22];
		atomsCount = new int[118];
		heteroList = new ArrayList();
		heteroListNum = new int[100];
		heteroListNumPointer = 0;
		foundTER = false;
		foundHELIX = false;
		foundSHEET = false;
		foundTURN = false;
		System.out.println("FROM LoadTools filename = " + filename);
		blackline = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Jmol - "
						+ filename);
		panel.setBorder(blackline);
		// System.out.println("viewer.getAtomCount() "+viewer.getAtomCount());
		StringTokenizer stLong = new StringTokenizer(pdb, "\n");
		int counter = 0;

		if (ext.equalsIgnoreCase("PDB")) {

			while (stLong.hasMoreTokens()) {
				String line = stLong.nextToken();
				StringTokenizer stShort = new StringTokenizer(line);
				counter = stShort.countTokens();

				String firstToken = stShort.nextToken();

				if (firstToken.equals("ATOM")) {
					stShort.nextToken();
					checkAtom("" + stShort.nextToken().charAt(0));

					checkAmino(stShort.nextToken());

					/*
					 * old parse for end of line //get amino acid for(int i = 0;
					 * i < 2; i++){ stShort.nextToken();
					 * 
					 * } checkAmino(stShort.nextToken());
					 * 
					 * 
					 * for(int i = 4; i < counter-1; i++) { stShort.nextToken();
					 * } checkAtom(stShort.nextToken());
					 */
				} else if (firstToken.equals("HETATM")) {

					for (int i = 1; i < counter - 1; i++) {
						stShort.nextToken();
					}

					checkAtom(stShort.nextToken());

				} else if (firstToken.equals("HET")) {
					boolean found = false;
					String het = stShort.nextToken();
					heteroList.trimToSize();
					for (int i = 0; i < heteroList.size(); i++) {
						if (heteroList.get(i).equals(het)) {
							found = true;
							heteroListNum[i]++;
							System.out.println("het = " + het);
							System.out.println("het count for " + het + " is "
									+ heteroListNum[i]);
							break;

						}
					}
					if (!found) {
						heteroList.add(het);
						System.out.println("het2 = " + het);

						heteroListNum[heteroListNumPointer]++;
						System.out.println("het2 count for " + het + " is "
								+ heteroListNum[heteroListNumPointer]);
						heteroListNumPointer++;

					}

				} else if (firstToken.equals("TER")) {
					foundTER = true;

				} else if (firstToken.equals("HELIX")) {
					foundHELIX = true;

				} else if (firstToken.equals("SHEET")) {
					foundSHEET = true;

				} else if (firstToken.equals("TURN")) {
					foundTURN = true;

				}

			}

			// end if pdb
		} else if (ext.equalsIgnoreCase("MOL")) {
			System.out.println("IS A MOL");
			counter = 0;
			stLong.nextToken();
			stLong.nextToken();
			stLong.nextToken();
			while (counter <= viewer.getAtomCount()) {
				String line = stLong.nextToken();

				StringTokenizer stShort = new StringTokenizer(line);
				stShort.nextToken();
				stShort.nextToken();
				stShort.nextToken();
				checkAtom(stShort.nextToken());
				counter++;
			}

		} else if (ext.equalsIgnoreCase("XYZ")) {
			System.out.println("IS XYZ");
			stLong.nextToken();
			stLong.nextToken();

			while (stLong.hasMoreTokens()) {
				String line = stLong.nextToken();

				StringTokenizer stShort = new StringTokenizer(line);

				checkAtom(stShort.nextToken());

			}

		}
		if (overwrite) {
			toolPanel.removeAll();

		}
		JTabbedPane tabbedPane = new JTabbedPane();
		Atoms atoms = new Atoms(builder, this, id);
		tabbedPane.addTab("Selection Options", atoms.getAtomsPanel());
		Residues residues = new Residues(builder, this, id);
		tabbedPane.addTab("Residue Selection", residues.getPanel());

		/////////////////////////RENDER/////////////////////////

		Render render = new Render(builder, this, id);
		tabbedPane.addTab("Visual Options", render.getRenderPanel());

		tabbedPane.setMinimumSize(new Dimension(0,0));
		tabbedPane.setPreferredSize(new Dimension(540, 615));
		
		jmolColorChooser = new JmolColor(builder, this, id, "color");
		panelColor = new JPanel();
		panelColor.add(jmolColorChooser.getColorPanel());
		panelColor.add(jmolColorChooser.getChooser());

		rasmolColors = new RasmolColors(builder, this, id);

		toolPanel.add(tabbedPane);
		builder.setEditEnable(true);
		getScriptPanel().getScriptArea().insert("select none;\n", getScriptPanel().getScriptArea().getCaretPosition());
		getScriptPanel().runScript("select none;", false);
		scriptPanel.getScriptArea().setText("");
	}

	public JmolColor getJmolColor() {
		return jmolColorChooser;
	}

	// mouselistener for jmol panel
	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		// atomNamePanel.setButtonLabel(jmolPanel.getAtomName()+ " " +
		// jmolPanel.getAtomNumber());
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	static class JmolPanel extends JPanel {
		JmolViewer viewer;
		JmolAdapter adapter;
		String atomName;
		String atomNumber;
		Picking pickingPanel;
		ScriptPanel scriptPanel;
		int errorCount = 0;
		
		JmolPanel(Picking pickingPanel, ScriptPanel scriptPanel) {
			adapter = new SmarterJmolAdapter();
			viewer = JmolViewer.allocateViewer(this, adapter);
			this.pickingPanel = pickingPanel;
			this.scriptPanel = scriptPanel;
		}

		public void setErrorCount(int i) {
			errorCount = i;
		}

		public String getAtomName() {
			return atomName;
		}

		public String getAtomNumber() {
			return atomNumber;
		}

		public JmolViewer getViewer() {
			return viewer;
		}

		public JmolAdapter getAdapter() {
			return adapter;

		}

		final Dimension currentSize = new Dimension();
		final Rectangle rectClip = new Rectangle();

		public void paint(Graphics g) {
			getSize(currentSize);
			g.getClipBounds(rectClip);
			viewer.renderScreenImage(g, currentSize, rectClip);
		}
		
		public void notifyAtomPicked(int atomIndex, java.lang.String strInfo) {

			System.out.println("Atom picked: " + atomIndex + " " + strInfo);
			/*
			 * System.out.println("Atom Name = "+viewer.getAtomName(atomIndex));
			 * System
			 * .out.println("Atom Number = "+viewer.getAtomNumber(atomIndex));
			 * atomName = viewer.getAtomName(atomIndex); atomNumber =
			 * ""+viewer.getAtomNumber(atomIndex);
			 * atomNamePanel.setButtonLabel(atomName + " " + atomNumber);
			 * System.out.println("AtomIndex = "+atomIndex);
			 */
			String str = strInfo.substring(0, strInfo.indexOf(" #"));
			// System.out.println(str);
			pickingPanel.setAtomInfoText(strInfo);
			scriptPanel.runScript(strInfo, true);
			// pickingPanel.setSelectedAtom(str);
		}
		
		public void notifyScriptTermination(String s, int i) {
			System.out.println("JSL notifyScriptTermination " + s + " " + i);
		}

		public void notifyNewPickingModeMeasurement(int i, String s) {
			System.out.println("JSL notifyNewPickingModeMeasurement " + i + " "
					+ s);
			//pickingPanel.setAtomInfoText(s);
		}

		public void notifyResized(int i, int j) {
		}

		public void notifyAtomHovered(int i, String s) {
		}

		public void createImage(String s, Object o, int i) {
		}

		public void notifyFileLoaded(String a, String s, String d, Object o,
				String f) {
		}

		public void handlePopupMenu(int x, int y) {
		}

		public void notifyFrameChanged(int frameNo) {
		}

		public void showUrl(String url) {
		}

		public void showConsole(boolean showConsole) {
		}

		public void createImage(String file, String type, int quality) {
		}

		public String eval(String strEval) {
			System.out.println("JSL eval " + strEval);
			return null;
		}

		public float[][] functionXY(String functionName, int x, int y) {
			return null;
		}

		public void notifyNewDefaultModeMeasurement(int count, String strInfo) {
			System.out.println("JSL notifyNewDefaultModeMeasurement " + count
					+ " " + strInfo);
			//pickingPanel.setAtomInfoText(count + " " + strInfo);
		}

		public void notifyScriptStart(String statusMessage,
				String additionalInfo) {
		}

		public void notifyFrameChanged(int a, int s, int d, int f, int g) {
		}

		public void sendConsoleEcho(String s) {
			System.out.println("JSL sendConsoleEcho " + s);
		}

		public void sendConsoleMessage(String s) {
			if (s.indexOf("completed") > 0) {
				++errorCount;
			}
			System.out.println("JSL sendConsoleMessage " + s);
			System.out.println("JSL ERROR COUNT + " + errorCount);
			String error = "ERROR";
			// for when type error
			if (s.indexOf(error) > -1) {

				// System.out.println("error is "+s.substring(s.lastIndexOf(">> ")+3,
				// s.lastIndexOf(" <<")));
				int i = s.lastIndexOf(" <<");
				System.out.println("error is "
						+ s.substring(s.lastIndexOf(" ", i - 1) + 1, i));
				// System.out.println(error+"!!!!!");
				scriptPanel.setError1(s.substring(
						s.lastIndexOf(" ", i - 1) + 1, i));
				// for when zero items selected
			} else if (s.charAt(0) == '0') {
				scriptPanel.setError2(errorCount);
			}
		}
		
		public void sendSyncScript(String s, String t) {
		}

	}
	// ZOOM ACTION HANDLERS
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		// get zoom value
		int zoom = (int)source.getValue();
		// get current caret pos
		int current = this.getScriptPanel().getScriptArea().getCaretPosition();
		// run script
		this.getScriptPanel().getScriptArea().insert("zoom " + zoom + ";\n", this.getScriptPanel().getScriptArea().getCaretPosition());
		this.getScriptPanel().runScript("zoom " + zoom + ";", false);
		//set zoomField value to match new value
		zoomField.setValue(zoom);
		//select that script, delete it
		this.getScriptPanel().getScriptArea().setSelectionStart(current);
		this.getScriptPanel().getScriptArea().setSelectionEnd(this.getScriptPanel().getScriptArea().getCaretPosition());
		this.getScriptPanel().getScriptArea().setText(this.getScriptPanel().getScriptArea().getText().replace(this.getScriptPanel().getScriptArea().getSelectedText(), ""));
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		Object source = e.getSource();
		boolean send = true;
		
		if (source == zoomField) {
			int num = ((Number) zoomField.getValue()).intValue();

			if (num < 0) {
				zoomField.setValue(0);
				num = 0;
				send = false;
			}
			if (send) {
				this
				.getScriptPanel()
				.getScriptArea()
				.insert("zoom " + num + ";\n",
						this.getScriptPanel().getScriptArea()
						.getCaretPosition());
				this.getScriptPanel().runScript("zoom " + num + ";",
						false);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source== antialiasButton) {
			if (!antialiasOn) {
				this.getScriptPanel().getScriptArea().insert("set antialiasDisplay on" + ";\n", this.getScriptPanel().getScriptArea().getCaretPosition());
				this.getScriptPanel().runScript("set antialiasDisplay on" + ";", false);
				antialiasOn = true;
				antialiasButton.setText("Anti-Alias Off");
			} else {
				this.getScriptPanel().getScriptArea().insert("set antialiasDisplay off" + ";\n", this.getScriptPanel().getScriptArea().getCaretPosition());
				this.getScriptPanel().runScript("set antialiasDisplay off" + ";", false);
				antialiasOn = false;
				antialiasButton.setText("Anti-Alias On");
			}
		}
		
	}
}
