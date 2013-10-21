package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class StatiticsView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private static final boolean OPAQUE = false;
	
	public static final int INITIAL_INDEX = 0;
	
	public JPanel layoutDisplayPanel = new JPanel(new BorderLayout(0, 0));

	private static final String[] panels = new String[] { "Statitics", "OS", "Network", "Software Engineering" };
	
	/**
	 * Create the frame.
	 */
	public StatiticsView() {

		// take original windows style
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("Your statitics");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 605, 409);

		// Center the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		/** ContentPanel : Main Panel of Form */
		contentPane = new JPanel(new MigLayout("wrap", "[]unrel[grow]", "[grow][pref!]"));
		setContentPane(contentPane);
		
		/** layoutPickerTabPane : Left pane. Using to pick category */
		JTabbedPane layoutPickerTabPane = new JTabbedPane();
		
		/** List of category will add into here */
		final JList<String> pickerList = new JList<String>(new DefaultListModel<String>());
		pickerList.setOpaque(OPAQUE);
		((DefaultListCellRenderer) pickerList.getCellRenderer()).setOpaque(OPAQUE);
		pickerList.setSelectionForeground(new Color(0, 0, 220));
		pickerList.setBackground(null);
		pickerList.setBorder(new EmptyBorder(2, 5, 0, 4));
		pickerList.setFont(pickerList.getFont().deriveFont(Font.BOLD));
		for (int i = 0; i < panels.length; i++) {
			((DefaultListModel<String>) pickerList.getModel()).addElement((String) panels[i]);
		}
		pickerList.requestFocusInWindow();
		Toolkit.getDefaultToolkit().setDynamicLayout(true);
		pickerList.setSelectedIndex(INITIAL_INDEX);
		layoutPickerTabPane.addTab("Category", pickerList);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		pickerList.setSelectedIndex(0);
		
		/** Main Panel */

		/** Order all panel into frame */
		contentPane.add(layoutPickerTabPane, "spany,grow 1");
		contentPane.add(btnBack);
		contentPane.add(layoutDisplayPanel, "grow");
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		String laf = UIManager.getSystemLookAndFeelClassName();

		if (laf.endsWith("WindowsLookAndFeel"))
			// buttonOpaque = false;

			try {
				UIManager.setLookAndFeel(laf);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new StatiticsView();
			}
		});
	}
	
}
