package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import com.controller.AttemptController;
import com.controller.ResultController;
import com.controller.WelcomeController;
import com.model.QuestionList;

import database.model.Question;

public class AttemptView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/** fields need to access from controller */
	public JLabel lblDate = new JLabel("12/12/2013 01:55 PM");
	public JLabel lblUsername = new JLabel("Username1");
	public JLabel lblSection = new JLabel("Network");
	public JComboBox<String> cbxPagination = new JComboBox<String>();
	public JList<String> lstQuestions = new JList<String>();
	public JPanel pnlQuestion = new JPanel();

	/**
	 * Launch the application.
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		throw new Exception("The view should be call by a controller!");
	}

	/**
	 * Create the frame.
	 */
	public AttemptView() {
		setTitle("Attempt");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 520);

		// Center the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 174, 382, 0 };
		gbl_contentPane.rowHeights = new int[] { 461, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 165, 0 };
		gbl_panel.rowHeights = new int[] { 15, 15, 20, 30, 20, 100, 30, 20 };
		gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, 0.0 };
		panel.setLayout(gbl_panel);

		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPm = new GridBagConstraints();
		gbc_lblPm.anchor = GridBagConstraints.NORTH;
		gbc_lblPm.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPm.insets = new Insets(0, 0, 5, 0);
		gbc_lblPm.gridx = 0;
		gbc_lblPm.gridy = 0;
		panel.add(lblDate, gbc_lblPm);

		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblQuestionPack = new GridBagConstraints();
		gbc_lblQuestionPack.anchor = GridBagConstraints.NORTH;
		gbc_lblQuestionPack.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuestionPack.insets = new Insets(0, 0, 5, 0);
		gbc_lblQuestionPack.gridx = 0;
		gbc_lblQuestionPack.gridy = 1;
		panel.add(lblUsername, gbc_lblQuestionPack);

		lblSection.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSection.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblSection, gbc_lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.PINK);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel("15:00:00");
		label.setBackground(Color.PINK);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 4;
		panel.add(cbxPagination, gbc_comboBox);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.info);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 5;
		panel.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		lstQuestions.setBackground(new Color(248, 248, 255));
		panel_2.add(lstQuestions);

		JButton btnFinish = new JButton("Finish");
		btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnFinish = new GridBagConstraints();
		gbc_btnFinish.fill = GridBagConstraints.BOTH;
		gbc_btnFinish.insets = new Insets(0, 0, 5, 0);
		gbc_btnFinish.gridx = 0;
		gbc_btnFinish.gridy = 6;
		panel.add(btnFinish, gbc_btnFinish);
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AttemptController.getInstance().processUserInput();
				ResultController.getInstance().load();
				dispose();
			}
		});

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomeController.getInstance().load();
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnBack.anchor = GridBagConstraints.NORTH;
		gbc_btnBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 7;
		panel.add(btnBack, gbc_btnBack);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);

		pnlQuestion.setLayout(new MigLayout());
		scrollPane.setViewportView(pnlQuestion);



	}
}
