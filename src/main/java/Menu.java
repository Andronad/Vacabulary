import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Menu extends JFrame {
    private JPanel rootPanel;
    private JButton testEngRus;
    private JButton addWords;
    private JButton testRusEng;
    private JButton vacabularyButton;

    public Menu() {
        testEngRus.addActionListener(e -> {
            String result=JOptionPane.showInputDialog("For how many days you want to download base?");
            if(result!=null) {
                try {
                    new Testing(1, Integer.parseInt(result));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error");
                }
            }
        });
        testRusEng.addActionListener(e -> {
            String result=JOptionPane.showInputDialog("For how many days you want to download base?");
            if(result!=null) {
                try {
                    new Testing(2, Integer.parseInt(result));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error");
                }
            }
        });
        vacabularyButton.addActionListener(e -> {
            try {
                new AllWords();
            } catch (SQLException | ParseException ex) {
                ex.printStackTrace();
            }
        });
        addWords.addActionListener(e -> {
            try {
                new AddWord();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        setContentPane(rootPanel);
        setVisible(true);
        setBounds(100,100,300,250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Menu();
    }
}
