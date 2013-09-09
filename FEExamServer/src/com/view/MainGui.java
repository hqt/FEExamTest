package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

public class MainGui {

	int currentIndex = 0;
	private static final boolean OPAQUE = false;
	
	public void initialize() {
		// take original windows style
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		final JFrame frame = new JFrame("Main Form");
		frame.setSize(new Dimension(800, 600));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/** ContentPanel : Main Panel of Form */
		final JPanel contentPanel = new JPanel(new MigLayout("wrap", "[]unrel[grow]", "[grow][pref!]"));
		contentPanel.setOpaque(OPAQUE);

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
		((DefaultListModel<String>) pickerList.getModel()).addElement((String)"Add Question");
		((DefaultListModel<String>) pickerList.getModel()).addElement((String)"Add Section");
		((DefaultListModel<String>) pickerList.getModel()).addElement((String)"Add Remove Section");
		pickerList.requestFocusInWindow();
		Toolkit.getDefaultToolkit().setDynamicLayout(true);
		pickerList.setSelectedIndex(0);
		layoutPickerTabPane.addTab("Menu Bar", pickerList);
		
		/** Main Panel */
		final JPanel layoutDisplayPanel = new JPanel(new BorderLayout(0, 0));
		
		/* add action for each item selection */
		pickerList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int id = pickerList.getSelectedIndex();
				if (id == -1 || currentIndex == id)	return;
				currentIndex = id;

				layoutDisplayPanel.removeAll();
				pickerList.requestFocusInWindow();
				JComponent component = getComponentByIndex(id);
				layoutDisplayPanel.add(component);
				contentPanel.revalidate();
				/*if (id == 0) {
					pickerList.requestFocusInWindow();
					AddQuestionPanel addQuestionComponent = new AddQuestionPanel();
					layoutDisplayPanel.add(addQuestionComponent);
					contentPanel.revalidate();
					frame.pack();
				}
				
				if (id == 1) {
					pickerList.requestFocusInWindow();
					AddCategoryPanel addCategoryComponent = new AddCategoryPanel();
					addCategoryComponent.initialize();
					layoutDisplayPanel.add(addCategoryComponent);
					contentPanel.revalidate();
					frame.pack();
				}*/
			}
		});
		
		pickerList.requestFocusInWindow(); 
	    Toolkit.getDefaultToolkit().setDynamicLayout(true); 
		pickerList.setSelectedIndex(0);
		
		contentPanel.add(layoutPickerTabPane, "spany,grow 1");
		contentPanel.add(layoutDisplayPanel, "grow");
		frame.setContentPane(contentPanel);
		//frame.pack();
		frame.setVisible(true);
		
	}
	
	private JComponent getComponentByIndex(int index) {
		if (index == 0) return new AddQuestionPanel();
		if (index == 1) return new AddSectionPanel();
		else return null;
	}
	
	
	public static void main(String[] args) {

		String laf = UIManager.getSystemLookAndFeelClassName();

		if (laf.endsWith("WindowsLookAndFeel"))
			try {
				UIManager.setLookAndFeel(laf);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainGui frame = new MainGui();
				frame.initialize();
			}
		});
	}

}
