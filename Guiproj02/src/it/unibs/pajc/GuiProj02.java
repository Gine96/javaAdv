package it.unibs.pajc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class GuiProj02 implements ActionListener{

	private JFrame frame;
	private JButton btnA;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiProj02 window = new GuiProj02();
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
	public GuiProj02() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel topPan = new JPanel();
		frame.getContentPane().add(topPan, BorderLayout.NORTH);

		JPanel centerPan = new JPanel();
		frame.getContentPane().add(centerPan, BorderLayout.CENTER);
		centerPan.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		centerPan.add(splitPane, BorderLayout.CENTER);

		JList list = new JList();
		list.setModel(listModel);
		splitPane.setRightComponent(list);

		JButton btnMain = new JButton("Main");
		splitPane.setLeftComponent(btnMain);

		btnA = new JButton("A");
		topPan.add(btnA);

		JButton btnB = new JButton("B");
		topPan.add(btnB);

		//per creare un'azione da fa generalmente posso implementare l'interfaccia ActionListener
		//poi implementi il metodo
		//ActionListener handlerBtn = e -> System.out.println("Bottone premuto");

		btnA.addActionListener(e->{
			System.out.println("A premuto");
		});

		btnA.addActionListener(this);
		btnB.addActionListener(this);
		btnMain.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		listModel.addElement("Bottone premuto " + e.getActionCommand());

	}

}
