package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;

import com.controller.AddQuestionController;

public class AddQuestionPanel extends JComponent {

	private static final long serialVersionUID = 1L;

	AddQuestionController controller;
	List<String> sectionList;

	// textfield
	public JTextField sectiontxt;
	public JTextArea questiontxt;
	public JTextField ansatxt;
	public JTextField ansbtxt;
	public JTextField ansctxt;
	public JTextField ansdtxt;
	public JTextField ansetxt;
	public JTextField explaintxt;
	public JTextField answertxt;

	// link for image textfield
	public JTextField imgquestiontxt;
	public JTextField imgatxt;
	public JTextField imgbtxt;
	public JTextField imgctxt;
	public JTextField imgdtxt;
	public JTextField imgetxt;
	public JTextField imgexplaintxt;

	// button
	JButton imgquestionbtn;
	JButton imgabtn;
	JButton imgbbtn;
	JButton imgcbtn;
	JButton imgdbtn;
	JButton imgebtn;
	JButton imgexplainbtn;
	JButton nextbtn;
	JButton finishbtn;
	JButton backbtn;

	public JComboBox<String> cmbSections;
	
	public AddQuestionPanel() {
		controller = new AddQuestionController(this);
		sectionList = controller.sectionList;
		setLayout(new MigLayout());
		setOpaque(false);
		
		/* Combobox contains all type of category */
		cmbSections = new JComboBox<String>(sectionList.toArray(new String[sectionList.size()]));
		
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
		sectiontxt = new JTextField(40);	
		questiontxt = new JTextArea(4, 70);	
		ansatxt = new JTextField(70);
		ansbtxt = new JTextField(70);
		ansctxt = new JTextField(70);
		ansdtxt = new JTextField(70);
		ansetxt = new JTextField(70);
		explaintxt = new JTextField(70);
		answertxt = new JTextField(20);
		
		/* image link text field */
		imgquestiontxt = new JTextField(70);
		imgatxt = new JTextField(70);
		imgbtxt = new JTextField(70);
		imgctxt = new JTextField(70);
		imgdtxt = new JTextField(70);
		imgetxt = new JTextField(70);
		imgexplaintxt = new JTextField(70);

		/* button section */
		imgquestionbtn = new JButton("...");
		imgabtn = new JButton("...");
		imgbbtn = new JButton("...");
		imgcbtn = new JButton("...");
		imgdbtn = new JButton("...");
		imgebtn = new JButton("...");
		imgexplainbtn = new JButton("...");
		nextbtn = new JButton("Next");
		finishbtn = new JButton("Finsish");
		backbtn = new JButton("Back");
		
		/* add all to panel */
		this.add(sectionlbl, "split 2"); this.add(cmbSections, "wrap");
		this.add(questionlbl, "wrap"); this.add(new JScrollPane(questiontxt), "wrap"); this.add(imgquestiontxt); this.add(imgquestionbtn, "wrap");
		this.add(ansalbl, "wrap"); this.add(ansatxt, "wrap"); this.add(imgatxt); this.add(imgabtn, "wrap");
		this.add(ansblbl, "wrap"); this.add(ansbtxt, "wrap"); this.add(imgbtxt); this.add(imgbbtn, "wrap");
		this.add(ansclbl, "wrap"); this.add(ansctxt, "wrap"); this.add(imgctxt); this.add(imgcbtn, "wrap");
		this.add(ansdlbl, "wrap"); this.add(ansdtxt, "wrap"); this.add(imgdtxt); this.add(imgdbtn, "wrap");
		this.add(anselbl, "wrap"); this.add(ansetxt, "wrap"); this.add(imgetxt); this.add(imgebtn, "wrap");
		this.add(explainlbl, "wrap"); this.add(explaintxt, "wrap"); this.add(imgexplaintxt); this.add(imgexplainbtn, "wrap");
		this.add(answerlbl, "split 2"); this.add(answertxt, "wrap"); 
		this.add(backbtn, "split 2"); this.add(nextbtn); this.add(finishbtn, "wrap");
		
		// add jchooser for image button
		addActionListener(imgquestionbtn);
		addActionListener(imgabtn);
		addActionListener(imgbbtn);
		addActionListener(imgcbtn);
		addActionListener(imgdbtn);
		addActionListener(imgebtn);
		addActionListener(imgexplainbtn);
		
		//TODO: add code for back button 
		backbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		nextbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addWord();
			}
		});
		
		finishbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addToDatabase();
			}
		});
	}

	private void addActionListener(final JButton btn) {
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String link = getLink();
				if (btn == imgquestionbtn) imgquestiontxt.setText(link);
				else if (btn == imgabtn) imgatxt.setText(link);
				else if (btn == imgbbtn) imgbtxt.setText(link);
				else if (btn == imgcbtn) imgctxt.setText(link);
				else if (btn == imgdbtn) imgdtxt.setText(link);
				else if (btn == imgebtn) imgetxt.setText(link);
				else if (btn == imgexplainbtn) imgexplaintxt.setText(link);
			}
		});
	}
	
	private String getLink() {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			if (f != null) { // Make sure the user didn't choose a directory.
				String path = f.getAbsolutePath();// get the absolute path to selected path
				return path;
			}
		}
		return "";
	}
}
