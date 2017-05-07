/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techtonic;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jwitsml.Value;
import org.jwitsml.WitsmlLog;
import org.jwitsml.WitsmlTrajectory;
import org.jwitsml.WitsmlTrajectoryStation;
import org.jwitsml.WitsmlWellbore;

/**
 *
 * @author 1613112
 */
public class WellBoreListenerOnView implements ActionListener {
    
    WitsmlXMLReader xmlreader = new WitsmlXMLReader();
    private int id;
    private WitsmlWellbore wellbore;
    private JPanel chartPanel;
    private JPanel trajectoryPanel;
    private JPanel logPanel;
    int talker = 0;
    JFreeChart charts;
    XYSeries seriesMagger;
    private final JComboBox xAxis;
    private final JComboBox yAxis;

    String[] wellborePlot = {"Tvd","MD","North","Gravity","Turn Rate","Vertical section Distance","Azimuth","Build Rate","Dmd","Dtvd","Dip Angle Uncertainty","Dls","Gravitational Field Reference","Gravitational Uncertinty","Station No.","Time","Tool Face Gravitational angle","Tool Face Magnetic Angle"};

    public WellBoreListenerOnView(int id, WitsmlWellbore wellbore, JPanel pan, JComboBox xAxis, JComboBox yAxis, JPanel traj, JPanel logsPanel) {
        this.id = id;
        this.wellbore = wellbore;
        this.seriesMagger = seriesMagger;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.trajectoryPanel = traj;
        this.logPanel = logsPanel;
        
        chartPanel = pan;
        talker++;
        Arrays.sort(wellborePlot);
        
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WitsmlWellbore getWellbore() {
        return wellbore;
    }

    public void setWellbore(WitsmlWellbore wellbore) {
        this.wellbore = wellbore;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        loadTrajectory();
        loadLogs();
        
    }
    
    //add trajectory
    public void loadTrajectory(){
        List<WitsmlTrajectory> trajectorys = xmlreader.getTrajectorys(wellbore);
       // System.out.println("Number of Trajectory: "+trajectorys.size());
        trajectoryPanel.removeAll();
       // trajectoryPanel.setPreferredSize(new Dimension(trajectorys.size(), trajectoryPanel.getWidth()* 105));
        System.out.println("panel size"+trajectoryPanel.getHeight());
        for(int i = 0; i < trajectorys.size(); i++){
            WitsmlTrajectory trajectory = trajectorys.get(i);
            JButton btn = new JButton("<html><b>Name:  " + trajectory.getName() + "</b><br/> Number of Stations:  " + trajectory.getStations().size()+  "</br></html>");
           // btn.setBounds(5, 5, trajectoryPanel.getWidth() - 10, 80);
           btn.setBounds(5, ((100 * i + 5)), trajectoryPanel.getWidth() - 20, 100);
            
            
            btn.addActionListener(new PlotGraphListener(trajectory));
            
            trajectoryPanel.add(btn);
            
            
        }
        superImposGraph(trajectorys);
        trajectoryPanel.validate();
        Techtonic.validateJSP();
        
        
        
        trajectoryPanel.repaint();
    }
    
    public void superImposGraph(List<WitsmlTrajectory> trajectorys){
        int ct = 0;
        XYSeriesCollection data = new XYSeriesCollection();
         for(WitsmlTrajectory trajectory : trajectorys){
                
             List<WitsmlTrajectoryStation> stationsAsList = Arrays.asList(new WitsmlTrajectoryStation[trajectory.getStations().size()]);
            for (WitsmlTrajectoryStation s : trajectory.getStations()) {
                stationsAsList.set(s.getStationNo(), s);
            }

        
        
        XYSeries series = new XYSeries(trajectory.getName());
// add data to Dataset (here assume data is in ArrayLists x and y
      
            for (WitsmlTrajectoryStation station : stationsAsList) {
                
                Value tvd = station.getTvd();
                
                if(tvd == null){
                continue;
                }
                Value md = station.getNorth();
                if(md == null){
                    continue;
                }
             //  System.out.println(count + " : ===>> tvd : "+tvd.getValue()+ "; md "+md.getValue());
                series.add(md.getValue(), tvd.getValue());
                         
            }      
         
                    
                    data.addSeries(series);
                    ct++;
         }
// create a chart using the createYLineChart method...
            JFreeChart chart = ChartFactory.createXYLineChart("Trajectory: True Vertical Depth Against North", // chart title
                    "North", "Depth", // x and y axis labels
                    data); // data

       
            ChartPanel cp = new ChartPanel(chart);
//            JFrame fr = new JFrame();
//            fr.add(cp);
//            fr.pack();
//            fr.setVisible(true);
            cp.setMouseZoomable(true, true);  
            Techtonic.setFreeChart(chart);
            Techtonic.setDisplayArea(cp);
    
    }
    public void loadLogs(){
        List<WitsmlLog> logs = xmlreader.getLogs(wellbore);
     //   System.out.println("Number of Logs: "+logs.size());
        logPanel.removeAll();
        for(int i = 0; i < logs.size(); i++){
            WitsmlLog log = logs.get(i);
            JButton btn = new JButton("<html><b>Name:  " + log.getName() + "</b><br/></html>");
          //  btn.setBounds(5, 5, logPanel.getWidth() - 10, 80);
           btn.setBounds(5, ((100 * i + 5)), logPanel.getWidth() - 50, 100);
           btn.addActionListener(new PlotLogGraphListener(log));
            logPanel.add(btn);
        }
        logPanel.validate();
        logPanel.repaint();
    }   
    // add logs

}
