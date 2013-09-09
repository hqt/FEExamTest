package com.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.layout.LayoutUtil;
import net.miginfocom.swing.MigLayout;

public class SwingDemoEmp extends JFrame {

	 public static final int INITIAL_INDEX = 22; 
	    private static final boolean DEBUG = false; 
	    private static final boolean OPAQUE = false; 
	 
	    private static final String[][] panels = new String[][] { 
	        {"Welcome", "\n\n         \"MigLayout makes complex layouts easy and normal layouts one-liners.\""}, 
	        {"Quick Start", "This is an example of how to build a common dialog type. Note that there are no special components, nested panels or absolute references to cell positions. If you look at the source code you will see that the layout code is very simple to understand."}, 
	        {"Plain", "A simple example on how simple it is to create normal forms. No builders needed since the whole layout manager works like a builder."}, 
	        {"Alignments", "Shows how the alignment of components are specified. At the top/left is the alignment for the column/row. The components has no alignments specified.\n\nNote that baseline alignment will be interpreted as 'center' before JDK 6."}, 
	        {"Cell Alignments", "Shows how components are aligned when both column/row alignments and component constraints are specified. At the top/left are the alignment for the column/row and the text on the buttons is the component constraint that will override the column/row alignment if it is an alignment.\n\nNote that baseline alignment will be interpreted as 'center' before JDK 6."}, 
	        {"Basic Sizes", "A simple example that shows how to use the column or row min/preferred/max size to set the sizes of the contained components and also an example that shows how to do this directly in the component constraints."}, 
	        {"Growing", "A simple example that shows how to use the growx and growy constraint to set the sizes and how they should grow to fit the available size. Both the column/row and the component grow/shrink constraints can be set, but the components will always be confined to the space given by its column/row."}, 
	        {"Grow Shrink", "Demonstrates the very flexible grow and shrink constraints that can be set on a component.\nComponents can be divided into grow/shrink groups and also have grow/shrink weight within each of those groups.\n\nBy default " + 
	                        "components shrink to their inherent (or specified) minimum size, but they don't grow."}, 
	        {"Span", "This example shows the powerful spanning and splitting that can be specified in the component constraints. With spanning any number of cells can be merged with the additional option to split that space for more than one component. This makes layouts very flexible and reduces the number of times you will need nested panels to very few."}, 
	        {"Flow Direction", "Shows the different flow directions. Flow direction for the layout specifies if the next cell will be in the x or y dimension. Note that it can be a different flow direction in the slit cell (the middle cell is slit in two). Wrap is set to 3 for all panels."}, 
	        {"Grouping", "Sizes for both components and columns/rows can be grouped so they get the same size. For instance buttons in a button bar can be given a size-group so that they will all get " + 
	                     "the same minimum and preferred size (the largest within the group). Size-groups can be set for the width, height or both."}, 
	        {"Units", "Demonstrates the basic units that are understood by MigLayout. These units can be extended by the user by adding one or more UnitConverter(s)."}, 
	        {"Component Sizes", "Minimum, preferred and maximum component sizes can be overridden in the component constraints using any unit type. The format to do this is short and simple to understand. You simply specify the " + 
	                            "min, preferred and max sizes with a colon between.\n\nAbove are some examples of this. An exclamation mark means that the value will be used for all sizes."}, 
	        {"Bound Sizes", "Shows how to create columns that are stable between tabs using minimum sizes."}, 
	        {"Cell Position", "Even though MigLayout has automatic grid flow you can still specify the cell position explicitly. You can even combine absolute (x, y) and flow (skip, wrap and newline) constraints to build your layout."}, 
	        {"Orientation", "MigLayout supports not only right-to-left orientation, but also bottom-to-top. You can even set the flow direction so that the flow is vertical instead of horizontal. It will automatically " + 
	                        "pick up if right-to-left is to be used depending on the ComponentWrapper, but it can also be manually set for every layout."}, 
	        {"Absolute Position", "Demonstrates the option to place any number of components using absolute coordinates. This can be just the position (if min/preferred size) using \"x y p p\" format or" + 
	                              "the bounds using the \"x1 y1 x2 y2\" format. Any unit can be used and percent is relative to the parent.\nAbsolute components will not disturb the flow or occupy cells in the grid. " + 
	                              "Absolute positioned components will be taken into account when calculating the container's preferred size."}, 
	        {"Component Links", "Components can be linked to any side of any other component. It can be a forward, backward or cyclic link references, as long as it is stable and won't continue to change value over many iterations." + 
	                            "Links are referencing the ID of another component. The ID can be overridden by the component's constrains or is provided by the ComponentWrapper. For instance it will use the component's 'name' on Swing.\n" + 
	                            "Since the links can be combined with any expression (such as 'butt1.x+10' or 'max(button.x, 200)' the links are very customizable."}, 
	        {"Docking", "Docking components can be added around the grid. The docked component will get the whole width/height on the docked side by default, however this can be overridden. When all docked components are laid out, whatever space " + 
	                    "is left will be available for the normal grid laid out components. Docked components does not in any way affect the flow in the grid.\n\nSince the docking runs in the same code path " + 
	                    "as the normal layout code the same properties can be specified for the docking components. You can for instance set the sizes and alignment or link other components to their docked component's bounds."}, 
	        {"Button Bars", "Button order is very customizable and are by default different on the supported platforms. E.g. Gaps, button order and minimum button size are properties that are 'per platform'. MigLayout picks up the current platform automatically and adjusts the button order and minimum button size accordingly, all without using a button builder or any other special code construct."}, 
	        {"Visual Bounds", "Human perceptible bounds may not be the same as the mathematical bounds for the component. This is for instance the case if there is a drop shadow painted by the component's border. MigLayout can compensate " + 
	                          "for this in a simple way. Note the top middle tab-component, it is not aligned visually correct on Windows XP. For the second tab the bounds are corrected automatically on Windows XP."}, 
	        {"Debug", "Demonstrates the non-intrusive way to get visual debugging aid. There is no need to use a special DebugPanel or anything that will need code changes. The user can simply turn on debug on the layout manager by using the \"debug\" constraint and it will " + 
	                  "continuously repaint the panel with debug information on top. This means you don't have to change your code to debug!"}, 
	        {"Layout Showdown", "This is an implementation of the Layout Showdown posted on java.net by John O'Conner. The first tab is a pure implemenetation of the showdown that follows all the rules. The second tab is a slightly fixed version that follows some improved layout guidelines." + 
	                            "The source code is for bothe the first and for the fixed version. Note the simplification of the code for the fixed version. Writing better layouts with MiG Layout is reasier that writing bad.\n\nReference: http://weblogs.java.net/blog/joconner/archive/2006/10/more_informatio.html"} 
//	      {"API Constraints", "This is a test of the new API constraints."}, 
	    }; 
	 
