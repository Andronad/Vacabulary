import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Menu extends JFrame {
    private JPanel rootPanel;
    private JButton testEngRus;
    private JButton addWords;
    private JButton testRusEng;
    private JButton vacabularyButton;
    private ArrayList<Word> vacabulary;

    public Menu() {
        testEngRus.addActionListener(e -> {
            try {
                new Testing(1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        testRusEng.addActionListener(e -> {
            try {
                new Testing(2);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        vacabularyButton.addActionListener(e -> {
            try {
                new AllWords();
            } catch (SQLException ex) {
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
