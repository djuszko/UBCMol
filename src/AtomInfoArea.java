import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import org.jmol.api.JmolCallbackListener;
import org.jmol.api.JmolViewer;
import org.jmol.constant.EnumCallback;
import org.jmol.viewer.Viewer;


public class AtomInfoArea implements JmolCallbackListener {
	Atoms atoms;
	Viewer viewer;
	JTextArea InfoArea;
	JScrollPane scrollPane;
	String callback;
	int num;
	
	public AtomInfoArea(JmolViewer viewer, JScrollPane pane, Atoms a) {
		atoms = a;
		setViewer(viewer);
		setScrollPane(pane);
		callback = null;
		
		InfoArea = new JTextArea();
		InfoArea.setLineWrap(true);
		InfoArea.setEnabled(true);
		
		//set autoscroll
		DefaultCaret caret = (DefaultCaret)InfoArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
				
		//panel = new JScrollPane(InfoArea);
		scrollPane.setViewportView(InfoArea);
		scrollPane.setMaximumSize(new Dimension(500, 80));
		scrollPane.setMinimumSize(new Dimension(500, 80));
		scrollPane.setPreferredSize(new Dimension(500, 80));
	}
	
	public JScrollPane getScrollPane(){
		return scrollPane;
	}
	
	public void setScrollPane(JScrollPane pane){
		scrollPane = pane;
	}
		
	protected void setViewer(JmolViewer viewer){
		this.viewer = (Viewer) viewer;
	}
	
	public void addText(String s){
		InfoArea.append(s);
	}
	
	public void addTextLn(String s){
		InfoArea.append(s + "\n");
	}

	@SuppressWarnings("incomplete-switch")
	public void notifyCallback(EnumCallback type, Object[] data) {
		String temp;
		String int1, int2, int3, int4;
		String strInfo = (data == null || data[1] == null ? null : data[1].toString());
		switch (type) {
		case ECHO:
			addTextLn(strInfo);
			break;
		case MEASURE:
			String mystatus = (String) data[3];
			if (mystatus.indexOf("Picked") >= 0 || mystatus.indexOf("Sequence") >= 0) // picking mode
				addTextLn(strInfo);
			else if (mystatus.indexOf("Completed") >= 0) addTextLn(strInfo.substring(strInfo.lastIndexOf(",") + 2, strInfo.length() - 1));
			if(callback == null){
				addTextLn(strInfo);
			} else {
				switch(callback){
				case "DISTANCE":
					if(((String) data[1]).toLowerCase().contains("distance")){
						temp = ((String) data[1]).substring(((String) data[1]).indexOf("#")+1);
						int1 = temp.substring(0, temp.indexOf(" "));
						temp = temp.substring(temp.indexOf("#")+1);
						int2 = temp.substring(0, temp.indexOf(" "));
						atoms.selectDistance(Integer.parseInt(int1), Integer.parseInt(int2));
					}
					break;
				case "ANGLE":
					if(((String) data[1]).toLowerCase().contains("angle")){
						temp = ((String) data[1]).substring(((String) data[1]).indexOf("#")+1);
						int1 = temp.substring(0, temp.indexOf(" "));
						temp = temp.substring(temp.indexOf("#")+1);
						int2 = temp.substring(0, temp.indexOf(" "));
						temp = temp.substring(temp.indexOf("#")+1);
						int3 = temp.substring(0, temp.indexOf(" "));
						atoms.selectAngle(Integer.parseInt(int1), Integer.parseInt(int2), Integer.parseInt(int3));
					}
					break;
				case "TORSION":
					if(((String) data[1]).toLowerCase().contains("torsion")){
						temp = ((String) data[1]).substring(((String) data[1]).indexOf("#")+1);
						int1 = temp.substring(0, temp.indexOf(" "));
						temp = temp.substring(temp.indexOf("#")+1);
						int2 = temp.substring(0, temp.indexOf(" "));
						temp = temp.substring(temp.indexOf("#")+1);
						int3 = temp.substring(0, temp.indexOf(" "));
						temp = temp.substring(temp.indexOf("#")+1);
						int4 = temp.substring(0, temp.indexOf(" "));
						atoms.selectTorsion(Integer.parseInt(int1), Integer.parseInt(int2), Integer.parseInt(int3), Integer.parseInt(int4));
					}
					break;
				}
			}
			System.out.println(data[1]);
			break;
		case MESSAGE:	
			addTextLn(data == null ? null : strInfo);
			break;
		case PICK:
			if(callback == null){
				addTextLn(strInfo);
			} else {
				switch(callback){
				case "ATOM":
					temp = ((String) data[1]).substring(((String) data[1]).indexOf("#")+1);
					num = Integer.parseInt((temp.substring(0, (temp.indexOf(" ")))));
					atoms.selectAtomNo(num);
					addTextLn(strInfo);
					callback = null;
					break;
				case "MOLECULE":
					temp = ((String) data[1]).substring(((String) data[1]).indexOf("#")+1);
					num = Integer.parseInt((temp.substring(0, (temp.indexOf(" ")))));
					atoms.selectMolecule(num);
					callback = null;
					break;
				case "ELEMENT":
					temp = ((String) data[1]).substring(((String) data[1]).indexOf("#")+1);
					num = Integer.parseInt((temp.substring(0, (temp.indexOf(" ")))));
					atoms.selectElement(num);
					callback = null;
					break;
				case "CHAIN":
					temp = ((String) data[1]).substring(((String) data[1]).indexOf("#")+1);
					num = Integer.parseInt((temp.substring(0, (temp.indexOf(" ")))));
					atoms.selectChain(num);
					callback = null;
					break;
				case "GROUP":
					temp = ((String) data[1]).substring(((String) data[1]).indexOf("#")+1);
					num = Integer.parseInt((temp.substring(0, (temp.indexOf(" ")))));
					atoms.selectGroup(num);
					callback = null;
					break;
				case "STRUCTURE":
					temp = ((String) data[1]).substring(((String) data[1]).indexOf("#")+1);
					num = Integer.parseInt((temp.substring(0, (temp.indexOf(" ")))));
					atoms.selectStructure(num);
					callback = null;
					break;
				case "LABEL":
					temp = ((String) data[1]).substring(((String) data[1]).indexOf("#")+1);
					num = Integer.parseInt((temp.substring(0, (temp.indexOf(" ")))));
					atoms.selectLabel(num);
					callback = null;
					break;
				default:
					addTextLn(strInfo);
					break;
				}
			}
			break;
		  }
	}

	@Override
	  public boolean notifyEnabled(EnumCallback type) {
	    // See org.jmol.viewer.JmolConstants.java for a complete list
	    switch (type) {
	    case ECHO:
	    case MEASURE:
	    case MESSAGE:
	    case PICK:
	      return true;
	    case ANIMFRAME:
	    case APPLETREADY:
	    case ATOMMOVED:
	    case CLICK:
	    case ERROR:
	    case EVAL:
	    case HOVER:
	    case LOADSTRUCT:
	    case MINIMIZATION:
	    case RESIZE:
	    case SCRIPT:
	    case SYNC:
	    case STRUCTUREMODIFIED:
	      break;
	    }
	    return false;
	  }
	
	public void setCallbackString(String s){
		callback = s;
	}
	
	public String getCallbackString(String s){
		return callback;
	}

	@Override
	public void setCallbackFunction(String arg0, String arg1) {
	}
	
}