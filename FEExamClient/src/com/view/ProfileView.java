package com.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.controller.ProfileController;

public class ProfileView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public JTextPane txtName = new JTextPane();

	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		throw new Exception("The view should be call by a controller!");
	}

	/**
	 * Create the frame.
	 */
	public ProfileView() {
		setTitle("Change user name");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 335, 210);

		// Center the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYourName = new JLabel("Your name:");
		lblYourName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblYourName.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourName.setBounds(10, 11, 309, 51);
		contentPane.add(lblYourName);
		
		txtName.setBounds(57, 72, 215, 37);
		contentPane.add(txtName);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isRegisteredUser = ProfileController.getInstance().changeProfile(txtName.getText());
				if (isRegisteredUser) {
					JOptionPane.showMessageDialog(null, "Welcome back " + txtName.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, "Welcome to FE Exam, " + txtName.getText());
				}
				
				dispose();
			}
		});
		btnOk.setBounds(121, 120, 89, 36);
		contentPane.add(btnOk);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, lblYourName, txtName, btnOk}));
	}
}
