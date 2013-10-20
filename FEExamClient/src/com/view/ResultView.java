package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.controller.ReviewController;
import com.controller.StatiticsController;
import com.controller.WelcomeController;

public class ResultView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JLabel lblYourScore = new JLabel("Your score: 23/50");
	public JLabel lblUsername = new JLabel("Username");

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
	public ResultView() {
		setTitle("Result");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 636, 416);

		// Center the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSubmitSuccessfully = new JLabel("SUBMIT SUCCESSFULLY!");
		lblSubmitSuccessfully.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblSubmitSuccessfully.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmitSuccessfully.setBounds(10, 37, 610, 43);
		contentPane.add(lblSubmitSuccessfully);
		
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(10, 91, 610, 32);
		contentPane.add(lblUsername);
		
		lblYourScore.setForeground(Color.RED);
		lblYourScore.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblYourScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourScore.setBounds(10, 134, 610, 43);
		contentPane.add(lblYourScore);
		
		JButton btnReviewYourTest = new JButton("Review your test");
		btnReviewYourTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReviewController.getInstance().showView();
			}
		});
		btnReviewYourTest.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnReviewYourTest.setBounds(203, 188, 224, 43);
		contentPane.add(btnReviewYourTest);
		
		JButton btnNewTest = new JButton("Attempt a new test");
		btnNewTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomeController.getInstance().showView();
				dispose();
			}
		});
		btnNewTest.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewTest.setBounds(203, 235, 224, 43);
		contentPane.add(btnNewTest);
		
		JButton btnViewStatitics = new JButton("View statitics");
		btnViewStatitics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatiticsController.getInstance().showView();
			}
		});
		btnViewStatitics.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnViewStatitics.setBounds(203, 282, 224, 43);
		contentPane.add(btnViewStatitics);
		
		JLabel lblExit = new JLabel("EXIT");
		lblExit.setForeground(Color.BLUE);
		lblExit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(10, 349, 610, 27);
		lblExit.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		       System.exit(0);
		    }  
		});
		contentPane.add(lblExit);
	}

}
