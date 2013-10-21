package com.helper;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

/**
 * A rotating 3D pie chart.
 * 
 */
public class PieChart3DRotation extends ApplicationFrame {

	private static final long serialVersionUID = 1L;

	public PieChart3DRotation(String s) {
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(PieDataset piedataset) {
		JFreeChart jfreechart = ChartFactory.createPieChart3D("Statitic", piedataset, true, false, false);
		PiePlot3D pieplot3d = (PiePlot3D) jfreechart.getPlot();
		pieplot3d.setStartAngle(270D);
		pieplot3d.setDirection(Rotation.ANTICLOCKWISE);
		pieplot3d.setForegroundAlpha(0.6F);
		return jfreechart;
	}

	private static PieDataset createDataset() {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		defaultpiedataset.setValue("Network", new Double(43.200000000000003D));
		defaultpiedataset.setValue("Database", new Double(10D));
		defaultpiedataset.setValue("Operating System", new Double(17.5D));
		defaultpiedataset.setValue("Software Engineering", new Double(32.5D));
		defaultpiedataset.setValue("Programming Language", new Double(12.5D));
		return defaultpiedataset;
	}
	
	public static JPanel createCustomDemoPanel(Map<String, Double> map) {
		// set model
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		for(String key : map.keySet()) {
			defaultpiedataset.setValue(key, map.get(key));
		}
		// assign to view
		JFreeChart jfreechart = createChart(createDataset());
		Rotator rotator = new Rotator((PiePlot3D) jfreechart.getPlot());
		rotator.start();
		return new ChartPanel(jfreechart);
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		Rotator rotator = new Rotator((PiePlot3D) jfreechart.getPlot());
		rotator.start();
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[]) {
		PieChart3DRotation piechart3ddemo2 = new PieChart3DRotation("Pie Chart 3D Demo 2");
		piechart3ddemo2.pack();
		RefineryUtilities.centerFrameOnScreen(piechart3ddemo2);
		piechart3ddemo2.setVisible(true);
	}
}

	/**
	 * The rotator.
	 * 
	 */
class Rotator extends Timer implements ActionListener {

	private static final long serialVersionUID = 1L;

	/** The plot. */
	private PiePlot3D plot;

	/** The angle. */
	private int angle = 270;

	/**
	 * Constructor.
	 * 
	 * @param plot the plot.
	 */
	Rotator(final PiePlot3D plot) {
		super(100, null);
		this.plot = plot;
		addActionListener(this);
	}

	/**
	 * Modifies the starting angle.
	 * 
	 * @param event the action event.
	 */
	public void actionPerformed(final ActionEvent event) {
		this.plot.setStartAngle(this.angle);
		this.angle = this.angle + 1;
		if (this.angle == 360) {
			this.angle = 0;
		}
	}
}
