package com.controller;

import com.view.AddSectionPanel;

public class AddSectionController {
	
	AddSectionPanel frame;
	public AddSectionController(AddSectionPanel frame) {
		this.frame = frame;
	}
	
	public boolean addSection() {
		return false;
	}
	
	public void cancelSection() {
		frame.setText("");
	}

}
