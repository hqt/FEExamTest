package com.controller;

import java.util.List;

import com.view.RemoveSectionPanel;

import database.transaction.CategoryTable;
import database.transaction.SectionTable;

public class RemoveSectionController {
	
	RemoveSectionPanel frame;
	List<String> sectionList;
	
	// current selected index
	int index = -1;
	
	public RemoveSectionController(RemoveSectionPanel frame) {
		this.frame = frame;
		sectionList = SectionTable.selectAllSections();
	}
	
	public boolean removeAtIndex(int index) {
		if (index < 0) return false;
		String tblName = sectionList.get(index);
		SectionTable.removeSectionEntity(tblName);
		CategoryTable.RemoveTable(tblName);
		
		sectionList = SectionTable.selectAllSections();
		
		return true;
	}
	
	public List<String> getList() { return sectionList; }
	
	// temporary function
	public boolean removeSelectedCategory() {
		if (index < 0) return false;
		String tblName = sectionList.get(index);
		SectionTable.removeSectionEntity(tblName);
		CategoryTable.RemoveTable(tblName);
		
		// loading again
		sectionList = SectionTable.selectAllSections();
		
		return true;
	}
	
	

}
