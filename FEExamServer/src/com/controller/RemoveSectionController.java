package com.controller;

import java.util.List;

import com.view.RemoveSectionPanel;

import database.transaction.CategoryTable;
import database.transaction.SectionTable;

public class RemoveSectionController {
	
	RemoveSectionPanel frame;
	List<String> sectionList;
	
	public RemoveSectionController(RemoveSectionPanel frame) {
		this.frame = frame;
		sectionList = SectionTable.selectAllSections();
	}
	
	public boolean removeAtIndex(int index) {
		String tblName = sectionList.get(index);
		SectionTable.removeSectionEntity(tblName);
		CategoryTable.RemoveTable(tblName);
		return true;
	}
	
	public List<String> getList() { return sectionList; }

}
