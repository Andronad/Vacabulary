import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class AddWord  extends JFrame{
    private JTextField wordInput;
    private JTextField translateInput;
    private JButton addButton;
    private JPanel addPanel;

    public AddWord(){
        setContentPane(addPanel);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(wordInput.getText()+" "+translateInput.getText());
                try {
                    FileWriter writer=new FileWriter("vacabulary.txt",true);
                    String record=wordInput.getText()+":"+translateInput.getText()+"\n";
                    writer.append(record);
                    writer.flush();
                    wordInput.setText("");
                    wordInput.requestFocus();
                    translateInput.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        setVisible(true);
        setBounds(200,300,200,120);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