	    private int lastIndex = -10; 
	 
	    private JPanel contentPanel = new JPanel(new MigLayout("wrap", "[]unrel[grow]", "[grow][pref!]")); 
	 
	    private JTabbedPane layoutPickerTabPane = new JTabbedPane(); 
	        private JList pickerList = new JList(new DefaultListModel()); 
	 
	    private JTabbedPane southTabPane = new JTabbedPane(); 
	        private JScrollPane descrTextAreaScroll = createTextAreaScroll("", 5, 80, true); 
	        private JTextArea descrTextArea = (JTextArea) descrTextAreaScroll.getViewport().getView(); 
	 
	        private JScrollPane sourceTextAreaScroll = null; 
	        private JTextArea sourceTextArea = null; 
	 
	    private JPanel layoutDisplayPanel = new JPanel(new BorderLayout(0, 0)); 
	    private JFrame sourceFrame = null; 
	    private JTextArea sourceFrameTextArea = null; 
	 
	    private static int benchRuns = 0; 
	    
	    public static void main(String args[]) 
	    { 

	    	String laf = UIManager.getSystemLookAndFeelClassName(); 
	 
	 
	        try { 
	            UIManager.setLookAndFeel(laf); 
	        } catch (Exception ex) { 
	            ex.printStackTrace(); 
	        } 
	 
	        SwingUtilities.invokeLater(new Runnable() { 
	            public void run() { 
	            	SwingDemoEmp demo = new SwingDemoEmp(); 
	            } 
	        }); 
	    } 
	    
