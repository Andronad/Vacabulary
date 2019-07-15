import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
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

    public Testing() throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader("vacabulary.txt"));
        vacabulary=new ArrayList<Word>();
        String line;
        while((line=reader.readLine())!=null){
            String word=line.split(":")[0];
            String translate=line.split(":")[1];
            addToVacabulary(word,translate);
        }
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    checkAnswer();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        answerInput.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER){
                    try {
                        checkAnswer();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            public void keyReleased(KeyEvent e) {}
        });
        hintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                countHints++;
                answerInput.setText(testingWord.getTranslate().get(0).substring(0,countHints));
            }
        });
        askQuestion();
        setContentPane(testPanel);
        setVisible(true);
        setBounds(150,200,300,200);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void checkAnswer() throws IOException {
        if(testingWord.getTranslate().indexOf(answerInput.getText())!=-1){
            JOptionPane.showMessageDialog(null,"Correct\n"+testingWord.getTranslate());
            askQuestion();
        }
        else{
            System.out.println(testingWord.getTranslate()+" "+answerInput.getText());
            if(answerInput.getText().length()==0){
                JOptionPane.showMessageDialog(null,testingWord.getTranslate());
            }
            else {
                JLabel text=new JLabel("<html><body><div width='200px' align='center'>Wrong answer<br>" +
                        "Correct answers were :<br>"+
                        testingWord.getTranslate()+
                        "<br>Do you want to add this translate of this word?</div></body></html>");
                       /* "<center>Wrong answer\n" +
                                "Correct answers were :"+testingWord.getTranslate()+
                                "\nDo you want to add this translate of this word?</center>*/
                int result = JOptionPane.showConfirmDialog(
                        null,
                        text,
                        "Error",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    FileWriter writer = new FileWriter("vacabulary.txt", true);
                    String record = testingWord.getWord() + ":" + answerInput.getText() + "\n";
                    writer.append(record);
                    writer.flush();
                    writer.close();
                    addToVacabulary(testingWord.getWord(),answerInput.getText());
                    askQuestion();
                }
            }
        }
    }
    public void addToVacabulary(String word,String translate){
        Boolean flag=false;
        for (Word w:vacabulary) {
            if(w.getWord().equals(word)){
                w.addTranslate(translate);
                flag=true;
                break;
            }
        }
        if(!flag){
            ArrayList<String> tt=new ArrayList<String>();
            tt.add(translate);
            vacabulary.add(new Word(word,tt));
        }
    }
    public void askQuestion(){
        countHints=0;
        testingWord=vacabulary.get((int)Math.floor(Math.random()*vacabulary.size()));
        Question.setText(testingWord.getWord());
        answerInput.setText("");
    }
}
