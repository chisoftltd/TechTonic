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
import org.jwitsml.WitsmlLog;
import org.jwitsml.WitsmlLogCurve;
import org.jwitsml.WitsmlTrajectory;
import org.jwitsml.WitsmlTrajectoryStation;

/**
 *
 * @author 1613112
 */
public class PlotLogGraphListener implements ActionListener{
   
    private WitsmlLog log;
  
    
    public PlotLogGraphListener(WitsmlLog log){     
    this.log = log;  
   

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        List<WitsmlLogCurve> curves = log.getCurves();
        
        Vector<String> curveDescription = new Vector<>();
        for(WitsmlLogCurve c : curves){
       // System.out.println(c.getDescription());
        curveDescription.add(c.getDescription());
        }
        Techtonic.setjcbX_Axis(curveDescription);
                
    Techtonic.setEnablejcbX_Axis(true);
    Techtonic.setEnablejcbY_Axis(false);
    Techtonic.setEnableRenderBtn(true);
    Techtonic.setPropertyBtn(true);
    
    //Techtonic.setjcbX_Axis();
    Vector<String> v = new Vector<String>(Arrays.asList(new String[]{"Depth"}));
    Techtonic.setjcbY_Axis(v);
    //plot the graph using values of the second entry
     WitsmlLogCurve ydata = curves.get(0);
     WitsmlLogCurve xdata = curves.get(1);
          List<Object> yvalues = ydata.getValues();
          List<Object> xvalues = xdata.getValues();
          String title = "Depth against "+xdata.getDescription();
          XYSeries series = new XYSeries(title);
          
        for(int i = 0; i<yvalues.size(); i++){
            Object vx = xvalues.get(i);
            Object vy = yvalues.get(i);
            double dx =  Double.parseDouble(vx.toString());
            double dy =  Double.parseDouble(vy.toString());
            series.add(dx, dy);
        }
            XYSeriesCollection data = new XYSeriesCollection();
           data.addSeries(series);
         
// create a chart using the createYLineChart method...
            JFreeChart chart = ChartFactory.createXYLineChart(title, // chart title
                    xdata.getDescription(), "Depth", // x and y axis labels
                    data); // data

  
            ChartPanel cp = new ChartPanel(chart);
//            JFrame fr = new JFrame();
//            fr.add(cp);
//            fr.pack();
//            fr.setVisible(true);
            //cp.setMouseZoomable(true, true);  
            Techtonic.setFreeChart(chart);
            Techtonic.setCurrentCurves(curves);
            Techtonic.setDisplayArea(cp);
//            chartPanel.setLayout(new java.awt.BorderLayout());
//            chartPanel.add(cp,BorderLayout.CENTER);
//            chartPanel.validate();
//            chartPanel.repaint();
    }
    
}
