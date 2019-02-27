package shopping.list.Bridge;

import javax.swing.*;
import java.util.Vector;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

class productTable extends JScrollPane {
    JTable table;

    public productTable(Vector list) {
        table = new JTable(new prodModel(list));
        table.getModel();
        getViewport().add(table);
    }
    public TableModel getModel(){
        return table.getModel();
    }

    public void setRowSorter(TableRowSorter<TableModel> sorter) {
        table.setRowSorter(sorter);
    }
}

