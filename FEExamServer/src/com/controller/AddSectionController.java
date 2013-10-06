package com.controller;

import javax.swing.JOptionPane;

import com.view.AddSectionPanel;

import database.helper.DBHelper;
import database.transaction.CategoryTable;
import database.transaction.SectionTable;

public class AddSectionController {
	
	AddSectionPanel frame;
	public AddSectionController(AddSectionPanel frame) {
		this.frame = frame;
	}
	
	public boolean addSection() {
		String tblName = frame.getText().trim();
		if (tblName.equals("") || tblName.split(" ").length > 1) {
			JOptionPane.showMessageDialog(null, "Name of new table must not be empty or space or special character");
			return false;
		}
		
		if (DBHelper.isTableExists(tblName)) {
			JOptionPane.showMessageDialog(null, "Table must not be duplicate");
			return false;
		}
		
		SectionTable.addSectionEntity(tblName);
		CategoryTable.CreateNewTable(tblName);
		JOptionPane.showMessageDialog(null, "Successfully add new table");
		return true;
	}
	
	public void cancelSection() {
		frame.setText("");
	}
	
}
