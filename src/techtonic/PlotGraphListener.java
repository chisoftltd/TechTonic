/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techtonic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jwitsml.Value;
import org.jwitsml.WitsmlTrajectory;
import org.jwitsml.WitsmlTrajectoryStation;

/**
 *
 * @author 1613112
 */
public class PlotGraphListener implements ActionListener{
   
    private WitsmlTrajectory trajectory;
    
    JComboBox xAxis;
    JComboBox yAxis;
    String[] trajectoryQuality = {"Tvd","MD","North","Gravity","Turn Rate","Vertical section Distance","Azimuth","Build Rate","Dmd","Dtvd","Dip Angle Uncertainty","Dls","Gravitational Field Reference","Gravitational Uncertinty","Station No.","Time","Tool Face Gravitational angle","Tool Face Magnetic Angle"}; 
    public PlotGraphListener(WitsmlTrajectory trajectory){     
    this.trajectory = trajectory;  
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       
        Vector<String> v = new Vector<String>(Arrays.asList(trajectoryQuality));
        Techtonic.setEnablejcbX_Axis(true);
    Techtonic.setEnablejcbY_Axis(true);
    Techtonic.setjcbX_Axis(v);
    Techtonic.setjcbY_Axis(v);
             List<WitsmlTrajectoryStation> stationsAsList = Arrays.asList(new WitsmlTrajectoryStation[trajectory.getStations().size()]);
            for (WitsmlTrajectoryStation s : trajectory.getStations()) {
                stationsAsList.set(s.getStationNo(), s);
            }
            Techtonic.setEnablejcbX_Axis(true);
            Techtonic.setPropertyBtn(true);
    Techtonic.setEnablejcbY_Axis(true);
    Techtonic.setjcbX_Axis(v);
    Techtonic.setjcbY_Axis(v);
        Techtonic.setEnableRenderBtn(true);
            XYSeries series = new XYSeries(trajectory.getName());
            String xu ="";
            String yu = "";
// add data to Dataset (here assume data is in ArrayLists x and y
            int count = 1;
            for (WitsmlTrajectoryStation station : stationsAsList) {
               
                Value tvd = station.getTvd();
                
                
                if(tvd == null){
                continue;
                }
                xu = tvd.getUnit();
                Value md = station.getNorth();
                
                if(md == null){
                    continue;
                }
                yu = md.getUnit();
             //  System.out.println(count + " : ===>> tvd : "+tvd.getValue()+ "; md "+md.getValue());
                series.add(md.getValue(), tvd.getValue());
                count++;               
            }       
                    XYSeriesCollection data = new XYSeriesCollection();
                    data.addSeries(series);
                    Techtonic.setCurrentStations(stationsAsList);
// create a chart using the createYLineChart method...
            JFreeChart chart = ChartFactory.createXYLineChart(trajectory.getName(), // chart title
                    xu, yu, // x and y axis labels
                    data);
//            XYPlot plot = (XYPlot) chart.getPlot();
//             XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//        renderer.setSeriesLinesVisible(0, true);
//        renderer.setSeriesShapesVisible(1, true);
//        renderer.setSeriesLinesVisible(1, false);
//        renderer.setSeriesShapesVisible(1, true);        
//           plot.setRenderer(renderer);
       
            ChartPanel cp = new ChartPanel(chart);
//            JFrame fr = new JFrame();
//            fr.add(cp);
//            fr.pack();
//            fr.setVisible(true);
            //cp.setMouseZoomable(true, true);  
       //     Techtonic.setTrajName(trajectory.getName());
            Techtonic.setFreeChart(chart);
            Techtonic.setDisplayArea(cp);
//            chartPanel.setLayout(new java.awt.BorderLayout());
//            chartPanel.add(cp,BorderLayout.CENTER);
//            chartPanel.validate();
//            chartPanel.repaint();`
    }
    
}