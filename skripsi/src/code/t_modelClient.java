/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import listData.listClient;

/**
 *
 * @author Rootkit-Ltp
 */
public class t_modelClient extends AbstractTableModel{
    
    private List<listData.listClient> list;

    public t_modelClient(List<listClient> list) {
        this.list = list;
    }
    

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch (columnIndex){
            case 0 : return list.get(rowIndex).getNo();
            case 1 : return list.get(rowIndex).getNama();
            case 2 : return list.get(rowIndex).getEkstensi();
            case 3 : return list.get(rowIndex).getTipe();
            case 4 : return list.get(rowIndex).getBesar();
            case 5 : return list.get(rowIndex).getTanggal();
            case 6 : return list.get(rowIndex).getModify();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
         switch (column){
            case 0 : return "No";
            case 1 : return "Nama";
            case 2 : return "Ekstensi";
            case 3 : return "Status";
            case 4 : return "Besar";
            case 5 : return "Tanggal";
            case 6 : return "Jam Modify";
            default: return null;
        }
    }
    
    
    
}
