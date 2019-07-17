import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class AddWord  extends JFrame{
    private JTextField wordInput;
    private JTextField translateInput;
    private JButton addButton;
    private JPanel addPanel;
    private Service service;

    public AddWord() throws SQLException {
        setContentPane(addPanel);
        service=new Service();
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    writeWord();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        translateInput.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER){
                    try {
                        writeWord();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            public void keyReleased(KeyEvent e) {}
        });
        setVisible(true);
        setBounds(200,300,250,150);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void writeWord() throws SQLException {
        service.addToDB(wordInput.getText(),translateInput.getText());
        wordInput.setText("");
        wordInput.requestFocus();
        translateInput.setText("");
    }
}
