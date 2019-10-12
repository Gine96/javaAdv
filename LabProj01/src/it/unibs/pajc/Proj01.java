package it.unibs.pajc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.awt.event.ActionEvent;

public class Proj01 {

	private JFrame frmProj;
	private JTextField txt1;
	private JTextField txt2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proj01 window = new Proj01();
					window.frmProj.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	HashMap<String, BinaryOperator<Double>> calcModel = new HashMap<String, BinaryOperator<Double>>();
	
	/**
	 * Create the application.
	 */
	public Proj01() {
		calcModel.put("+", (x,y)->x+y);
		calcModel.put("-", (x,y)->x-y);
		calcModel.put("*", (x,y)->x*y);
		calcModel.put("/", (x,y)->x/y);
		calcModel.put("^", (x,y)->Math.pow(x, y));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProj = new JFrame();
		frmProj.setResizable(false);
		frmProj.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 12));
		frmProj.setTitle("ProjCalc");
		frmProj.setBounds(100, 100, 450, 300);
		frmProj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProj.getContentPane().setLayout(null);
		
		/*
		 * Qua genera bottoni per ogni operazione nella hashmap sopra
		 * */
		int x=15,y=80;
		int btnsWidth=30;
		int btnsHeigth=30;
		int btnsOffset=5;
		HashMap<String, JButton> btns = new HashMap<String, JButton>();
		for(String op:calcModel.keySet()) {
			btns.put(op.toString(), new JButton(op.toString()));
			if(x+btnsWidth>frmProj.getWidth()) {
				x=15;
				y+=btnsOffset+btnsHeigth;
			}
			btns.get(op.toString()).setBounds(x,y,btnsWidth,btnsHeigth);
			x+=btnsOffset+btnsWidth;
			frmProj.getContentPane().add(btns.get(op.toString()));
		}

		JLabel lblResult = new JLabel("--");
		lblResult.setBounds(0, 228, 450, 50);
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		frmProj.getContentPane().add(lblResult);

		txt1 = new JTextField();
		txt1.setText("1");
		txt1.setBounds(6, 44, 130, 26);
		frmProj.getContentPane().add(txt1);
		txt1.setColumns(10);

		txt2 = new JTextField();
		txt2.setText("2");
		txt2.setBounds(314, 44, 130, 26);
		frmProj.getContentPane().add(txt2);
		txt2.setColumns(10);

		/*
		 * voglio evitare di scrivere piu cose uguali...
		 * posso creare un oggetto che tiene quella lambda exp
		 * */
		
		ActionListener btnListener = e->{
			double v1=Double.parseDouble(txt1.getText());
			double v2=Double.parseDouble(txt2.getText());
			//questo fa in modo che posso usare l'action command dei bottoni per cercare le operazioni da fare nella hash map
			BinaryOperator<Double> op = calcModel.get(e.getActionCommand());
			lblResult.setText(String.valueOf(op.apply(v1, v2)));
		};
		
		/*
		 * estendi la calcolatrice con altre operazioni
		 * prova a generare in automatico i bottoni da qua senza usare il designer
		 * OK!!
		 * */
		
		for(String op : calcModel.keySet()) 
			btns.get(op).addActionListener(btnListener);
		
		

	}
}
