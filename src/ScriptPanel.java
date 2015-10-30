import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.Border;

public class ScriptPanel implements KeyListener, ActionListener{

	private HTMLBuilder builder;
	private int id;
	private MainPanel mainPanel;
	private JPanel panel;
	private JTextArea scriptArea;
	private JButton testScriptButton, resetButton, printScriptButton, saveScriptButton, loadScriptButton, clearButton;
	private Border blackline;
	private String pdbString, pdbFileName, lastEvalString;
	private int lastCaretPositionMouseClick;

	public ScriptPanel(HTMLBuilder builder, MainPanel mainPanel, int id){

		this.builder = builder;
		this.id = id;
		this.mainPanel = mainPanel;



		//EAST
		blackline = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Script Window");
		scriptArea = new JTextArea();
		JScrollPane scriptScroll = new JScrollPane(scriptArea);

		scriptArea.setLineWrap(true);

		scriptArea.setWrapStyleWord(true);
		scriptArea.setEditable(true);
		scriptArea.addKeyListener(this);
		scriptArea.setEnabled(false);

		testScriptButton = new JButton("Run Script");
		testScriptButton.addActionListener(this);
		testScriptButton.setActionCommand("testScript");
		testScriptButton.setEnabled(false);

		resetButton = new JButton("Reload");
		resetButton.addActionListener(this);
		resetButton.setActionCommand("resetScript");
		resetButton.setEnabled(false);

		printScriptButton = new JButton("JavaScript");
		printScriptButton.addActionListener(this);
		printScriptButton.setActionCommand("javaScript");
		printScriptButton.setEnabled(false);

		loadScriptButton = new JButton("Load Script");
		loadScriptButton.addActionListener(this);
		loadScriptButton.setActionCommand("loadScript");
		loadScriptButton.setEnabled(false);

		saveScriptButton = new JButton("Save Script");
		saveScriptButton.addActionListener(this);
		saveScriptButton.setActionCommand("saveScript");
		saveScriptButton.setEnabled(false);
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
		clearButton.setActionCommand("clear");
		clearButton.setEnabled(false);

		panel = new JPanel();
		panel.setBorder(blackline);
		
		//panel.setMinimumSize(new Dimension(0,0));
		//panel.setMaximumSize(new Dimension(300,300));
		//panel.setPreferredSize(new Dimension(300,0));
		
		JPanel buttonPanel = new JPanel();
		JPanel subButtonPanel = new JPanel();
		buttonPanel.add(loadScriptButton);
		buttonPanel.add(saveScriptButton);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(buttonPanel);
		panel.add(scriptScroll);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		subButtonPanel = new JPanel();
			subButtonPanel = new JPanel();
			subButtonPanel.add(testScriptButton);
			subButtonPanel.add(resetButton);
			subButtonPanel.add(printScriptButton);
		buttonPanel.add(subButtonPanel);
			subButtonPanel = new JPanel();
			subButtonPanel.add(clearButton);
		buttonPanel.add(subButtonPanel);
		panel.add(buttonPanel);
		
		scriptScroll.setMinimumSize(new Dimension(0,0));
		scriptScroll.setMaximumSize(new Dimension(300, 5000));
		scriptScroll.setPreferredSize(new Dimension(2000, 2000));
	}

	public void setPDBString(String p){
		pdbString = p;

	}
	public void setPDBFileName(String n){
		pdbFileName = n;
	}
	public JPanel getPanel(){
		return panel;
	}

	public void setAllEnabled(boolean b){
		printScriptButton.setEnabled(b);
		resetButton.setEnabled(b);
		testScriptButton.setEnabled(b);
		saveScriptButton.setEnabled(b);
		loadScriptButton.setEnabled(b);
		scriptArea.setEnabled(b);
		clearButton.setEnabled(b);
	}

	public JTextArea getScriptArea(){
		return scriptArea;
	}

	public void runScript(String s, boolean runLineOnly){

		String script = scriptArea.getText();
		if(runLineOnly){
			System.out.println("EVALUATING THE LINE");
			mainPanel.evalString(s);


		}else{
			System.out.println("EVALUATING ALL");
			System.out.println("Script is ");
			int start = 0;
			int end = script.indexOf(";", start);
			while(end > -1){
				System.out.println(start+" : "+end+" = "+script.substring(start, end));
				//lastEvalString = script.substring(start, end);
				mainPanel.evalString(script.substring(start, end).trim());
				start = end+1;
				end = script.indexOf(";", start);
			}

			//mainPanel.evalString(script);
		}
	}

