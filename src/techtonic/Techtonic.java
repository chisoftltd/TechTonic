/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techtonic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.Logger;
//import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jwitsml.WitsmlWell;
import org.jwitsml.WitsmlWellbore;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jwitsml.Value;
import org.jwitsml.WitsmlLogCurve;
import org.jwitsml.WitsmlTrajectoryStation;
//import static techtonic.Onview.setDisplayArea;

/**
 *
 * @author 1412625
 */
public class Techtonic extends javax.swing.JFrame {

    static private JFreeChart chart;
    static private String Orientation;
    static private String xLabel;
    static private String yLabel;
    static private String title;
    private static List<WitsmlTrajectoryStation> currentStation; 
    private static String trajName="Trajectory"; 
    private static List<WitsmlLogCurve> currentCurves;
    private static boolean plotted = false;
    private static ChartPanel chartPanel = null; 
    static private Color bgColor;
    static private Color foreColor;
    static private String fileFormat = "jpg";
    static private String defaultDirectory = "H:\\Desktop\\files";
    static private String fileName = "Brude";
    static private int eWidth = 800;
    static private int eHeight = 600;
    
    static private List<WitsmlTrajectoryStation> currentWitsmlTrajectoryStation;
    
//    public static void setTrajName(String name){
//        Techtonic.setTrajName(name);
//    }
    
    public static String getFileFormat() {
        return fileFormat;
    }
    
    
    public static void setCurrentCurves(List<WitsmlLogCurve> currentCurves){
        Techtonic.currentCurves = currentCurves;
    }
    public static void setWellCombo(boolean b){
        Techtonic.wellCombo.setEnabled(b);
    }
    public static void setPropertyBtn(boolean b){
        Techtonic.btnSetProperties.setEnabled(b);
    }
    public static void setCurrentStations(List<WitsmlTrajectoryStation> stationsAsList){
        Techtonic.currentStation = stationsAsList;
    }
    public static void setFileFormat(String fileFormat) {
        Techtonic.fileFormat = fileFormat;
    }

    public static String getDefaultDirectory() {
        return defaultDirectory;
    }

    public static void setDefaultDirectory(String defaultDirectory) {
        Techtonic.defaultDirectory = defaultDirectory;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        Techtonic.fileName = fileName;
    }

    public static int geteWidth() {
        return eWidth;
    }

    public static void seteWidth(int eWidth) {
        Techtonic.eWidth = eWidth;
    }

    public static int geteHeight() {
        return eHeight;
    }

    public static void seteHeight(int eHeight) {
        Techtonic.eHeight = eHeight;
    }
    public static int getjcbX_Axis() {
        return eHeight;
    }

    public static void setjcbX_Axis(Vector<String> s) {
        Techtonic.jcbX_Axis.setModel(new DefaultComboBoxModel(s));
    }
    public static int getjcbY_Axis() {
        return eHeight;
    }

    public static void setjcbY_Axis(Vector<String> s) {
        Techtonic.jcbY_Axis.setModel(new DefaultComboBoxModel(s));
    }
    public static void setEnablejcbX_Axis(boolean flag) {
        Techtonic.jcbX_Axis.setEnabled(flag);
    }
    public static void setEnablejcbY_Axis(boolean flag) {
        Techtonic.jcbY_Axis.setEnabled(flag);
    }
    public static void setEnableRenderBtn(boolean f){
      Techtonic.btnRender.setEnabled(f);
    }
    public static JFreeChart getFreeChart() {
        return chart;
    }
    public static void setFreeChart(JFreeChart fc) {
         chart = fc;
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
        Techtonic.chart = ch;
    }
    public static void validateJSP() {
        Techtonic.jScrollPane2.validate();
        Techtonic.jScrollPane2.repaint();
        Techtonic.jScrollPane1.validate();
        Techtonic.jScrollPane1.repaint();
        
    }

    public static void setOrientation(String Orientation) {
        Techtonic.Orientation = Orientation;
    }

    public static void setxLabel(String xLabel) {
        Techtonic.xLabel = xLabel;
    }

