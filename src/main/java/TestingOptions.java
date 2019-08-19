import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class TestingOptions extends JFrame{
    private JLabel word;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JRadioButton a4RadioButton;
    private JButton checkAnswerButton;
    private JPanel optionsPanel;
    private ButtonGroup group;
    private Service service;
    private Word testingWord;
    private ArrayList<Word> options;

    public TestingOptions() throws SQLException {
        checkAnswerButton.addActionListener(e -> {
            String answer=getSelection();
            if(testingWord.getAllTranslates().indexOf(answer)!=-1){
                askQuestion();
            }
            else{
                JOptionPane.showMessageDialog(null,"incorrect");
            }
        });
        service=new Service();
        service.fillVacabulary(2,0);
        group=new ButtonGroup();
        group.add(a1RadioButton);
        group.add(a2RadioButton);
        group.add(a3RadioButton);
        group.add(a4RadioButton);
        askQuestion();
        setVisible(true);
        setContentPane(optionsPanel);
        setBounds(150,200,300,290);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public String getSelection(){
        if(a1RadioButton.isSelected()){
            return a1RadioButton.getText();
        }
        if(a2RadioButton.isSelected()){
            return a2RadioButton.getText();
        }
        if(a3RadioButton.isSelected()){
            return a3RadioButton.getText();
        }
        return a4RadioButton.getText();
    }
    public void askQuestion(){
        testingWord=service.getRandomWord();
        word.setText(testingWord.getWord());
        options=new ArrayList<>();
        options.add(testingWord);
        options.add(service.getRandomWord());
        options.add(service.getRandomWord());
        options.add(service.getRandomWord());
        Collections.shuffle(options);
        a1RadioButton.setText(options.get(0).getRandomTranslate());
        a2RadioButton.setText(options.get(1).getRandomTranslate());
        a3RadioButton.setText(options.get(2).getRandomTranslate());
        a4RadioButton.setText(options.get(3).getRandomTranslate());
        a1RadioButton.setSelected(true);
    }
}
