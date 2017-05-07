/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techtonic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jwitsml.Value;
import org.jwitsml.WitsmlTrajectory;
import org.jwitsml.WitsmlTrajectoryStation;
import org.jwitsml.WitsmlWellbore;

/**
 *
 * @author 1412625
 */
public class WellBoreListener implements ActionListener {

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

    public WellBoreListener(int id, WitsmlWellbore wellbore, JPanel pan, JComboBox xAxis, JComboBox yAxis, JPanel traj, JPanel logsPanel) {
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
        
     //   System.out.print("talker created   ==>>>  "+talker);
   
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
        List<WitsmlTrajectory> trajectorys = xmlreader.getTrajectorys(wellbore);
        for (int i = 0; i < trajectorys.size(); i++) {
     
           System.out.println("***************************************************************");
           
           
            System.out.println("Wellbore Trajectories "+trajectorys.size());
            System.out.println("Trajectory Stations");
            System.out.println("***************************************************************");
            System.out.println("*/ "+(i+1)+" == " + trajectorys.get(i).getName() + " has   " + trajectorys.get(i).getStations().size() + "  stations");
                  System.out.println("***************************************************************");
                  System.out.println("***************************************************************");
            
//            // trajectories button
//            JButton btnTrajectory = new JButton("<html><b>Name:  " + trajectorys.get(i).getName()
//                        + "</b><br/> Status:  " +trajectorys.get(i).getStations().size() + "<br> Type:  " + trajectorys.get(i).getTvdUnit() + "</br></html>");
//
//                btnTrajectory.setBounds(5, ((100 * i) + 5), this.trajectoryPanel.getWidth() - 20, 100);
//
//                this.trajectoryPanel.add(btnTrajectory);
//            
//                trajectoryPanel.validate();
//                trajectoryPanel.repaint();
            
            
            
            
            
            
            
            
            //Set<WitsmlTrajectoryStation> stations = trajectory.getStations();
            //System.out.println(stations);
            List<WitsmlTrajectoryStation> stationsAsList = Arrays.asList(new WitsmlTrajectoryStation[trajectorys.get(i).getStations().size()]);
            for (WitsmlTrajectoryStation s : trajectorys.get(i).getStations()) {
                stationsAsList.set(s.getStationNo(), s);
            }

//            for (WitsmlTrajectoryStation station : stationsAsList) {
//                System.out.println(
//                        "tvd: " + station.getTvd() + "  "
//                        + "md:  " + station.getMd() + "  "
//                        + "north:  " + station.getNorth() + "  "
//                        + "east:  " + station.getEast() + "  "
//                        + "gravity:  " + station.getInclination() + "  "
//                        + " Turn Rate :"+ station.getTurnRate() + " "
//                        + " "+ station.getVerticalSectionDistance() + " "
//                        + " "+ station.getAzimuth()
//                );
//            }
            // create a DataSet object for storing xy data 
            XYSeries series = new XYSeries("series name");
// add data to Dataset (here assume data is in ArrayLists x and y
            int count = 1;
            for (WitsmlTrajectoryStation station : stationsAsList) {
                
                Value tvd = station.getTvd();
                
                if(tvd == null){
                continue;
                }
                Value md = station.getNorth();
                if(md == null){
                    continue;
                }
           //     System.out.println(count + " : ===>> tvd : "+tvd.getValue()+ "; md "+md.getValue());
                series.add(md.getValue(), tvd.getValue());
                count++;               
            }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
//            
            
            
            XYSeriesCollection data = new XYSeriesCollection();
            data.addSeries(series);
            
// create a chart using the createYLineChart method...
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Line Chart", // chart title
                    "x", "y", // x and y axis labels
                    data); // data

            Techtonic.setChart(chart);
        Techtonic.setcurrentWitsmlTrajectoryStation(stationsAsList);
          
            ChartPanel cp = new ChartPanel(chart);
            cp.setMouseZoomable(true, true);
            chartPanel.setLayout(new java.awt.BorderLayout());
            chartPanel.add(cp,BorderLayout.CENTER);
            chartPanel.validate();        
            System.out.println("");
            System.out.println("");
        }
    }

}
