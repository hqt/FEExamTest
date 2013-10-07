package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.controller.AddSectionController;

import net.miginfocom.swing.MigLayout;

public class AddSectionPanel extends JComponent {

	private static final long serialVersionUID = 1L;
	AddSectionController controller;
	
	/* component that controller need to reference */
	JTextField inputtxt;
	JButton okbtn, cancelbtn;
	
	public AddSectionPanel() {
		controller = new AddSectionController(this);
		setLayout(new MigLayout());
		setOpaque(false);

		JLabel categorylbl = new JLabel("Category");
		inputtxt = new JTextField(30);
		okbtn = new JButton("OK");
		cancelbtn = new JButton("Cancel");
		
		this.add(categorylbl, "wrap");
		this.add(inputtxt, "wrap");
		this.add(okbtn, "split 2"); this.add(cancelbtn, "wrap");
		
		okbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addSection();
				inputtxt.setText("");
			}
		});
		
		cancelbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.cancelSection();
				inputtxt.setText("");
			}
		});
	}
	
	public void setText(String s) {	inputtxt.setText(s); }
	
	public String getText() { return inputtxt.getText(); }
	
}
