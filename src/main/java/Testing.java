import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Testing extends JFrame{
    public JLabel Question;
    private JTextField answerInput;
    private JButton checkButton;
    private JPanel testPanel;
    private JButton hintButton;
    private ArrayList<Word> vacabulary;
    private Word testingWord;
    private int countHints=0;
    private int testingType;
    private Service service;

    public Testing(int type) throws SQLException {
        testingType=type;
        service=new Service();
        service.fillVacabulary(testingType);
        checkButton.addActionListener(e -> {
            try {
                checkAnswer();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        answerInput.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER){
                    try {
                        checkAnswer();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            public void keyReleased(KeyEvent e) {}
        });
        hintButton.addActionListener(e -> {
            countHints++;
            answerInput.setText(testingWord.getTranslate().get(0).substring(0,countHints));
        });
        askQuestion();
        setContentPane(testPanel);
        setVisible(true);
        setBounds(150,200,300,220);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void askQuestion(){
        countHints=0;
        testingWord=service.getRandomWord();
        Question.setText(testingWord.getWord());
        answerInput.setText("");
    }
    public void checkAnswer() throws SQLException {
        if(testingWord.getTranslate().indexOf(answerInput.getText())!=-1){
            JOptionPane.showMessageDialog(null,"Correct\n"+testingWord.getTranslate());
            askQuestion();
        }
        else{
            if(answerInput.getText().length()==0){
                JOptionPane.showMessageDialog(null,testingWord.getTranslate());
            }
            else {
                JLabel text=new JLabel("<html><body><div width='200px' align='center'>Wrong answer<br>" +
                        "Correct answers were :<br>"+
                        testingWord.getTranslate()+
                        "<br>Do you want to add this translate of this word?</div></body></html>");
                int result = JOptionPane.showConfirmDialog(
                        null,
                        text,
                        "Error",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {

                    if(testingType==1){
                        service.addToDB(testingWord.getWord(),answerInput.getText());
                    }
                    else{
                        service.addToDB(answerInput.getText(),testingWord.getWord());
                    }
                    service.addToVacabulary(testingWord.getWord(),answerInput.getText());
                    askQuestion();
                }
            }
        }
    }

}
