package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import java.awt.Font;

public class Guiesempio {

	private JFrame frmTestapp;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*
		 * questa parte serve per creare un thread su cui spostare la parte di interfaccia grafica
		 * */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guiesempio window = new Guiesempio();
					window.frmTestapp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Guiesempio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTestapp = new JFrame();
		frmTestapp.setTitle("TestApp");
		frmTestapp.setBounds(100, 100, 450, 300);
		frmTestapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTestapp.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblresult = new JLabel("prova");
		lblresult.setHorizontalAlignment(SwingConstants.CENTER);
		lblresult.setBounds(10, 10, 50, 20);
		frmTestapp.getContentPane().add(lblresult, BorderLayout.NORTH);
		
		
		JButton btnA1 = new JButton("azione1");
		frmTestapp.getContentPane().add(btnA1, BorderLayout.WEST);
		
		JButton btnA2 = new JButton("azione2");
		frmTestapp.getContentPane().add(btnA2, BorderLayout.EAST);
		
		textField = new JTextField();
		textField.setFont(new Font(".AppleSystemUIFont", textField.getFont().getStyle(), textField.getFont().getSize()));
		frmTestapp.getContentPane().add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
	
		
		/*
		 * prova a modificare la label con un textbox ogni volta che cambia il testo
		 * prova a mettere un border layout con i bottoni sotto label sopra e textbox centrale
		 * */
		
		btnA1.addActionListener(e->{
			lblresult.setText(lblresult.getText()+".");
		});
		
		btnA2.addActionListener(e->{
			lblresult.setText("ok");
		});
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				update();
			}
			
			private void update() {
				lblresult.setText(textField.getText());
			}
			
		});
		
	}

}
