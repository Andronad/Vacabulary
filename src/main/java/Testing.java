import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;

public class Testing extends JFrame{
    public JLabel Question;
    private JTextField answerInput;
    private JButton checkButton;
    private JPanel testPanel;
    private JButton hintButton;
    private Word testingWord;
    private int countHints=0;
    private int testingType;
    private Service service;

    public Testing(int type, int countDays) throws SQLException, ParseException {
        testingType=type;
        service=new Service();
        service.fillVacabulary(testingType,countDays);
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
                if(e.getKeyChar()==KeyEvent.VK_ESCAPE){
                    getHint();
                }
            }
            public void keyReleased(KeyEvent e) {}
        });
        hintButton.addActionListener(e -> {
            getHint();
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
    public void getHint(){
        countHints++;
        if(countHints<=testingWord.getTranslate().get(0).length()){
            answerInput.setText(testingWord.getTranslate().get(0).substring(0,countHints));
        }
    }
    public void checkAnswer() throws SQLException {
        if(testingWord.getTranslate().indexOf(answerInput.getText().trim())!=-1){
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
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {

                    if(testingType==1){
                        service.addToDB(testingWord.getWord(),answerInput.getText());
                    }
                    else{
                        service.addToDB(answerInput.getText(),testingWord.getWord());
                    }
                    service.addToVacabulary(testingWord.getWord(),answerInput.getText());
                }
                askQuestion();
            }
        }
    }

}
