package shopping.list.Bridge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class productDisplay extends JFrame {
    public productDisplay() {
        super("The Java Factory-- Products");
        setLF(); //set look and feel
        setCloseClick(); //set close on window close click
        InputFile f = new InputFile("src/shopping/list/Bridge/products.txt");
        Vector prod = new Vector();
        //read in product list
        String s = f.readLine();
        while (s != null) {
            prod.addElement(s);
            s = f.readLine();
        }
        JPanel p = new JPanel();
        getContentPane().add(p);
        p.setLayout(new GridLayout(1, 2));

        JPanel pleft = new JPanel();
        JPanel pright = new JPanel();
        JPanel pfooter = new JPanel();
        p.add(pleft);
        p.add(pright);
        pleft.setLayout(new BorderLayout());
        pright.setLayout(new BorderLayout());
        productList prodList=new productList(prod);
        //add in customer view as list box
        pleft.add("North", new JLabel("Customer view"));
        pleft.add("Center", prodList);

        //add in execute view as table
        pright.add("North", new JLabel("Executive view"));
        productTable table=new productTable(prod);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys;
        sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.UNSORTED));
        //sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        pright.add("Center", table);
        
        
        JButton sortBtn=new JButton("Sort");
        sortBtn.setBounds(100,100,100,100);
        pleft.add("South",sortBtn); 
        
        sortBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                prodList.sort();
                //pright.remove(0);
                //pright.add("North", new JLabel("Executive view"));
                //pright.add("Center", new productTable(prod));
                
            }
        });
        
        setSize(new Dimension(400, 300));
        setVisible(true);
    }

    //-----------------------------------------
    private void setCloseClick() {
        //create window listener to respond to window close click
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //------------------------------------------
    private void setLF() {
        // Force SwingApp to come up in the System L&F
        String laf = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException exc) {
            System.err.println("Warning: UnsupportedLookAndFeel: " + laf);
        } catch (Exception exc) {
            System.err.println("Error loading " + laf + ": " + exc);
        }
    }

    //---------------------------------------------

    static public void main(String argv[]) {
        new productDisplay();
    }
}
