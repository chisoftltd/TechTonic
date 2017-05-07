/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techtonic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import javax.swing.event.CellEditorListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jwitsml.Value;
import org.jwitsml.WitsmlLogCurve;
import org.jwitsml.WitsmlTrajectoryStation;
import org.jwitsml.WitsmlWell;
import org.jwitsml.WitsmlWellbore;

/**
 *
 * @author 1613112
 */
public class Onview extends JFrame{
    static private JFreeChart chart;
    static private String Orientation;
    static private String xLabel;
    static private String yLabel;
    static private String title;
    private static List<WitsmlTrajectoryStation> currentStation; 
    private static String trajName="Trajectory"; 
    private static List<WitsmlLogCurve> currentCurves;
    private static boolean plotted = false;
    
    static private Color bgColor;
    static private Color foreColor;
    static private String fileFormat = "jpg";
    static private String defaultDirectory = "H:\\Desktop\\files";
    static private String fileName = "Brude";
    static private int eWidth = 800;
    static private int eHeight = 600;
    
    static private List<WitsmlTrajectoryStation> currentWitsmlTrajectoryStation;
    
//    public static void setTrajName(String name){
//        Onview.setTrajName(name);
//    }
    
    public static String getFileFormat() {
        return fileFormat;
    }
    public static void setCurrentCurves(List<WitsmlLogCurve> currentCurves){
        Onview.currentCurves = currentCurves;
    }
    public static void setCurrentStations(List<WitsmlTrajectoryStation> stationsAsList){
        Onview.currentStation = stationsAsList;
    }
    public static void setFileFormat(String fileFormat) {
        Onview.fileFormat = fileFormat;
    }

    public static String getDefaultDirectory() {
        return defaultDirectory;
    }

    public static void setDefaultDirectory(String defaultDirectory) {
        Onview.defaultDirectory = defaultDirectory;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        Onview.fileName = fileName;
    }

    public static int geteWidth() {
        return eWidth;
    }

    public static void seteWidth(int eWidth) {
        Onview.eWidth = eWidth;
    }

    public static int geteHeight() {
        return eHeight;
    }

    public static void seteHeight(int eHeight) {
        Onview.eHeight = eHeight;
    }
    public static int getjcbX_Axis() {
        return eHeight;
    }

    public static void setjcbX_Axis(Vector<String> s) {
        Onview.jcbX_Axis.setModel(new DefaultComboBoxModel(s));
    }
    public static int getjcbY_Axis() {
        return eHeight;
    }

    public static void setjcbY_Axis(Vector<String> s) {
        Onview.jcbY_Axis.setModel(new DefaultComboBoxModel(s));
    }
    public static void setEnablejcbX_Axis(boolean flag) {
        Onview.jcbX_Axis.setEnabled(flag);
    }
    public static void setEnablejcbY_Axis(boolean flag) {
        Onview.jcbY_Axis.setEnabled(flag);
    }
    public static void setEnableRenderBtn(boolean f){
      Onview.btnRender.setEnabled(f);
    }
    public static JFreeChart getChart() {
        return chart;
    }

    public static String getOrientation() {
        return Orientation;
    }
    private WitsmlWell currentWell;

    public DefaultComboBoxModel<String> getaModel() {
        return aModel;
    }

    public Vector<String> getArrName() {
        return arrName;
    }

    public static void setChart(JFreeChart ch) {
        Onview.chart = ch;
    }

    public static void setOrientation(String Orientation) {
        Onview.Orientation = Orientation;
    }

    public static void setxLabel(String xLabel) {
        Onview.xLabel = xLabel;
    }

    public static void setyLabel(String yLabel) {
        Onview.yLabel = yLabel;
    }

    public static void setBgColor(Color bgColor) {
        Onview.bgColor = bgColor;
    }

    public static void setForeColor(Color foreColor) {
        Onview.foreColor = foreColor;
    }