    public static void setyLabel(String yLabel) {
        Techtonic.yLabel = yLabel;
    }

    public static void setBgColor(Color bgColor) {
        Techtonic.bgColor = bgColor;
    }

    public static void setForeColor(Color foreColor) {
        Techtonic.foreColor = foreColor;
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
            java.util.logging.Logger.getLogger(Techtonic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Techtonic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Techtonic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Techtonic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Techtonic().setVisible(true);
            }
        });
    }

    /**
     * Creates new form Techtonic
     */
    public Techtonic() {
        initComponents();
        wellCombo.setEnabled(false);
        loadDisplayScreen();
        jcbX_Axis.setModel(new DefaultComboBoxModel(new String[]{}));
        jcbX_Axis.setEnabled(false);
        jcbY_Axis.setModel(new DefaultComboBoxModel(new String[]{}));
        jcbY_Axis.setEnabled(false);
        btnRender.setEnabled(false);
        btnSetProperties.setEnabled(false);
        maxBtn.setEnabled(false);
        exportBtn.setEnabled(false);
        saveBtn.setEnabled(false);
      //  setResizable(false);
    }
    public static void setDisplayArea(ChartPanel cp){
            displayAreaPanel1.removeAll();

           
            displayAreaPanel1.setLayout(new java.awt.BorderLayout());
            
            cp.getChart().getXYPlot().getRangeAxis().setInverted(true);
            displayAreaPanel1.add(cp,BorderLayout.CENTER);
            
            displayAreaPanel1.validate();
            displayAreaPanel1.repaint();
    
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
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
        statusBar = new javax.swing.JLabel();
        btnSetProperties = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        trajectoryPanel = new javax.swing.JPanel();
        btnWellPanel = new javax.swing.JPanel();
        displayAreaPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        shape = new javax.swing.JLabel();
        bgPan = new javax.swing.JPanel();
        bgColorLab = new javax.swing.JLabel();
        forePan = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        logsPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        menBarTectTonic = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiNew = new javax.swing.JMenuItem();
        jmiLoad = new javax.swing.JMenuItem();
        jmiSave = new javax.swing.JMenuItem();
        jmiExit = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        access = new javax.swing.JMenu();
        login = new javax.swing.JMenuItem();
        logout = new javax.swing.JMenuItem();

        jMenu5.setText("File");
        jMenuBar1.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar1.add(jMenu6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Well Data Processor -TechTonic");
        setName("TechTonic"); // NOI18N
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
                .addContainerGap(1034, Short.MAX_VALUE))
        );
        titleBarPanelLayout.setVerticalGroup(
            titleBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleBarPanelLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(20, 20, 20))
        );

        getContentPane().add(titleBarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1290, 80));

        toolBarPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        toolBarPanel.setPreferredSize(new java.awt.Dimension(100, 65));

        axisPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Plot", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        axisPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        axisPanel.setMinimumSize(new java.awt.Dimension(100, 100));
        axisPanel.setPreferredSize(new java.awt.Dimension(699, 99));

        jcbX_Axis.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbX_Axis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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
        btnRender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techtonic/images/render.png"))); // NOI18N
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
                .addComponent(jcbY_Axis, 0, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRender, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        axisPanelLayout.setVerticalGroup(
            axisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(axisPanelLayout.createSequentialGroup()
                .addGroup(axisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(axisPanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(axisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblXAxis, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbX_Axis, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblYAxis)
                            .addComponent(jcbY_Axis, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnRender))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        utilityPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Uitility", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        maxBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techtonic/images/maxx.png"))); // NOI18N
        maxBtn.setActionCommand("");
        maxBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxBtnActionPerformed(evt);
            }
        });

        saveBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techtonic/images/save.png"))); // NOI18N
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        exportBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        exportBtn.setIcon(new javax.swing.ImageIcon("H:\\Desktop\\export.png")); // NOI18N
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
                .addComponent(maxBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        utilityPanelLayout.setVerticalGroup(
            utilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, utilityPanelLayout.createSequentialGroup()
                .addGroup(utilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(maxBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, utilityPanelLayout.createSequentialGroup()
                        .addComponent(saveBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Wells", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        wellCombo.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        wellCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        wellCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wellComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(wellCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(wellCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        wellCombo.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout toolBarPanelLayout = new javax.swing.GroupLayout(toolBarPanel);
        toolBarPanel.setLayout(toolBarPanelLayout);
        toolBarPanelLayout.setHorizontalGroup(
            toolBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolBarPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(axisPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(utilityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        toolBarPanelLayout.setVerticalGroup(
            toolBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolBarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(toolBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(utilityPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(toolBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(axisPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(toolBarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 101, 1290, 120));

        statusBar.setForeground(new java.awt.Color(0, 51, 204));
        statusBar.setText(" ");
        statusBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        getContentPane().add(statusBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 670, 1620, -1));

        btnSetProperties.setText("Set Properties");
        btnSetProperties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetPropertiesActionPerformed(evt);
            }
        });
        getContentPane().add(btnSetProperties, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 450, 230, 60));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout trajectoryPanelLayout = new javax.swing.GroupLayout(trajectoryPanel);
        trajectoryPanel.setLayout(trajectoryPanelLayout);
        trajectoryPanelLayout.setHorizontalGroup(
            trajectoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );
        trajectoryPanelLayout.setVerticalGroup(
            trajectoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(trajectoryPanel);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 210, 190));

        btnWellPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnWellPanel.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout btnWellPanelLayout = new javax.swing.GroupLayout(btnWellPanel);
        btnWellPanel.setLayout(btnWellPanelLayout);
        btnWellPanelLayout.setHorizontalGroup(
            btnWellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );
        btnWellPanelLayout.setVerticalGroup(
            btnWellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );

        getContentPane().add(btnWellPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 220, 420));

        displayAreaPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout displayAreaPanel1Layout = new javax.swing.GroupLayout(displayAreaPanel1);
        displayAreaPanel1.setLayout(displayAreaPanel1Layout);
        displayAreaPanel1Layout.setHorizontalGroup(
            displayAreaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
        );
        displayAreaPanel1Layout.setVerticalGroup(
            displayAreaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );

        getContentPane().add(displayAreaPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 590, 420));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Properties");

        shape.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shape.setText("Shape");
        shape.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bgPan.setBackground(new java.awt.Color(255, 255, 255));
        bgPan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bgPan.setForeground(new java.awt.Color(255, 255, 255));

        bgColorLab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bgColorLab.setText("Background Colour");
        bgColorLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bgColorLabMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout bgPanLayout = new javax.swing.GroupLayout(bgPan);
        bgPan.setLayout(bgPanLayout);
        bgPanLayout.setHorizontalGroup(
            bgPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgPanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bgColorLab, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        bgPanLayout.setVerticalGroup(
            bgPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgColorLab, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        forePan.setBackground(new java.awt.Color(255, 255, 255));
        forePan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Foreground Colour");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout forePanLayout = new javax.swing.GroupLayout(forePan);
        forePan.setLayout(forePanLayout);
        forePanLayout.setHorizontalGroup(
            forePanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, forePanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        forePanLayout.setVerticalGroup(
            forePanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, forePanLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(forePan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(shape, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bgPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bgPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(forePan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(shape, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 240, 230, 190));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout logsPanelLayout = new javax.swing.GroupLayout(logsPanel);
        logsPanel.setLayout(logsPanelLayout);
        logsPanelLayout.setHorizontalGroup(
            logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );
        logsPanelLayout.setVerticalGroup(
            logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(logsPanel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, 210, 180));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Trajectories");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 190, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Logs");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 460, 200, -1));

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

        jMenu4.setText("About");
        menBarTectTonic.add(jMenu4);

        access.setText("Access");

        login.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        login.setText("Log In");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        access.add(login);

        logout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        logout.setText("Log Out");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        access.add(logout);

        menBarTectTonic.add(access);

        setJMenuBar(menBarTectTonic);

        setSize(new java.awt.Dimension(1332, 767));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
     
    private void jmiLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLoadActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("xml file only", ".xml");
        chooser.setFileFilter(fnef);
        int input = chooser.showOpenDialog(this);
        if (input == 0) {

        }
    }//GEN-LAST:event_jmiLoadActionPerformed

    private void jmiExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jmiExitActionPerformed

    private void jmiExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmiExitMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jmiExitMouseClicked

    private void maxBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxBtnActionPerformed
        ChartFrame frame = new ChartFrame(
                "XY graph using JFreeChart", chart);
        frame.pack();
        frame.setVisible(true);

    }//GEN-LAST:event_maxBtnActionPerformed

    public static JLabel getStatusBar() {
        return statusBar;
    }
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
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

    }//GEN-LAST:event_saveBtnActionPerformed

    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnActionPerformed

        Exports ex = new Exports(this, true);
        ex.setVisible(true);
    }//GEN-LAST:event_exportBtnActionPerformed

    private void btnRenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenderActionPerformed
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
                 xdata.getUnit();
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
                    xdata.getDescription()+" ["+xdata.getUnit()+"]", "Depth [m]", // x and y axis labels
                    data); // data
            
//        XYPlot plot = (XYPlot) chart.getPlot();
//        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//        renderer.setSeriesLinesVisible(0, true);
//        renderer.setSeriesShapesVisible(0, false);
//        renderer.setSeriesLinesVisible(1, false);
//        renderer.setSeriesShapesVisible(1, true);        
//           plot.setRenderer(renderer);
            ChartPanel cp = new ChartPanel(chart);
            setFreeChart(chart);
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
         setFreeChart(chart);
         setDisplayArea(cp);
// call a method to select the method here  
        }

    }//GEN-LAST:event_btnRenderActionPerformed

    private void jcbX_AxisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbX_AxisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbX_AxisActionPerformed

    private void jcbX_AxisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbX_AxisItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbX_AxisItemStateChanged

    private void wellComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wellComboActionPerformed
        String op = (String) wellCombo.getSelectedItem();

        loadwellBtnPanel(op);
    }//GEN-LAST:event_wellComboActionPerformed

    private void jcbY_AxisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbY_AxisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbY_AxisActionPerformed

    private void bgColorLabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgColorLabMouseClicked
        Color c = JColorChooser.showDialog(null, "Choose a Background Color", null);
        bgPan.setBackground(c);
        bgColor = c;
    }//GEN-LAST:event_bgColorLabMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        Color c = JColorChooser.showDialog(null, "Choose a Foreground Color", null);
        forePan.setBackground(c);
        foreColor = c;
        
    }//GEN-LAST:event_jLabel7MouseClicked

    private void btnSetPropertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetPropertiesActionPerformed

        
         XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, foreColor);
        renderer.setSeriesShapesVisible(0, false);
        
       plot.setRenderer(renderer);
       ChartPanel cp = new ChartPanel(chart);
        System.out.println("opacity: "+cp.isOpaque());
       cp.setBackground(bgColor);
       setFreeChart(chart);
        setDisplayArea(cp);

  
    }//GEN-LAST:event_btnSetPropertiesActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
            Loggger loger = new Loggger(this, true);
            loger.setVisible(true);
    }//GEN-LAST:event_loginActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        jcbY_Axis.setEnabled(false);
        jcbX_Axis.setEnabled(false);
        btnRender.setEnabled(false);
        btnSetProperties.setEnabled(false);
        maxBtn.setEnabled(false);
        exportBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        wellCombo.setEditable(false);
    }//GEN-LAST:event_logoutActionPerformed
