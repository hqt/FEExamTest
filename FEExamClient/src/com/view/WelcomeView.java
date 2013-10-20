package com.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.controller.AttemptController;
import com.controller.ProfileController;
import com.controller.StatiticsController;

public class WelcomeView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JComboBox<String> cbxQuestionPack = new JComboBox<String>();
	public JLabel lblHello = new JLabel("Hello Username1");

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
	public WelcomeView() {

		setTitle("Welcome!");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 415);
		
		// Center the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSelectYourQuestion = new JLabel("SELECT YOUR QUESTION PACK");
		lblSelectYourQuestion.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblSelectYourQuestion.setBounds(101, 98, 435, 68);
		contentPane.add(lblSelectYourQuestion);

		cbxQuestionPack.setBounds(198, 177, 238, 31);
		contentPane.add(cbxQuestionPack);

		lblHello.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHello.setBounds(10, 11, 614, 14);
		contentPane.add(lblHello);

		JLabel lblChangeUsername = new JLabel("Change");
		lblChangeUsername.setForeground(Color.BLUE);
		lblChangeUsername.setBounds(508, 28, 43, 14);
		lblChangeUsername.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblChangeUsername.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ProfileController.getInstance().load();
			}
		});
		contentPane.add(lblChangeUsername);

		JLabel lblViewStatitics = new JLabel("View Statitics");
		lblViewStatitics.setForeground(Color.BLUE);
		lblViewStatitics.setBounds(561, 28, 63, 14);
		lblViewStatitics.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblViewStatitics.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				StatiticsController.getInstance().load();
			}
		});
		contentPane.add(lblViewStatitics);

		JButton btnAttempt = new JButton("Attempt");
		btnAttempt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AttemptController.getInstance().load(cbxQuestionPack.getSelectedItem().toString());
				dispose();
			}
		});
		btnAttempt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAttempt.setBounds(259, 226, 115, 52);
		contentPane.add(btnAttempt);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] {
				contentPane, lblSelectYourQuestion, cbxQuestionPack,
				btnAttempt, lblHello, lblChangeUsername, lblViewStatitics }));
	}

}
