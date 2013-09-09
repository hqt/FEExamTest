package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

public class MainJFrame {

	JLabel jlabel1; // usr name

	JLabel jlabel2; // school

	private static final String[] panels = new String[] { "Welcome", "OS", "Network", "Software Engineering" };

	private static final boolean OPAQUE = false;

	public static final int INITIAL_INDEX = 22;

	private int currentIndex = 0;

	public void initialize() {

		// take original windows style
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		JFrame frame = new JFrame("Main Form");
		frame.setSize(new Dimension(800, 600));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/** ContentPanel : Main Panel of Form */
		final JPanel contentPanel = new JPanel(new MigLayout("wrap", "[]unrel[grow]", "[grow][pref!]"));

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

		/** Main Panel */
		final JPanel layoutDisplayPanel = new JPanel(new BorderLayout(0, 0));

		/** footer of form */
		final JTabbedPane southTabPane = new JTabbedPane();

		JScrollPane descrTextAreaScroll = createTextAreaScroll("", 5, 80, true);
		JTextArea descrTextArea = (JTextArea) descrTextAreaScroll.getViewport().getView();
		descrTextAreaScroll.setBorder(null);
		descrTextAreaScroll.setOpaque(OPAQUE);
		descrTextAreaScroll.getViewport().setOpaque(OPAQUE);
		descrTextArea.setOpaque(OPAQUE);
		descrTextArea.setEditable(false);
		descrTextArea.setBorder(new EmptyBorder(0, 4, 0, 4));

		JScrollPane sourceTextAreaScroll = createTextAreaScroll("", 5, 80, true);
		JTextArea sourceTextArea = (JTextArea) sourceTextAreaScroll.getViewport().getView();
		sourceTextAreaScroll.setBorder(null);
		sourceTextAreaScroll.setOpaque(OPAQUE);
		sourceTextAreaScroll.getViewport().setOpaque(OPAQUE);
		sourceTextAreaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sourceTextArea.setOpaque(OPAQUE);
		sourceTextArea.setLineWrap(false);
		sourceTextArea.setWrapStyleWord(false);
		sourceTextArea.setEditable(false);
		sourceTextArea.setBorder(new EmptyBorder(0, 4, 0, 4));
		sourceTextArea.setFont(new Font("monospaced", Font.PLAIN, 11));

		/** add two tab into footer */
		southTabPane.addTab("Description", descrTextAreaScroll);
		southTabPane.addTab("Source Code", sourceTextAreaScroll);

		/* add action for each item selection */
		pickerList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int id = pickerList.getSelectedIndex();
				if (id == -1 || currentIndex == id)
					return;
				currentIndex = id;

				String methodName = "create" + panels[id].replace(' ', '_');
				layoutDisplayPanel.removeAll();
				try {
					pickerList.requestFocusInWindow();
					// act as function pointer
					JComponent panel = (JComponent) MainJFrame.class.getMethod(methodName, new Class[0]).invoke(
							MainJFrame.this, new Object[0]);
					layoutDisplayPanel.add(panel);
					contentPanel.revalidate();
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				southTabPane.setSelectedIndex(0);
			}
		});
		
		pickerList.setSelectedIndex(0);

		/** Order all panel into frame */
		contentPanel.add(layoutPickerTabPane, "spany,grow 1");
		contentPanel.add(layoutDisplayPanel, "grow");
		contentPanel.add(southTabPane, "growx");
		frame.setContentPane(contentPanel);

		frame.pack();
		frame.setVisible(true);

	}

	public JComponent createWelcome() {
		JTabbedPane tabbedPane = new JTabbedPane();
		MigLayout lm = new MigLayout("ins 50, fill", "", "[grow]unrel[]");
		JPanel mainPanel = createTabPanel(lm);
		tabbedPane.add("Welcome", mainPanel);
		return tabbedPane;
	}

	private JPanel createTabPanel(LayoutManager lm) {
		JPanel panel = new JPanel(lm);
		//configureActiveComponet(panel);
		panel.setOpaque(OPAQUE);
		return panel;
	}
	
	private JScrollPane createTextAreaScroll(String text, int rows, int cols, boolean hasVerScroll) {
		JTextArea ta = new JTextArea(text, rows, cols);
		ta.setFont(UIManager.getFont("TextField.font"));
		ta.setWrapStyleWord(true);
		ta.setLineWrap(true);

		JScrollPane scroll = new JScrollPane(ta, hasVerScroll ? ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
				: ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		return scroll;
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
				MainJFrame frame = new MainJFrame();
				frame.initialize();
			}
		});
	}

}