//    private void loadwellBtnPanel(String op) {
//        ArrayList<WitsmlWell> welllist = new ArrayList();
//        for (int x = 0; x < wells.size(); x++) {
//            if (op.equals(wells.get(x).getOperator())) {
//                welllist.add(wells.get(x));
//            }
//        }
//        // load the well panel with well on a button
//        int welllistsize = welllist.size();
//        btnWellPanel.removeAll();
//        //btnWellPanel.setLayout(new GridLayout(welllistsize, 1, 5, 5));
//        for (int i = 0; i < welllistsize; i++) {
//
//            JButton btn = new JButton("<html><b>" + welllist.get(i).getName() + "</b></html>");
//            btn.setBounds(5, 5, btnWellPanel.getWidth() - 10, 80);
//            btn.addActionListener(new ButtonListener(btn, welllist.get(i)));
//            btnWellPanel.add(btn);
//
////            repaint();
//            JButton b = (JButton) btnWellPanel.getComponent(0);
//            b.doClick();
//        }

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
 
    
     public ArrayList<Double> getValues(String type){
 ArrayList<Double> values = new ArrayList <>();
 
    switch(type){
            case "Tvd":
                
                
                
                for(WitsmlTrajectoryStation station : currentStation) {              
                Value tvd = station.getTvd(); 
                    System.out.println("unit of tvd"+tvd.getUnit());
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
    
    
    
    
    
    class ButtonListener implements ActionListener {

        private final JButton btn;
        private final WitsmlWell well;
        private WitsmlXMLReader xmlreader = new WitsmlXMLReader();

        public ButtonListener(JButton btn, WitsmlWell well) {
            this.btn = btn;
            this.well = well;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//            System.out.println(btn.getText());
            List<WitsmlWellbore> wellbores = xmlreader.getWellbores(well);
            btnWellPanel.removeAll();
            //btnWellPanel.setLayout(new GridLayout(wellbores.size(), 1, 5, 5));
            btnWellPanel.setPreferredSize(new Dimension(10, (wellbores.size() * 100) + 15));
            for (int i = 0; i < wellbores.size(); i++) {
                WitsmlWellbore wellbore = wellbores.get(i);
                JButton btnWellbore = new JButton("<html><b>Name:  " + wellbore.getName()
                        + "</b><br/> Status:  " + wellbore.getStatus() + "<br> Type:  " + wellbore.getType() + "</br></html>");

                btnWellbore.setBounds(5, ((100 * i) + 5), btnWellPanel.getWidth() - 20, 100);

                btnWellPanel.add(btnWellbore);

    //            btnWellbore.addActionListener(new WellBoreListener(i, wellbore, displayAreaPanel1, jcbX_Axis, jcbY_Axis, trajectoryPanel, logsPanel));

            }
            repaint();
            //WitsmlWellbore wellbore = wellbores.get(0);
        }
    }

   
    private DefaultComboBoxModel<String> aModel;
    private SortedSet<Well> opName = new TreeSet<>();
    List<WitsmlWell> wells;
    private int wellIndex;
    private Vector<String> arrName = new Vector<>();
    WitsmlXMLReader xmlreader = new WitsmlXMLReader();
//    DefaultPieDataset data = new DefaultPieDataset();


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu access;
    private javax.swing.JPanel axisPanel;
    private javax.swing.JLabel bgColorLab;
    private javax.swing.JPanel bgPan;
    private static javax.swing.JButton btnRender;
    private static javax.swing.JButton btnSetProperties;
    private javax.swing.JPanel btnWellPanel;
    private static javax.swing.JPanel displayAreaPanel1;
    private javax.swing.JButton exportBtn;
    private javax.swing.JPanel forePan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private static javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private static javax.swing.JComboBox<String> jcbX_Axis;
    private static javax.swing.JComboBox<String> jcbY_Axis;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenuItem jmiLoad;
    private javax.swing.JMenuItem jmiNew;
    private javax.swing.JMenuItem jmiSave;
    private javax.swing.JLabel lblXAxis;
    private javax.swing.JLabel lblYAxis;
    private javax.swing.JMenuItem login;
    private javax.swing.JMenuItem logout;
    private javax.swing.JPanel logsPanel;
    private javax.swing.JButton maxBtn;
    private javax.swing.JMenuBar menBarTectTonic;
    private javax.swing.JButton saveBtn;
    private javax.swing.JLabel shape;
    private static javax.swing.JLabel statusBar;
    private javax.swing.JPanel titleBarPanel;
    private javax.swing.JPanel toolBarPanel;
    private javax.swing.JPanel trajectoryPanel;
    private javax.swing.JPanel utilityPanel;
    private static javax.swing.JComboBox<String> wellCombo;
    // End of variables declaration//GEN-END:variables

}
