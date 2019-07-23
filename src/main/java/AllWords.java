import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class AllWords extends JFrame{
    private JTable table1;
    private JPanel panel1;
    private Service service;
    public AllWords() throws SQLException {
        service=new Service();
        service.fillVacabulary(1,0);
        ArrayList<Word> vacabulary=service.getVacabulary();
        vacabulary.sort(Comparator.comparing(Word::getWord));
        MyTableModel mtm=new MyTableModel(vacabulary);
        table1=new JTable(mtm);
        table1.setBounds(30,40,800,400);
        JScrollPane sp=new JScrollPane(table1);
        add(sp);
        setVisible(true);
        setBounds(200,300,800,400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
