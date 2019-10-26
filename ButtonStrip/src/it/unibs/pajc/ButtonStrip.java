package it.unibs.pajc;

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
		initialize(btnNames);
	}

	public void addButton(String name) {
		if(this.btnNames==null)
			btnNames = new ArrayList<String>();
		JButton btn = new JButton(name);
		btn.addActionListener(this);
		this.add(btn);
	}

	private void initialize(ArrayList<String> btnNames) {
		if(btnNames==null)
			return;
		for(String name:btnNames)
			addButton(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * devo mandare l'evento generato all'esterno!
		 * dal bottone l'evento va al contenitore (in questo caso il pannello)
		 * dal contenitore all'esterno
		 * */
		fireActionPerformed(e);

	}

	private void fireActionPerformed(ActionEvent e) {
		//questo actionevent serve per cambiare il source dell'evento facendolo diventare il pannello stesso
		//per impedire all'esterno di accedere direttamente ai componenti
		ActionEvent ev = new ActionEvent(this,0,e.getActionCommand());
		for(ActionListener l : listenerList) 
			l.actionPerformed(ev);
		
	}
	ArrayList<ActionListener> listenerList = new ArrayList<ActionListener>();
	public void addActionListener(ActionListener l) {
		listenerList.add(l);
	}

}
