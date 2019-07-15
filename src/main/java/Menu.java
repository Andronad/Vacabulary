import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Menu extends JFrame {
    private JPanel rootPanel;
    private JButton test;
    private JButton addWords;
    public Menu() {
        test.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new Testing();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        addWords.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddWord();
            }
        });
        setContentPane(rootPanel);
        setVisible(true);
        setBounds(100,100,200,150);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Menu();
    }

}
