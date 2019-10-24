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
		btnNames = new ArrayList<String>();
	}

	public void addButton(String name) {
		this.btnNames.add(name);
		JButton btn = new JButton(name);
		btn.addActionListener(this);
		this.add(btn);
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