	public void setError1(String s){
		System.out.println("ERROR 1::: ");
		System.out.println("Last string was "+getLastEvalString());
		System.out.println("ERROR s "+s);

		//scriptArea.setSelectionStart(scriptArea.getText().indexOf(lastEvalString));
		//scriptArea.setSelectionEnd(scriptArea.getText().indexOf(lastEvalString)+1 + lastEvalString.length());
		scriptArea.setSelectionStart(scriptArea.getText().indexOf(s));
		scriptArea.setSelectionEnd(scriptArea.getText().indexOf(s)+1 + s.length());
		scriptArea.setSelectedTextColor(Color.red);

	}
	public void setError2(int errorInt){
		System.out.println("ERROR 2:::");
		System.out.println("ERROR COUNT IS "+errorInt);
		System.out.println("Last string was "+getLastEvalString());
		if(getLastEvalString() != null){
			System.out.println(scriptArea.getText().indexOf(getLastEvalString()));
			System.out.println(scriptArea.getText().indexOf(getLastEvalString()) + getLastEvalString().length());
			scriptArea.setSelectionStart(scriptArea.getText().indexOf(getLastEvalString()));
			scriptArea.setSelectionEnd(scriptArea.getText().indexOf(getLastEvalString()) + getLastEvalString().length());
			scriptArea.setSelectedTextColor(Color.red);
		}else{

			int start = 0;

			for(int i = 0; i < errorInt; i++){
				start = scriptArea.getText().indexOf(";", start+1);
			}
			int end = scriptArea.getText().indexOf(";", start+1);

			String error = scriptArea.getText().substring(start+2, end);
			System.out.println("String error = "+error);

			//System.out.println(scriptArea.getText().indexOf(error) + error.length());

			scriptArea.requestFocusInWindow();
			scriptArea.setSelectionStart(start+1);
			scriptArea.setSelectionEnd(end+1);
			scriptArea.setSelectedTextColor(Color.red);



		}
	}


	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (("testScript").equals(e.getActionCommand())) {
			String script = scriptArea.getText();
			setLastEvalString(null);
			runScript(script, false);


		}else if(("resetScript").equals(e.getActionCommand())) {
			mainPanel.reloadPDBString();


		}else if(("javaScript").equals(e.getActionCommand())) {
			mainPanel.getJavaScriptField().setEnabled(true);
			mainPanel.getJavaScriptField().setText("script: \"load <Put Path Here>/"+pdbFileName+"; "+scriptArea.getText());

		}else if ("saveScript".equals(e.getActionCommand())) {
			builder.saveScript(scriptArea.getText());

		}else if(("loadScript").equals(e.getActionCommand())) {
			setLastEvalString(null);
			String str = builder.loadScript();
			scriptArea.append(str);
			setAllEnabled(true);

			runScript(str, false);
		}else if ("clear".equals(e.getActionCommand())){
			scriptArea.setText("");
		}

	}
	/** Handle the key typed event from the text field. */
	public void keyTyped(KeyEvent e) {

	}

	/** Handle the key-pressed event from the text field. */
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 10){

			if(scriptArea.getText().charAt(scriptArea.getCaretPosition()-1) != ';'){
				scriptArea.insert(";", scriptArea.getCaretPosition());
			}



			System.out.println("scriptArea.getCaretPosition() = "+scriptArea.getCaretPosition());

			int a = scriptArea.getText().lastIndexOf(";", scriptArea.getCaretPosition()-2);
			System.out.println("a = "+a);

			int b = scriptArea.getCaretPosition();
			System.out.println("b = "+b);
			if(a > -1)
				setLastEvalString(scriptArea.getText().substring(a+2, b));
			else{
				setLastEvalString(scriptArea.getText().substring(0, b));


			}
			if(!getLastEvalString().equals("")){
				//System.out.println(scriptArea.getText().lastIndexOf(";", scriptArea.getCaretPosition()));
				//System.out.println(scriptArea.getText().substring(scriptArea.getText().lastIndexOf("\n", scriptArea.getCaretPosition()), scriptArea.getCaretPosition()));
				//scriptArea.setSelectionStart(scriptArea.getText().lastIndexOf("\n", scriptArea.getCaretPosition()));
				//scriptArea.setSelectionEnd(scriptArea.getCaretPosition());
				//scriptArea.setSelectedTextColor(Color.red);
				System.out.println("KP : lastEvalString = "+getLastEvalString());
				StringTokenizer select = new StringTokenizer(getLastEvalString(), " ");
				if(select.nextToken().equalsIgnoreCase("SELECT")){
					String ee = select.nextToken();
					int index = ee.indexOf(';');
					if(index>-1){
						ee = ee.substring(0, index);
						System.out.println("EE = "+ee);
						String [] symbols = builder.getAtomsStrings();
						for(int i = 0; i < symbols.length; i++){
							//System.out.println(symbols[i]);
							if(symbols[i].equalsIgnoreCase(ee)){
								String [] element = builder.getAtomsNames();
								setLastEvalString("Select "+element[i]+";");
								System.out.println("New Last Eval String = "+getLastEvalString());
								if(a < 0)
									scriptArea.setSelectionStart(a);
								else
									scriptArea.setSelectionStart(a+2);
								scriptArea.setSelectionEnd(b);

								scriptArea.replaceSelection(getLastEvalString());

								break;
							}else{
								//System.out.println(symbols[i]+" no match");
							}
						}
					}
				}
				if(scriptArea.getCaretPosition() < scriptArea.getText().length()){
					System.out.println("CARET NOT AT THE END");
					runScript(getLastEvalString(), false);
				}else{
					runScript(getLastEvalString(), true);
				}

			}
		}
	}

	/** Handle the key-released event from the text field. */
	public void keyReleased(KeyEvent e) {

	}

	//mouselistener forscript panel
	public void mousePressed(MouseEvent e) {
		lastCaretPositionMouseClick = scriptArea.getCaretPosition();

	}

	public void mouseReleased(MouseEvent e) {
		//atomNamePanel.setButtonLabel(jmolPanel.getAtomName()+ " " + jmolPanel.getAtomNumber());
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public String getLastEvalString() {
		return lastEvalString;
	}

	public void setLastEvalString(String lastEvalString) {
		this.lastEvalString = lastEvalString;
	}
}
