/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techtonic;

/**
 *
 * @author 1412625
 */
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
public class JTableColorChooser extends JFrame
{
    JTable table;
	String title[]={"Language","Type","color"};
    Object [][]valuess={{"C","1"}
    ,{"C++","2"}
    ,{"C#","3"}
    ,{"Java","4"}
    ,{"Perl","5"}
    ,{"PHP","6"}
    ,{"Python","7"}};
   DefaultTableModel tabModel;
	public JTableColorChooser(){
		table=new JTable();
		 table.setModel(new DefaultTableModel(valuess, title));
		 TableColumn colonneAnnee = table.getColumnModel().getColumn(2);
         colonneAnnee.setCellEditor(new ColorEditor());
        JScrollPane jsp = new JScrollPane(table);
        getContentPane( ).add(jsp, BorderLayout.CENTER);
	}
		public static void main(String [] arg)
	{
       JTableColorChooser tab= new JTableColorChooser();
       tab.setTitle("JTable And JColorChooser Example");
       tab.setSize(450,150);
       tab.setVisible(true);
	}
}
class ColorEditor extends AbstractCellEditor
                         implements TableCellEditor,
                                    ActionListener {
    Color currentColor;
    JButton button;
    JColorChooser colorChooser;
    JDialog dialog;
    protected static final String EDIT = "edit";
    public ColorEditor() {
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
