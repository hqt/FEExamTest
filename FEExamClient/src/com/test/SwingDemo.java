package com.test;
 
import net.miginfocom.layout.*; 
import net.miginfocom.swing.MigLayout; 
 
import javax.swing.*; 
import javax.swing.border.EmptyBorder; 
import javax.swing.border.EtchedBorder; 
import javax.swing.border.LineBorder; 
import javax.swing.event.ListSelectionEvent; 
import javax.swing.event.ListSelectionListener; 
import java.awt.*; 
import java.awt.event.*; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.PrintWriter; 
import java.io.StringWriter; 
 
public class SwingDemo extends JFrame 
{ 
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
//      {"API Constraints", "This is a test of the new API constraints."}, 
    }; 
 
    private int lastIndex = -10; 
 
    private JPanel contentPanel = DEBUG ? new JPanel(new BorderLayout()) : new JPanel(new MigLayout("wrap", "[]unrel[grow]", "[grow][pref!]")); 
 
    private JTabbedPane layoutPickerTabPane = new JTabbedPane(); 
        private JList pickerList = new JList(new DefaultListModel()); 
 
    private JTabbedPane southTabPane = new JTabbedPane(); 
        private JScrollPane descrTextAreaScroll = createTextAreaScroll("", 5, 80, true); 
        private JTextArea descrTextArea = (JTextArea) descrTextAreaScroll.getViewport().getView(); 
 
        private JScrollPane sourceTextAreaScroll = null; 
        private JTextArea sourceTextArea = null; 
 
    private JPanel layoutDisplayPanel = new JPanel(new BorderLayout(0, 0)); 
    private static boolean buttonOpaque = true; 
    private JFrame sourceFrame = null; 
    private JTextArea sourceFrameTextArea = null; 
 
    private static int benchRuns = 0; 
    private static long startupMillis = 0; 
    private static long timeToShowMillis = 0; 
    private static long benchRunTime = 0; 
    private static String benchOutFileName = null; 
    private static boolean append = false; 
    private static long lastRunTimeStart = 0; 
    private static StringBuffer runTimeSB = null; 
 
    public static void main(String args[]) 
    { 
//      try { 
//          System.setProperty("apple.laf.useScreenMenuBar", "true"); 
//          System.setProperty("com.apple.macos.useScreenMenuB ar", "true"); 
//          System.setProperty("apple.awt.brushMetalRounded", "true"); 
//          System.setProperty("apple.laf.useScreenMenuBar", "true"); 
//          System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Viewer"); 
//      } catch(Throwable ex) { 
//          // Beacuse we did not have permissions. 
//      } 
 
      //  LayoutUtil.setStoreConstraintData(true); 
 
        startupMillis = System.currentTimeMillis(); 
 
        String laf = UIManager.getSystemLookAndFeelClassName(); 
 
        if (args.length > 0) { 
            for (int i = 0; i <args.length; i++) { 
                String arg = args[i].trim(); 
                if (arg.startsWith("-laf")) { 
                    laf = arg.substring(4); 
                } else if (arg.startsWith("-bench")) { 
                    benchRuns = 10; 
                    try { 
                        benchRuns = Integer.parseInt(arg.substring(6)); 
                    } catch(Exception ex) {} 
                } else if (arg.startsWith("-bout")) { 
                    benchOutFileName = arg.substring(5); 
                } else if (arg.startsWith("-append")) { 
                    append = true; 
                } else if (arg.startsWith("-verbose")) { 
                    runTimeSB = new StringBuffer(256); 
                } else if (arg.equals("-steel")) { 
                    laf = "javax.swing.plaf.metal.MetalLookAndFeel"; 
                    System.setProperty("swing.metalTheme", "steel"); 
                } else if (arg.equals("-ocean")) { 
                    laf = "javax.swing.plaf.metal.MetalLookAndFeel"; 
                } else { 
                    System.out.println("Usage: [-laf[look_&_feel_class_name]] [-bench[#_of_runs]] [-bout[benchmark_results_filename]] [-append] [-steel] [-ocean]\n" + 
                                       " -laf    Set the Application Look&Feel. (Look and feel must be in Classpath)\n" + 
                                       " -bench  Run demo as benchmark. Run count can be appended. 10 is default.\n" + 
                                       " -bout   Benchmark results output filename.\n" + 
                                       " -append Appends the result to the -bout file.\n" + 
                                       " -verbose Print the times of every run.\n" + 
                                       " -steel  Sets the old Steel theme for Sun's Metal look&feel.\n" + 
                                       " -ocean  Sets the Ocean theme for Sun's Metal look&feel.\n" + 
                                       "\nExamples:\n" + 
                                       " java -jar swingdemoapp.jar -bench -boutC:/bench.txt -append\n" + 
                                       " java -jar swingdemoapp.jar -ocean -bench20\n" + 
                                       " java -cp c:\\looks-2.0.4.jar;.\\swingdemoapp.jar net.miginfocom.demo.SwingDemo -lafcom.jgoodies.looks.plastic.PlasticLookAndFeel -bench20 -boutC:/bench.txt"); 
                    System.exit(0); 
                } 
            } 
        } 
 
//      laf = "net.sourceforge.napkinlaf.NapkinLookAndFeel"; 
//      laf = "javax.swing.plaf.metal.MetalLookAndFeel"; 
//      System.setProperty("swing.metalTheme", "steel"); 
//      laf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"; 
//      laf = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"; 
//      laf = "com.jgoodies.looks.plastic.PlasticLookAndFeel"; 
//      laf = "com.jgoodies.looks.plastic.Plastic3DLookAndFeel"; 
//      laf = "com.jgoodies.looks.plastic.PlasticXPLookAndFeel"; 
//      laf = "com.jgoodies.looks.windows.WindowsLookAndFeel"; 
 
        if (laf.endsWith("WindowsLookAndFeel")) 
            buttonOpaque = false; 
 
        try { 
            UIManager.setLookAndFeel(laf); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        } 
 
        SwingUtilities.invokeLater(new Runnable() { 
            public void run() { 
                SwingDemo demo = new SwingDemo(); 
//              if (benchRuns == 0) { 
//                  JOptionPane.showOptionDialog(demo, "Note that you can right click any Component or Container to view and change its constraint!", "Important!", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {"OK, Right Click Stuff I Will"}, null); 
//              } 
            } 
        }); 
    } 
 
    public SwingDemo() 
    { 
        super("MigLayout Swing Demo v2.1 - Mig Layout v" + LayoutUtil.getVersion()); 
 
//      if (benchRuns > 0) 
//          RepaintManager.currentManager(this).setDoubleBufferingEnabled(false); 
 
        if (benchRuns == 0) { 
            sourceTextAreaScroll = createTextAreaScroll("", 5, 80, true); 
            sourceTextArea = (JTextArea) sourceTextAreaScroll.getViewport().getView(); 
        } 
 
        if (DEBUG) { 
            contentPanel.add(layoutDisplayPanel, BorderLayout.CENTER); 
//          contentPanel.add(layoutPickerTabPane, BorderLayout.WEST); 
//          contentPanel.add(descriptionTabPane, BorderLayout.SOUTH); 
        } else { 
            contentPanel.add(layoutPickerTabPane, "spany,grow"); 
            contentPanel.add(layoutDisplayPanel, "grow"); 
            contentPanel.add(southTabPane, "growx"); 
        } 
 
//      contentPanel.setPreferredSize(new Dimension(750, 550)); 
 
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
                    if (e.getClickCount() == 2) 
                        showSourceInFrame(); 
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
                    JComponent panel = (JComponent) SwingDemo.class.getMethod(methodName, new Class[0]).invoke(SwingDemo.this, new Object[0]); 
                    layoutDisplayPanel.add(panel); 
                    descrTextArea.setText(panels[ix][1]); 
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
        } else { 
            pickerList.setSelectedIndex(INITIAL_INDEX); 
        } 
    } 
 
   
    private void setSource(String source) 
    { 
        if (benchRuns > 0 || sourceTextArea == null) 
            return; 
 
        if (source.length() > 0) { 
            source = source.replaceAll("\t\t", ""); 
            source = "DOUBLE CLICK TAB TO SHOW SOURCE IN SEPARATE WINDOW!\n===================================================\n\n" + source; 
        } 
        sourceTextArea.setText(source); 
        sourceTextArea.setCaretPosition(0); 
 
        if (sourceFrame != null && sourceFrame.isVisible()) { 
            sourceFrameTextArea.setText(source.substring(105)); 
            sourceFrameTextArea.setCaretPosition(0); 
        } 
    } 
 
    private void showSourceInFrame() 
    { 
       
    } 
 

 
    public JComponent createLayout_Showdown() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createWelcome() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createVisual_Bounds() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createDocking() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createAbsolute_Position() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createComponent_Links() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createFlow_Direction() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JPanel createFlowPanel(String gridFlow, String cellFlow) 
    { 
        MigLayout lm = new MigLayout("center, wrap 3," + gridFlow, 
                                     "[110,fill]", 
                                     "[110,fill]"); 
        JPanel panel = createTabPanel(lm); 
        return panel; 
    } 
 
    public JComponent createDebug() 
    { 
        return createPlainImpl(true); 
    } 
 
    public JComponent createButton_Bars() 
    { 
        MigLayout lm = new MigLayout("ins 0 0 15lp 0", 
                                          "[grow]", 
                                          "[grow][baseline,nogrid]"); 
 
        final JPanel mainPanel = new JPanel(lm); 
        return mainPanel; 
    } 
 
    private JComponent createButtonBarsPanel(String helpTag, boolean sizeLocked) 
    { 
        MigLayout lm = new MigLayout("nogrid, fillx, aligny 100%, gapy unrel"); 
        JPanel panel = createTabPanel(lm); 
 
        // Notice that the order in the rows below does not matter... 
        String[][] buttons = new String[][] { 
                {"No", "Yes"}, 
                {"Help", "Close"}, 
                {"OK", "Help"}, 
                {"OK", "Cancel", "Help"}, 
                {"OK", "Cancel", "Apply", "Help"}, 
                {"No", "Yes", "Cancel"}, 
                {"Help", "< Back", "Forward >", "Cancel"}, 
                {"Print...", "Cancel", "Help"}, 
        }; 
 
        for (int r = 0; r < buttons.length; r++) { 
            for (int i = 0; i < buttons[r].length; i++) { 
                String txt = buttons[r][i]; 
                String tag = txt; 
 
                if (txt.equals("Help")) { 
                    tag = helpTag; 
                } else if (txt.equals("< Back")) { 
                    tag = "back"; 
                } else if (txt.equals("Close")) { 
                    tag = "cancel"; 
                } else if (txt.equals("Forward >")) { 
                    tag = "next"; 
                } else if (txt.equals("Print...")) { 
                    tag = "other"; 
                } 
                String wrap = (i == buttons[r].length - 1) ? ",wrap" : ""; 
                String sizeGroup = sizeLocked ? ("sgx " + r + ",") : ""; 
                panel.add(createButton(txt), sizeGroup + "tag " + tag + wrap); 
            } 
        } 
        return panel; 
    } 
 
    public JComponent createOrientation() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createPlain() 
    { 
        return createPlainImpl(false); 
    } 
 
    private JComponent createPlainImpl(boolean debug) 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
 
        MigLayout lm = new MigLayout((debug && benchRuns == 0 ? "debug" : ""), "[r][100lp, fill][60lp][95lp, fill]", ""); 
        JPanel panel = createTabPanel(lm); 
 
        addSeparator(panel, "Manufacturer"); 
        panel.add(createLabel("Company")); 
        panel.add(createTextField(""), "span,growx"); 
        panel.add(createLabel("Contact")); 
        panel.add(createTextField(""), "span,growx"); 
        panel.add(createLabel("Order No")); 
        panel.add(createTextField(15), "wrap"); 
 
        addSeparator(panel, "Inspector"); 
        panel.add(createLabel("Name")); 
        panel.add(createTextField(""), "span,growx"); 
        panel.add(createLabel("Reference No")); 
        panel.add(createTextField(""), "wrap"); 
        panel.add(createLabel("Status")); 
        panel.add(createCombo(new String[] {"In Progress", "Finnished", "Released"}), "wrap"); 
 
        addSeparator(panel, "Ship"); 
        panel.add(createLabel("Shipyard")); 
        panel.add(createTextField(""), "span,growx"); 
        panel.add(createLabel("Register No")); 
        panel.add(createTextField("")); 
        panel.add(createLabel("Hull No"), "right"); 
        panel.add(createTextField(15), "wrap"); 
        panel.add(createLabel("Project Type")); 
        panel.add(createCombo(new String[] {"New Building", "Convention", "Repair"})); 
 
        if (debug) 
            panel.add(createLabel("Red is cell bounds. Blue is component bounds."), "newline,ax left,span,gaptop 40,"); 
 
        tabbedPane.addTab("Plain", panel); 
 
        // Disregard. Just forgetting the source code text close to the source code. 
        setSource("JTabbedPane tabbedPane = new JTabbedPane();\n" + 
                  "\n" + 
                  "MigLayout lm = new MigLayout((debug ? \"debug\" : \"\"), \"[r][100lp, fill][60lp][95lp, fill]\", \"\");\n" + 
                  "JPanel panel = createTabPanel(lm);\n" + 
                  "\n" + 
                  "addSeparator(panel, \"Manufacturer\");\n" + 
                  "panel.add(createLabel(\"Company\"));\n" + 
                  "panel.add(createTextField(\"\"), \"span,growx\");\n" + 
                  "panel.add(createLabel(\"Contact\"));\n" + 
                  "panel.add(createTextField(\"\"), \"span,growx\");\n" + 
                  "panel.add(createLabel(\"Order No\"));\n" + 
                  "panel.add(createTextField(15), \"wrap\");\n" + 
                  "\n" + 
                  "addSeparator(panel, \"Inspector\");\n" + 
                  "panel.add(createLabel(\"Name\"));\n" + 
                  "panel.add(createTextField(\"\"), \"span,growx\");\n" + 
                  "panel.add(createLabel(\"Reference No\"));\n" + 
                  "panel.add(createTextField(\"\"), \"wrap\");\n" + 
                  "panel.add(createLabel(\"Status\"));\n" + 
                  "panel.add(new JComboBox(new String[] {\"In Progress\", \"Finnished\", \"Released\"}), \"wrap\");\n" + 
                  "\n" + 
                  "addSeparator(panel, \"Ship\");\n" + 
                  "panel.add(createLabel(\"Shipyard\"));\n" + 
                  "panel.add(createTextField(\"\"), \"span,growx\");\n" + 
                  "panel.add(createLabel(\"Register No\"));\n" + 
                  "panel.add(createTextField(\"\"));\n" + 
                  "panel.add(createLabel(\"Hull No\"), \"right\");\n" + 
                  "panel.add(createTextField(15), \"wrap\");\n" + 
                  "panel.add(createLabel(\"Project Type\"));\n" + 
                  "panel.add(new JComboBox(new String[] {\"New Building\", \"Convention\", \"Repair\"}));\n" + 
                  "\n" + 
                  "if (debug)\n" + 
                  "\tpanel.add(createLabel(\"Red is cell bounds. Blue is component bounds.\"), \"newline,ax left,span,gaptop 40,\");\n" + 
                  "\n" + 
                  "tabbedPane.addTab(\"Plain\", panel);"); 
 
        return tabbedPane; 
    } 
 
    public JComponent createBound_Sizes() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createComponent_Sizes() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createCell_Alignments() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createUnits() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createGrouping() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createSpan() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createGrowing() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createBasic_Sizes() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createAlignments() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createQuick_Start() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
    public JComponent createGrow_Shrink() 
    { 
        JTabbedPane tabbedPane = new JTabbedPane(); 
        return tabbedPane; 
    } 
 
   
    // ********************************************************** 
    // * Helper Methods 
    // ********************************************************** 
 
    private final ToolTipListener toolTipListener = new ToolTipListener(); 
    private final ConstraintListener constraintListener = new ConstraintListener(); 
 
    private JLabel createLabel(String text) 
    { 
        return createLabel(text, SwingConstants.LEADING); 
    } 
 
    private JLabel createLabel(String text, int align) 
    { 
        final JLabel b = new JLabel(text, align); 
        configureActiveComponet(b); 
        return b; 
    } 
 
    public JComboBox createCombo(String[] items) 
    { 
        JComboBox combo = new JComboBox(items); 
 
        if (PlatformDefaults.getCurrentPlatform() == PlatformDefaults.MAC_OSX) 
            combo.setOpaque(false); 
 
        return combo; 
    } 
 
    private JTextField createTextField(int cols) 
    { 
        return createTextField("", cols); 
    } 
 
    private JTextField createTextField(String text) 
    { 
        return createTextField(text, 0); 
    } 
 
    private JTextField createTextField(String text, int cols) 
    { 
        final JTextField b = new JTextField(text, cols); 
 
        configureActiveComponet(b); 
 
        return b; 
    } 
 
    private static final Font BUTT_FONT = new Font("monospaced", Font.PLAIN, 12); 
    private JButton createButton() 
    { 
        return createButton(""); 
    } 
 
    private JButton createButton(String text) 
    { 
        return createButton(text, false); 
    } 
 
    private JButton createButton(String text, boolean bold) 
    { 
        JButton b = new JButton(text) { 
            public void addNotify() 
            { 
                super.addNotify(); 
                if (benchRuns == 0) {   // Since this does not exist in the SWT version 
                    if (getText().length() == 0) { 
                        String lText = (String) ((MigLayout) getParent().getLayout()).getComponentConstraints(this); 
                        setText(lText.length() > 0 ? lText : "<Empty>"); 
                    } 
                } else { 
                    setText("Benchmark Version"); 
                } 
            } 
        }; 
 
        if (bold) 
            b.setFont(b.getFont().deriveFont(Font.BOLD)); 
 
        configureActiveComponet(b); 
 
        b.setOpaque(buttonOpaque); // Or window's buttons will have strange border 
 
        if (PlatformDefaults.getCurrentPlatform() == PlatformDefaults.MAC_OSX) 
            b.setContentAreaFilled(false); 
 
        return b; 
    } 
 
    private JToggleButton createToggleButton(String text) 
    { 
        JToggleButton b = new JToggleButton(text); 
//      configureActiveComponet(b); 
        b.setOpaque(OPAQUE); // Or window's buttons will have strange border 
        return b; 
    } 
 
    private JCheckBox createCheck(String text) 
    { 
        JCheckBox b = new JCheckBox(text); 
 
        configureActiveComponet(b); 
 
        b.setOpaque(OPAQUE); // Or window's checkboxes will have strange border 
        return b; 
    } 
 
    private JPanel createTabPanel(LayoutManager lm) 
    { 
        JPanel panel = new JPanel(lm); 
        configureActiveComponet(panel); 
        panel.setOpaque(OPAQUE); 
        return panel; 
    } 
 
    private JPanel createPanel() 
    { 
        return createPanel(""); 
    } 
 
    private JPanel createPanel(String s) 
    { 
        JPanel panel = new JPanel(new MigLayout("fill")) { 
            public void addNotify() 
            { 
                super.addNotify(); 
                if (benchRuns == 0) {   // Since this does not exist in the SWT version 
                    JLabel label = (JLabel) getComponent(0); 
                    if (label.getText().length() == 0) { 
                        String lText = (String) ((MigLayout) getParent().getLayout()).getComponentConstraints(this); 
                        label.setText(lText.length() > 0 ? lText : "<Empty>"); 
                    } 
                } 
            } 
        }; 
        panel.setBorder(new EtchedBorder()); 
        JLabel label = createLabel(s, SwingConstants.CENTER); 
 
        panel.add(label, "grow"); 
        configureActiveComponet(panel); 
 
//      panel.setOpaque(OPAQUE); 
        return panel; 
    } 
 
    private JTextArea createTextArea(String text, int rows, int cols) 
    { 
        JTextArea ta = new JTextArea(text, rows, cols); 
        ta.setBorder(UIManager.getBorder("TextField.border")); 
        ta.setFont(UIManager.getFont("TextField.font")); 
        ta.setWrapStyleWord(true); 
        ta.setLineWrap(true); 
 
        configureActiveComponet(ta); 
 
        return ta; 
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
 
    private JComponent configureActiveComponet(JComponent c) 
    { 
        if (benchRuns == 0) { 
            c.addMouseMotionListener(toolTipListener); 
            c.addMouseListener(constraintListener); 
        } 
        return c; 
    } 
 
    static final Color LABEL_COLOR = new Color(0, 70, 213); 
    private void addSeparator(JPanel panel, String text) 
    { 
        JLabel l = createLabel(text); 
        l.setForeground(LABEL_COLOR); 
 
        panel.add(l, "gapbottom 1, span, split 2"); 
        panel.add(configureActiveComponet(new JSeparator()), "gapleft rel, growx"); 
    } 
 
    private class ConstraintListener extends MouseAdapter 
    { 
        public void mousePressed(MouseEvent e) 
        { 
            if (e.isPopupTrigger()) 
                react(e); 
        } 
 
        public void mouseReleased(MouseEvent e) 
        { 
            if (e.isPopupTrigger()) 
                react(e); 
        } 
 
        public void react(MouseEvent e) 
        { 
            JComponent c = (JComponent) e.getSource(); 
            LayoutManager lm = c.getParent().getLayout(); 
            if (lm instanceof MigLayout == false) 
                lm = c.getLayout(); 
 
            if (lm instanceof MigLayout) { 
                MigLayout layout = (MigLayout) lm; 
                boolean isComp = layout.isManagingComponent(c); 
                String compConstr = isComp ? (String) layout.getComponentConstraints(c) : null; 
 
                String rowsConstr = isComp ? null : (String) layout.getRowConstraints(); 
                String colsConstr = isComp ? null : (String) layout.getColumnConstraints(); 
                String layoutConstr = isComp ? null : (String) layout.getLayoutConstraints(); 
 
                ConstraintsDialog cDlg = new ConstraintsDialog(SwingDemo.this, layoutConstr, rowsConstr, colsConstr, compConstr); 
                cDlg.pack(); 
                cDlg.setLocationRelativeTo(c); 
 
                if (cDlg.showDialog()) { 
                    try { 
                        if (isComp) { 
                            String constrStr = cDlg.componentConstrTF.getText().trim(); 
                            layout.setComponentConstraints(c, constrStr); 
                            if (c instanceof JButton) { 
                                c.setFont(BUTT_FONT); 
                                ((JButton) c).setText(constrStr.length() == 0 ? "<Empty>" : constrStr); 
                            } 
                        } else { 
                            layout.setLayoutConstraints(cDlg.layoutConstrTF.getText()); 
                            layout.setRowConstraints(cDlg.rowsConstrTF.getText()); 
                            layout.setColumnConstraints(cDlg.colsConstrTF.getText()); 
                        } 
                    } catch(Exception ex) { 
                        StringWriter sw = new StringWriter(); 
                        ex.printStackTrace(new PrintWriter(sw)); 
                        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(c), sw.toString(), "Error parsing Constraint!", JOptionPane.ERROR_MESSAGE); 
                        return; 
                    } 
 
                    c.invalidate(); 
                    c.getParent().validate(); 
                } 
            } 
        } 
    } 
 
    private static class ToolTipListener extends MouseMotionAdapter 
    { 
        public void mouseMoved(MouseEvent e) 
        { 
            JComponent c = (JComponent) e.getSource(); 
            LayoutManager lm = c.getParent().getLayout(); 
            if (lm instanceof MigLayout) { 
                String constr = (String) ((MigLayout) lm).getComponentConstraints(c); 
                c.setToolTipText((constr != null ? ("\"" + constr + "\"") : "null")); 
            } 
        } 
    } 
 
    private static class ConstraintsDialog extends JDialog implements ActionListener, KeyEventDispatcher 
    { 
        private static final Color ERROR_COLOR = new Color(255, 180, 180); 
        private final JPanel mainPanel = new JPanel(new MigLayout("fillx,flowy,ins dialog,debug", 
                                                                  "[fill]", 
                                                                  "2[]2")); 
        final JTextField layoutConstrTF; 
        final JTextField rowsConstrTF; 
        final JTextField colsConstrTF; 
        final JTextField componentConstrTF; 
 
        private final JButton okButt = new JButton("OK"); 
        private final JButton cancelButt = new JButton("Cancel"); 
 
        private boolean okPressed = false; 
 
        public ConstraintsDialog(Frame owner, String layoutConstr, String rowsConstr, String colsConstr, String compConstr) 
        { 
            super(owner, (compConstr != null ? "Edit Component Constraints" : "Edit Container Constraints"), true); 
 
            layoutConstrTF = createConstraintField(layoutConstr); 
            rowsConstrTF = createConstraintField(rowsConstr); 
            colsConstrTF = createConstraintField(colsConstr); 
            componentConstrTF = createConstraintField(compConstr); 
 
            if (componentConstrTF != null) { 
                mainPanel.add(new JLabel("Component Constraints")); 
                mainPanel.add(componentConstrTF); 
            } 
 
            if (layoutConstrTF != null) { 
                mainPanel.add(new JLabel("Layout Constraints")); 
                mainPanel.add(layoutConstrTF); 
            } 
 
            if (colsConstrTF != null) { 
                mainPanel.add(new JLabel("Column Constraints"), "gaptop unrel"); 
                mainPanel.add(colsConstrTF); 
            } 
 
            if (rowsConstrTF != null) { 
                mainPanel.add(new JLabel("Row Constraints"), "gaptop unrel"); 
                mainPanel.add(rowsConstrTF); 
            } 
 
            mainPanel.add(okButt, "tag ok,split,flowx,gaptop 15"); 
            mainPanel.add(cancelButt, "tag cancel,gaptop 15"); 
 
            setContentPane(mainPanel); 
 
            okButt.addActionListener(this); 
            cancelButt.addActionListener(this); 
        } 
 
        public void addNotify() 
        { 
            super.addNotify(); 
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this); 
        } 
 
        public void removeNotify() 
        { 
            KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this); 
            super.removeNotify(); 
        } 
 
        public boolean dispatchKeyEvent(KeyEvent e) 
        { 
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) 
                dispose(); 
            return false; 
        } 
 
        public void actionPerformed(ActionEvent e) 
        { 
            if (e.getSource() == okButt) 
                okPressed = true; 
            dispose(); 
        } 
 
        private JTextField createConstraintField(String text) 
        { 
            if (text == null) 
                return null; 
 
            final JTextField tf = new JTextField(text, 50); 
            tf.setFont(new Font("monospaced", Font.PLAIN, 12)); 
 
            tf.addKeyListener(new KeyAdapter() { 
                public void keyPressed(KeyEvent e) 
                { 
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
                        okButt.doClick(); 
                        return; 
                    } 
 
                    javax.swing.Timer timer = new Timer(50, new ActionListener() { 
                        public void actionPerformed(ActionEvent e) 
                        { 
                            String constr = tf.getText(); 
                            try { 
                                if (tf == layoutConstrTF) { 
                                    ConstraintParser.parseLayoutConstraint(constr); 
                                } else if (tf == rowsConstrTF) { 
                                    ConstraintParser.parseRowConstraints(constr); 
                                } else if (tf == colsConstrTF) { 
                                    ConstraintParser.parseColumnConstraints(constr); 
                                } else if (tf == componentConstrTF) { 
                                    ConstraintParser.parseComponentConstraint(constr); 
                                } 
 
                                tf.setBackground(Color.WHITE); 
                                okButt.setEnabled(true); 
                            } catch(Exception ex) { 
                                tf.setBackground(ERROR_COLOR); 
                                okButt.setEnabled(false); 
                            } 
                        } 
                    }); 
                    timer.setRepeats(false); 
                    timer.start(); 
                } 
            }); 
 
            return tf; 
        } 
 
        private boolean showDialog() 
        { 
            setVisible(true); 
            return okPressed; 
        } 
    } 
 
}