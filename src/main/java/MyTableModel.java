import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel{
    private ArrayList<Word> vacabulary;
    public MyTableModel(ArrayList<Word> v){
        vacabulary=v;
    }
    public int getRowCount() {
        return vacabulary.size();
    }
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        if (column==0) return "Word";
        return "Translates";
    }
    

    public Object getValueAt(int r, int c) {
        if(c==0) return vacabulary.get(r).getWord();
        return vacabulary.get(r).getAllTranslates();
    }
}