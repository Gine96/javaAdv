package it.unibs.pajc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonStrip extends JPanel implements ActionListener{


	private ArrayList<String> btnNames;
	/**
	 * Create the panel.
	 */
	public ButtonStrip() {

	}

	public ButtonStrip(ArrayList<String> btnNames) {
		this.btnNames=(ArrayList<String>)btnNames.clone();
		initialize();
	}

	public void addButton(String name) {
		if(this.btnNames==null)
			btnNames = new ArrayList<String>();
		JButton btn = new JButton(name);
		btn.addActionListener(this);
		this.add(btn);
	}

	private void initialize() {
		if(this.btnNames==null)
			return;
		for(String name:this.btnNames)
			addButton(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		fireActionEvent(e);

	}

	private void fireActionEvent(ActionEvent e) {
		for(ActionListener l : listenerList) {
			ActionEvent ev = new ActionEvent(this,0,e.getActionCommand());
			l.actionPerformed(ev);
		}
	}
	ArrayList<ActionListener> listenerList = new ArrayList<ActionListener>();
	public void addActionListener(ActionListener l) {
		listenerList.add(l);
	}

}
