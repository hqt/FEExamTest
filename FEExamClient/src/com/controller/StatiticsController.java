package com.controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;

import com.helper.PieChart3DRotation;
import com.view.StatiticsView;

public class StatiticsController {
	private static volatile StatiticsController controller;
	StatiticsView frame = new StatiticsView();

	private StatiticsController() {
	}

	public static StatiticsController getInstance() {
		if (controller == null) {
			synchronized (StatiticsController.class) {
				if (controller == null) {
					controller = new StatiticsController();
				}
			}
		}
		return controller;
	}

	public void showView() {
		// The correct way to call a view.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String args[]) {
	}

	public void load() {
		// loading code here
		System.out.println("loading chart");
		statiticsCategoryPieChart();
		showView();
	}
	
	public void statiticsCategoryPieChart() {
		/*List<String> sections = CommonDataModel.getInstance().sections;
		List<Integer> statitics = new ArrayList<Integer>();
		for (String section : sections) {
			
		}*/
		JPanel jpanel = PieChart3DRotation.createDemoPanel();
		frame.layoutDisplayPanel.add(jpanel, BorderLayout.CENTER);
	}
}