	    public SwingDemoEmp() 
	    { 
	        super("MigLayout Swing Demo v2.1 - Mig Layout v" + LayoutUtil.getVersion()); 
	 
	 
	        if (benchRuns == 0) { 
	            sourceTextAreaScroll = createTextAreaScroll("", 5, 80, true); 
	            sourceTextArea = (JTextArea) sourceTextAreaScroll.getViewport().getView(); 
	        } 
	 
	        if (DEBUG) { 
	            contentPanel.add(layoutDisplayPanel, BorderLayout.CENTER); 
	          contentPanel.add(layoutPickerTabPane, BorderLayout.WEST); 
//	          contentPanel.add(descriptionTabPane, BorderLayout.SOUTH); 
	        } else { 
	            contentPanel.add(layoutPickerTabPane, "spany,grow"); 
	            contentPanel.add(layoutDisplayPanel, "grow"); 
	            contentPanel.add(southTabPane, "growx"); 
	        } 
	 
	        setContentPane(contentPanel); 
	 
	            pickerList.setOpaque(OPAQUE); 
	            ((DefaultListCellRenderer) pickerList.getCellRenderer()).setOpaque(OPAQUE); 
	            pickerList.setSelectionForeground(new Color(0, 0, 220)); 
	            pickerList.setBackground(null); 
	            pickerList.setBorder(new EmptyBorder(2, 5, 0, 4)); 
	            pickerList.setFont(pickerList.getFont().deriveFont(Font.BOLD)); 
	        layoutPickerTabPane.addTab("Example Browser", pickerList); 
	 
	            descrTextAreaScroll.setBorder(null); 
	            descrTextAreaScroll.setOpaque(OPAQUE); 
	            descrTextAreaScroll.getViewport().setOpaque(OPAQUE); 
	            descrTextArea.setOpaque(OPAQUE); 
	            descrTextArea.setEditable(false); 
	            descrTextArea.setBorder(new EmptyBorder(0, 4, 0, 4)); 
	        southTabPane.addTab("Description", descrTextAreaScroll); 
	 
	 
	        if (sourceTextArea != null) { 
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
	            southTabPane.addTab("Source Code", sourceTextAreaScroll); 
	 
	            southTabPane.addMouseListener(new MouseAdapter() { 
	                public void mouseClicked(MouseEvent e) 
	                { 
	                    if (e.getClickCount() == 2) {
	                    	    showSourceInFrame(); 
	                    }
	                } 
	            }); 
	        } 
	 
	        for (int i = 0; i < panels.length; i++) 
	            ((DefaultListModel) pickerList.getModel()).addElement(panels[i][0]); 
	 
	        try { 
	            setSize(900, 650); 
	            setLocationRelativeTo(null); 
	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	            setVisible(true); 
	        } catch(Throwable t) { 
	            t.printStackTrace(); 
	            System.exit(1); 
	        } 
	 
	        pickerList.addListSelectionListener(new ListSelectionListener() { 
	            public void valueChanged(ListSelectionEvent e) 
	            { 
	                int ix = pickerList.getSelectedIndex(); 
	                if (ix == -1 || lastIndex == ix) 
	                    return; 
	 
	                lastIndex = ix; 
	 
	                String methodName = "create" + panels[ix][0].replace(' ', '_'); 
	                layoutDisplayPanel.removeAll(); 
	                try { 
	                    pickerList.requestFocusInWindow(); 
	                    JComponent panel = (JComponent) SwingDemoEmp.class.getMethod(methodName, new Class[0]).invoke(SwingDemoEmp.this, new Object[0]); 
	                    layoutDisplayPanel.add(panel); 
	                 //   descrTextArea.setText(panels[ix][1]); 
	                    descrTextArea.setCaretPosition(0); 
	                    contentPanel.revalidate(); 
	                } catch (Exception e1) { 
	                    e1.printStackTrace();   // Should never happpen... 
	                } 
	                southTabPane.setSelectedIndex(0); 
	            } 
	        }); 
	 
	        pickerList.requestFocusInWindow(); 
	        Toolkit.getDefaultToolkit().setDynamicLayout(true); 
	 
	        if (benchRuns > 0) { 
	          //  doBenchmark(); 
	        } else { 
	            pickerList.setSelectedIndex(INITIAL_INDEX); 
	        } 
	    } 
	    
	    private JScrollPane createTextAreaScroll(String text, int rows, int cols, boolean hasVerScroll) 
	    { 
	        JTextArea ta = new JTextArea(text, rows, cols); 
	        ta.setFont(UIManager.getFont("TextField.font")); 
	        ta.setWrapStyleWord(true); 
	        ta.setLineWrap(true); 
	 
	        JScrollPane scroll = new JScrollPane( 
	                ta, 
	                hasVerScroll ? ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED : ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, 
	                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
	 
	        return scroll; 
	    } 
	    
	    private void showSourceInFrame() 
	    { 
	        if (sourceTextArea == null) 
	            return; 
	 
	        JScrollPane sourceFrameTextAreaScroll = createTextAreaScroll("", 5, 80, true); 
	        sourceFrameTextArea = (JTextArea) sourceFrameTextAreaScroll.getViewport().getView(); 
	 
	        sourceFrameTextAreaScroll.setBorder(null); 
	        sourceFrameTextAreaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
	        sourceFrameTextArea.setLineWrap(false); 
	        sourceFrameTextArea.setWrapStyleWord(false); 
	        sourceFrameTextArea.setEditable(false); 
	        sourceFrameTextArea.setBorder(new EmptyBorder(10, 10, 10, 10)); 
	        sourceFrameTextArea.setFont(new Font("monospaced", Font.PLAIN, 12)); 
	        sourceFrameTextArea.setText(this.sourceTextArea.getText().substring(105)); 
	 
	        sourceFrame = new JFrame("Source Code"); 
	        sourceFrame.getContentPane().add(sourceFrameTextAreaScroll, BorderLayout.CENTER); 
	        sourceFrame.setSize(700, 800); 
	        sourceFrame.setLocationRelativeTo(this); 
	        sourceFrame.setVisible(true); 
	    } 
}
