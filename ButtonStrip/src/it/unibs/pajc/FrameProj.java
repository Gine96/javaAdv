package it.unibs.pajc;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.BorderLayout;

public class FrameProj {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameProj window = new FrameProj();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameProj() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		ArrayList<String> names = new ArrayList<String>();
		names.add("test");
		names.add("1");
		names.add("2");
		names.add("3");
		ButtonStrip buttonStrip = new ButtonStrip(names);
		frame.getContentPane().add(buttonStrip, BorderLayout.SOUTH);
		buttonStrip.addButton("A");
		buttonStrip.addButton("B");
		buttonStrip.addButton("OK");
		buttonStrip.addButton("Prova");
		buttonStrip.addActionListener(e->{
			System.out.println(e.getActionCommand());
		});
		
		
	}

}
