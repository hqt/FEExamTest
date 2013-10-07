package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import com.controller.RemoveSectionController;

public class RemoveSectionPanel extends JComponent {

	private static final long serialVersionUID = 1L;

	RemoveSectionController controller;
	JButton removeBtn;
	JList<String> list;
	
	public RemoveSectionPanel() {
		controller = new RemoveSectionController(this);
		setLayout(new MigLayout());
		setOpaque(false);

		List<String> lst = controller.getList();
		list = new JList<String>(lst.toArray(new String[lst.size()]));
		JScrollPane scrollPanel =new JScrollPane(list);
		removeBtn = new JButton("Remove");
		
		this.add(scrollPanel);
		this.add(removeBtn);
		
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//TODO: implement here
			}
		});
		
		removeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean res = controller.removeAtIndex(list.getSelectedIndex());
				if (res) {
					JOptionPane.showMessageDialog(null, "Successfully remove");
					List<String> lst = controller.getList();
					list.setListData(lst.toArray(new String[lst.size()]));
				}
				else {
					JOptionPane.showMessageDialog(null, "Cannot remove. try again!!!");
				}
			}
		});
	}

}