    public static void setcurrentWitsmlTrajectoryStation(List<WitsmlTrajectoryStation> stations) {
        currentWitsmlTrajectoryStation = stations;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Onview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Onview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Onview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Onview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Onview().setVisible(true);
            }
        });
    }

    /**
     * Creates new form Onview
     */
    public Onview() {
        initComponents();
        loadDisplayScreen();
        jcbX_Axis.setModel(new DefaultComboBoxModel(new String[]{}));
        jcbX_Axis.setEnabled(false);
        jcbY_Axis.setModel(new DefaultComboBoxModel(new String[]{}));
        jcbY_Axis.setEnabled(false);
        setResizable(false);
    }
    public static void setDisplayArea(ChartPanel cp){
            displayArea1.removeAll();

           
            displayArea1.setLayout(new java.awt.BorderLayout());
            
            cp.getChart().getXYPlot().getRangeAxis().setInverted(true);
            displayArea1.add(cp,BorderLayout.CENTER);
            
            displayArea1.validate();
            displayArea1.repaint();
    
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">    
    
     private void initComponents() {

        titleBarPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        toolBarPanel = new javax.swing.JPanel();
        axisPanel = new javax.swing.JPanel();
        jcbX_Axis = new javax.swing.JComboBox<>();
        jcbY_Axis = new javax.swing.JComboBox<>();
        lblXAxis = new javax.swing.JLabel();
        lblYAxis = new javax.swing.JLabel();
        btnRender = new javax.swing.JButton();
        utilityPanel = new javax.swing.JPanel();
        maxBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        exportBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        wellCombo = new javax.swing.JComboBox<>();
        jspWell = new javax.swing.JScrollPane();
        btnWellPanel = new javax.swing.JPanel();
        jspProperty = new javax.swing.JScrollPane();
        tblProperty = new javax.swing.JTable();
        statusBar = new javax.swing.JLabel();
        btnSetProperties = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        trajectoryPanel = new javax.swing.JPanel();
        logsPanel = new javax.swing.JPanel();
        displayArea1 = new javax.swing.JPanel();
        menBarTectTonic = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiNew = new javax.swing.JMenuItem();
        jmiLoad = new javax.swing.JMenuItem();
        jmiSave = new javax.swing.JMenuItem();
        jmiExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Well Data Processor -TechTonic");
        setName("TechTonic"); // NOI18N
        setPreferredSize(new java.awt.Dimension(100, 200));
        setSize(new java.awt.Dimension(1000, 1500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleBarPanel.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techtonic/images/rlogo1.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout titleBarPanelLayout = new javax.swing.GroupLayout(titleBarPanel);
        titleBarPanel.setLayout(titleBarPanelLayout);
        titleBarPanelLayout.setHorizontalGroup(
            titleBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleBarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(1321, Short.MAX_VALUE))
        );
        titleBarPanelLayout.setVerticalGroup(
            titleBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleBarPanelLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(20, 20, 20))
        );

        getContentPane().add(titleBarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 1620, 80));

        toolBarPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        toolBarPanel.setPreferredSize(new java.awt.Dimension(100, 65));

        axisPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Plot", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        axisPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        axisPanel.setMinimumSize(new java.awt.Dimension(100, 100));
        axisPanel.setPreferredSize(new java.awt.Dimension(699, 99));

        jcbX_Axis.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbX_Axis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbX_Axis.setBorder(null);
        jcbX_Axis.setMinimumSize(new java.awt.Dimension(69, 20));
        jcbX_Axis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbX_AxisItemStateChanged(evt);
            }
        });
        jcbX_Axis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbX_AxisActionPerformed(evt);
            }
        });

        jcbY_Axis.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbY_Axis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbY_Axis.setBorder(null);
        jcbY_Axis.setMinimumSize(new java.awt.Dimension(69, 20));
        jcbY_Axis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbY_AxisActionPerformed(evt);
            }
        });

        lblXAxis.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblXAxis.setText("X-Axis");

        lblYAxis.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblYAxis.setText("Y-Axis");

        btnRender.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRender.setText("Render");
        btnRender.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRender.setEnabled(false);
        btnRender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout axisPanelLayout = new javax.swing.GroupLayout(axisPanel);
        axisPanel.setLayout(axisPanelLayout);
        axisPanelLayout.setHorizontalGroup(
            axisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(axisPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblXAxis)
                .addGap(18, 18, 18)
                .addComponent(jcbX_Axis, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYAxis, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbY_Axis, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRender, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        axisPanelLayout.setVerticalGroup(
            axisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(axisPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(axisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblXAxis, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbX_Axis, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblYAxis)
                    .addComponent(jcbY_Axis, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRender, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        utilityPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Uitility", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        maxBtn.setIcon(new javax.swing.ImageIcon("H:\\NetBeansProjects\\CMM021-Project-Team\\src\\techtonic\\images\\max.png")); // NOI18N
        maxBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        maxBtn.setEnabled(false);
        maxBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxBtnActionPerformed(evt);
            }
        });

        saveBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        saveBtn.setText("Save");
        saveBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        saveBtn.setEnabled(false);
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        exportBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        exportBtn.setText("Export");
        exportBtn.setEnabled(false);
        exportBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        exportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout utilityPanelLayout = new javax.swing.GroupLayout(utilityPanel);
        utilityPanel.setLayout(utilityPanelLayout);
        utilityPanelLayout.setHorizontalGroup(
            utilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, utilityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maxBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(exportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addContainerGap())
        );
        utilityPanelLayout.setVerticalGroup(
            utilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(utilityPanelLayout.createSequentialGroup()
                .addGroup(utilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maxBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Wells", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        wellCombo.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        wellCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        wellCombo.setBorder(null);
        wellCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wellComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(wellCombo, 0, 404, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(wellCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout toolBarPanelLayout = new javax.swing.GroupLayout(toolBarPanel);
        toolBarPanel.setLayout(toolBarPanelLayout);
        toolBarPanelLayout.setHorizontalGroup(
            toolBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolBarPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(axisPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(utilityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        toolBarPanelLayout.setVerticalGroup(
            toolBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolBarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(toolBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(utilityPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(toolBarPanelLayout.createSequentialGroup()
                        .addGroup(toolBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(axisPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(toolBarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 101, 1620, 120));

        jspWell.setPreferredSize(new java.awt.Dimension(1173, 2263));

        btnWellPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnWellPanel.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout btnWellPanelLayout = new javax.swing.GroupLayout(btnWellPanel);
        btnWellPanel.setLayout(btnWellPanelLayout);
        btnWellPanelLayout.setHorizontalGroup(
            btnWellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );
        btnWellPanelLayout.setVerticalGroup(
            btnWellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 564, Short.MAX_VALUE)
        );

        jspWell.setViewportView(btnWellPanel);

        getContentPane().add(jspWell, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 220, 570));

        jspProperty.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jspProperty.setPreferredSize(new java.awt.Dimension(1173, 2263));
        ColorSelectionModel bgColorModel = new DefaultColorSelectionModel(Color.LIGHT_GRAY);
        ColorSelectionModel fgColorModel = new DefaultColorSelectionModel(Color.WHITE);
        TableButton t = new TableButton("hello");
        tblProperty.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblProperty.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Background Colour", "color 1"},
                {"Fore ground Colour" , "color 2"},
                {"Shape",  t},
                {"Shape Size" },
                {"Label Colour"},
                {null, null},
          
            },
            new String [] {
                "Properties", "Values"
            }
        ));

        ///// table.getModel().getValueAt(row_index, col_index)
        TableModel tm = tblProperty.getModel();
        TableColumn ce = tblProperty.getColumnModel().getColumn(1);
   
       
        
        
       // System.out.println(tm.getValueAt(1, 1));
       
      //  ce.

        jspProperty.setViewportView(tblProperty);
        if (tblProperty.getColumnModel().getColumnCount() > 0) {
            tblProperty.getColumnModel().getColumn(0).setResizable(false);
            tblProperty.getColumnModel().getColumn(1).setResizable(false);
        }

        getContentPane().add(jspProperty, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 240, 290, 250));

        statusBar.setForeground(new java.awt.Color(0, 51, 204));
        statusBar.setText(" ");
        statusBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        getContentPane().add(statusBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 830, 1620, -1));

        btnSetProperties.setText("Set Properties");
        getContentPane().add(btnSetProperties, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 510, 290, 60));
        btnSetProperties.setEnabled(false);
        btnSetProperties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               btnSetPropery();
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        trajectoryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Trajectory"));

        javax.swing.GroupLayout trajectoryPanelLayout = new javax.swing.GroupLayout(trajectoryPanel);
        trajectoryPanel.setLayout(trajectoryPanelLayout);
        trajectoryPanelLayout.setHorizontalGroup(
            trajectoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        trajectoryPanelLayout.setVerticalGroup(
            trajectoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        logsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Logs"));

        javax.swing.GroupLayout logsPanelLayout = new javax.swing.GroupLayout(logsPanel);
        logsPanel.setLayout(logsPanelLayout);
        logsPanelLayout.setHorizontalGroup(
            logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );
        logsPanelLayout.setVerticalGroup(
            logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trajectoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(trajectoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 240, 230, 570));

        javax.swing.GroupLayout displayArea1Layout = new javax.swing.GroupLayout(displayArea1);
        displayArea1.setLayout(displayArea1Layout);
        displayArea1Layout.setHorizontalGroup(
            displayArea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        displayArea1Layout.setVerticalGroup(
            displayArea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );
         displayArea1.setBorder(javax.swing.BorderFactory.createTitledBorder("Display Area"));

        getContentPane().add(displayArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, 850, 570));

        jMenu1.setText("File");

        jmiNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jmiNew.setText("New");
        jMenu1.add(jmiNew);

        jmiLoad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jmiLoad.setText("Load");
        jmiLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLoadActionPerformed(evt);
            }
        });
        jMenu1.add(jmiLoad);

        jmiSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jmiSave.setText("Save");
        jMenu1.add(jmiSave);

        jmiExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jmiExit.setText("Exit");
        jmiExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmiExitMouseClicked(evt);
            }
        });
        jmiExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExitActionPerformed(evt);
            }
        });
        jMenu1.add(jmiExit);

        menBarTectTonic.add(jMenu1);

        jMenu2.setText("Edit");
        menBarTectTonic.add(jMenu2);

        jMenu3.setText("Visualize");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem5.setText("Scatter Plot");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem6.setText("Bar Chat");
        jMenu3.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem7.setText("Line Graph");
        jMenu3.add(jMenuItem7);

        menBarTectTonic.add(jMenu3);

        jMenu4.setText("About");
        menBarTectTonic.add(jMenu4);

        setJMenuBar(menBarTectTonic);

        setSize(new java.awt.Dimension(1670, 982));
        setLocationRelativeTo(null);
    }// </editor-fold>   
    
    
