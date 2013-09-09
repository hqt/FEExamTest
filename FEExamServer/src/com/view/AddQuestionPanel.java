package com.view;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class AddQuestionPanel extends JComponent {

	private static final long serialVersionUID = 1L;
	
	public AddQuestionPanel() {
		setLayout(new MigLayout());
		setOpaque(false);
		
		/* label section */
		JLabel sectionlbl = new JLabel("Section:");
		JLabel questionlbl = new JLabel("Question");
		JLabel ansalbl = new JLabel("Answer A");
		JLabel ansblbl = new JLabel("Answer B");
		JLabel ansclbl = new JLabel("Answer C");
		JLabel ansdlbl = new JLabel("Answer D");
		JLabel anselbl = new JLabel("Answer E");
		JLabel explainlbl = new JLabel("Explain");
		JLabel answerlbl = new JLabel("Answer");
		
		/* text field section */
		JTextField sectiontxt = new JTextField(40);	
		JTextArea questiontxt = new JTextArea(4, 70);	
		JTextField ansatxt = new JTextField(70);
		JTextField ansbtxt = new JTextField(70);
		JTextField ansctxt = new JTextField(70);
		JTextField ansdtxt = new JTextField(70);
		JTextField ansetxt = new JTextField(70);
		JTextField explaintxt = new JTextField(70);
		JTextField answertxt = new JTextField(20);
		
		/* image link text field */
		JTextField imgquestiontxt = new JTextField(70);
		JTextField imgatxt = new JTextField(70);
		JTextField imgbtxt = new JTextField(70);
		JTextField imgctxt = new JTextField(70);
		JTextField imgdtxt = new JTextField(70);
		JTextField imgetxt = new JTextField(70);
		JTextField imgexplaintxt = new JTextField(70);

		/* button section */
		JButton imgquestionbtn = new JButton("...");
		JButton imgabtn = new JButton("...");
		JButton imgbbtn = new JButton("...");
		JButton imgcbtn = new JButton("...");
		JButton imgdbtn = new JButton("...");
		JButton imgebtn = new JButton("...");
		JButton imgexplainbtn = new JButton("...");
		JButton nextbtn = new JButton("Next");
		JButton finishbtn = new JButton("Finsish");
		JButton backbtn = new JButton("Back");
		
		/* add all to panel */
		this.add(sectionlbl, "split 2"); this.add(sectiontxt, "wrap");
		this.add(questionlbl, "wrap"); this.add(new JScrollPane(questiontxt), "wrap"); this.add(imgquestiontxt); this.add(imgquestionbtn, "wrap");
		this.add(ansalbl, "wrap"); this.add(ansatxt, "wrap"); this.add(imgatxt); this.add(imgabtn, "wrap");
		this.add(ansblbl, "wrap"); this.add(ansbtxt, "wrap"); this.add(imgbtxt); this.add(imgbbtn, "wrap");
		this.add(ansclbl, "wrap"); this.add(ansctxt, "wrap"); this.add(imgctxt); this.add(imgcbtn, "wrap");
		this.add(ansdlbl, "wrap"); this.add(ansdtxt, "wrap"); this.add(imgdtxt); this.add(imgdbtn, "wrap");
		this.add(anselbl, "wrap"); this.add(ansetxt, "wrap"); this.add(imgetxt); this.add(imgebtn, "wrap");
		this.add(explainlbl, "wrap"); this.add(explaintxt, "wrap"); this.add(imgexplaintxt); this.add(imgexplainbtn, "wrap");
		this.add(answerlbl, "split 2"); this.add(answertxt, "wrap"); 
		this.add(backbtn, "split 2"); this.add(nextbtn); this.add(finishbtn, "wrap");
	}

}