// </editor-fold>                        
// this loads the wells in a combo list
     private void loadDisplayScreen() {
        wells = xmlreader.getWells();
      
        for (int i = 0; i < wells.size(); i++) {
            opName.add(new Well(i, wells.get(i).getName()));
           
        }
        Iterator<Well> iterate = opName.iterator();
      
        while (iterate.hasNext()) {
            arrName.add(iterate.next().getName());
        }
        aModel = new DefaultComboBoxModel<>(arrName);
        wellCombo.setModel(aModel);

    } 
     
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void jmiLoadActionPerformed(java.awt.event.ActionEvent evt) {                                        
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("xml file only", ".xml");
        chooser.setFileFilter(fnef);
        int input = chooser.showOpenDialog(this);
        if (input == 0) {

        }
    }                                       

    private void jmiExitActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        System.exit(0);
    }                                       
    private void jmiExitMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:

    }                                    

    private void maxBtnActionPerformed(java.awt.event.ActionEvent evt) {                                       
        ChartFrame frame = new ChartFrame(
                "XY graph using JFreeChart", chart);
        frame.pack();
        frame.setVisible(true);

    }                                      

    public static JLabel getStatusBar() {
        return statusBar;
    }
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {                                        
        try {
            ChartUtilities.saveChartAsJPEG(
                    new File(defaultDirectory, fileName + "." + fileFormat), chart, 800, 600);
            statusBar.setForeground(Color.blue);
            statusBar.setText("File Saved");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Problem saving this file");
            statusBar.setForeground(Color.red);
            statusBar.setText("File not saved");
        }

    }                                       

    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          

        Exports ex = new Exports(this, true);
        ex.setVisible(true);
    }                                         

    private void btnRenderActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // get the axis first
        
        String x = jcbX_Axis.getSelectedItem().toString();
        String y = jcbY_Axis.getSelectedItem().toString();
        
        if(y.equals("Depth")){
        //log plot
    
        WitsmlLogCurve ydata = currentCurves.get(0);
        WitsmlLogCurve xdata = null;
        // the selected value by looping through the curve object
        
        for(int i = 1; i < currentCurves.size(); i ++){
            if(x.equals(currentCurves.get(i).getDescription())){
                 xdata = currentCurves.get(i);
           //      System.out.println("got "+currentCurves.get(i).getDescription());
                 break;
            }
        }
        
       
          List<Object> yvalues = ydata.getValues();
          List<Object> xvalues = xdata.getValues();
          
          
          String title = "Depth against "+xdata.getDescription();
          XYSeries series = new XYSeries(title);
         for(int i = 0; i<yvalues.size(); i++){
            Object vx = xvalues.get(i);
            Object vy = yvalues.get(i);
            double dx =  Double.parseDouble(vx.toString());
            double dy =  Double.parseDouble(vy.toString());
        //     System.out.println("depth : "+dy +" : "+title+" : "+dx);
            series.add(dx, dy);
        }
         XYSeriesCollection data = new XYSeriesCollection();
           data.addSeries(series);
           
           // create a chart using the createYLineChart method...
            JFreeChart chart = ChartFactory.createXYLineChart(title, // chart title
                    xdata.getDescription(), "Depth", // x and y axis labels
                    data); // data
            
         XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesShapesVisible(1, true);        
           plot.setRenderer(renderer);
            ChartPanel cp = new ChartPanel(chart);
            setDisplayArea(cp);
            
        }else{
        // trajectory
        
        ArrayList<Double> ax = getValues(x);
        ArrayList<Double> ay = getValues(y);
        
        XYSeries series = new XYSeries(trajName);
        for(int i = 0; i < ax.size(); i++){
        series.add(ax.get(i), ay.get(i));
        }
        XYSeriesCollection data = new XYSeriesCollection();
        data.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(trajName, // chart title
                    x, y, // x and y axis labels
                    data);
         ChartPanel cp = new ChartPanel(chart);
         setDisplayArea(cp);
// call a method to select the method here  
        }
    }                                         

    private void jcbX_AxisActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void jcbX_AxisItemStateChanged(java.awt.event.ItemEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void wellComboActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String op = (String) wellCombo.getSelectedItem();

        loadwellBtnPanel(op);
    }                                         

    private void jcbY_AxisActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         


    private void loadwellBtnPanel(String op) {
     // get the particular well using its name
        for (int x = 0; x < wells.size(); x++) {
            if (op.equals(wells.get(x).getName())) {
                currentWell = wells.get(x);
                break;
            }
        }        
       // load the well panel with well on a button
        List<WitsmlWellbore> wellbores = xmlreader.getWellbores(currentWell);
        btnWellPanel.removeAll();
        for (int i = 0; i < wellbores.size(); i++) {
            WitsmlWellbore getWellBore = wellbores.get(i);

            JButton btn = new JButton("<html><b>Name:  " + getWellBore.getName() + "</b><br/> Status:  " + getWellBore.getStatus() + "<br> Type:  " + getWellBore.getType() + "</br></html>");
            btn.setBounds(5, 5, btnWellPanel.getWidth() - 10, 80);
            btn.setBounds(5, ((100 * i + 5)), btnWellPanel.getWidth() - 20, 100);
            btnWellPanel.add(btn);
// add Listener to the button            
            btn.addActionListener(new WellBoreListenerOnView(i, getWellBore, displayAreaPanel1, jcbX_Axis, jcbY_Axis, trajectoryPanel, logsPanel));
           repaint();
        }
             JButton b = (JButton) btnWellPanel.getComponent(0);
            b.doClick();
            wellCombo.setEnabled(true);
            exportBtn.setEnabled(true);
            saveBtn.setEnabled(true);
            maxBtn.setEnabled(true);
    }

    public static void setPlotted(boolean flag){
        plotted = flag;
    
    }
    public void btnSetPropery(){
        
    if(plotted){
        
    }else{
    
    }
    
    }
    
 public ArrayList<Double> getValues(String type){
 ArrayList<Double> values = new ArrayList <>();
    switch(type){
            case "Tvd":
                for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getTvd();    
                values.add(tvd.getValue());
                }  
                break;
            case "MD":
                for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getMd();    
                values.add(tvd.getValue());
                }
                break;
            case "North":
                for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getNorth();    
                values.add(tvd.getValue());
                }
                break;
          
            case "Turn Rate":
                for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getTurnRate();    
                values.add(tvd.getValue());
                }
                break;
            case "Vertical Section Distance":
                for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getVerticalSectionDistance();    
                values.add(tvd.getValue());
                }
                break;
            case "Azimuth":
                  for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getAzimuth();    
                values.add(tvd.getValue());
                }
                break;
            case "Build Rate":
              for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getBuildRate();    
                values.add(tvd.getValue());
                }
                break;
            case "Dmd":
                 for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getDMd();    
                values.add(tvd.getValue());
                }               
                break;
            case "Dtvd":
                 for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getDTvd();    
                values.add(tvd.getValue());
                }               
                break;
            case "Dip Angle Uncertainty":
                 for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getDipAngleUncertainty();    
                values.add(tvd.getValue());
                }               
                break;
            case "Dls":
            for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getDls();    
                values.add(tvd.getValue());
                }
                break;
            case "Gravitational Field Reference":
               for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getGravitationFieldReference();    
                values.add(tvd.getValue());
                }
                break;
            case "Gravitational Uncertainty":
                for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getGravityUncertainty();    
                values.add(tvd.getValue());
                }
                break;
            case "Station No.":
            for(WitsmlTrajectoryStation station : currentStation) {              
                 
                values.add(1.0*station.getStationNo());
                }
                break;
            case "Time":
                            for(WitsmlTrajectoryStation station : currentStation) {              
                 
                values.add(1.0*station.getStationNo());
                }
                break;
            case "Tool Face Gravitational angle":
             for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getToolfaceGravityAngle();    
                values.add(tvd.getValue());
                }
                break;
            case "Tool  Face Magnetic angle":
                for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getToolfaceMagneticAngle();    
                values.add(tvd.getValue());
                }
                break;
        }
        
 return values;
}




   
    private DefaultComboBoxModel<String> aModel;
    private SortedSet<Well> opName = new TreeSet<>();
    List<WitsmlWell> wells;

    private Vector<String> arrName = new Vector<>();
    WitsmlXMLReader xmlreader = new WitsmlXMLReader();



    // Variables declaration - do not modify                     
    private javax.swing.JPanel axisPanel;
    private static javax.swing.JButton btnRender;
    private javax.swing.JButton btnSetProperties;
    private javax.swing.JPanel btnWellPanel;
    private javax.swing.JPanel displayAreaPanel;
    private javax.swing.JPanel displayAreaPanel1;
    private javax.swing.JButton exportBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;

    private static javax.swing.JComboBox<String> jcbX_Axis;
    private static javax.swing.JComboBox<String> jcbY_Axis;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenuItem jmiLoad;
    private javax.swing.JMenuItem jmiNew;
    private javax.swing.JMenuItem jmiSave;
    private javax.swing.JScrollPane jspProperty;
    private javax.swing.JScrollPane jspWell;
    private javax.swing.JLabel lblXAxis;
    private javax.swing.JLabel lblYAxis;
    private javax.swing.JPanel logsPanel;
    private javax.swing.JButton maxBtn;
    private javax.swing.JMenuBar menBarTectTonic;
    private javax.swing.JButton saveBtn;
    private static javax.swing.JLabel statusBar;
    private javax.swing.JTable tblProperty;
    private javax.swing.JPanel titleBarPanel;
    private javax.swing.JPanel toolBarPanel;
    private javax.swing.JPanel trajectoryPanel;
    private static javax.swing.JPanel displayArea1;
    private javax.swing.JPanel utilityPanel;
    private javax.swing.JComboBox<String> wellCombo;
    // End of variables declaration                   
}

class ColorEditors extends AbstractCellEditor
                         implements TableCellEditor,
                                    ActionListener {
    Color currentColor;
    JButton button;
    JColorChooser colorChooser;
    JDialog dialog;
    protected static final String EDIT = "edit";
    public ColorEditors() {
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);
        colorChooser = new JColorChooser();
        dialog = JColorChooser.createDialog(button,
                                        "choose color",
                                        true,  //modal
                                        colorChooser,
                                        this,
                                        null);
    }
    public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
            button.setBackground(currentColor);
            colorChooser.setColor(currentColor);
            dialog.setVisible(true);
            fireEditingStopped(); 
        } else {
            currentColor = colorChooser.getColor();
        }
    }
    public Object getCellEditorValue() {
        return currentColor;
    }
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        currentColor = (Color)value;
        return button;
    }
                        
}

class TableButton extends JButton implements TableCellRenderer, TableCellEditor {
  private int selectedRow;
  private int selectedColumn;


  public TableButton(String text) {
    super(text); 

  }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getCellEditorValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean stopCellEditing() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelCellEditing() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

  
}