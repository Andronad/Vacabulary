import lombok.Data;

import java.sql.*;
import java.util.ArrayList;

@Data
public class Service {
    private ArrayList<Word> vacabulary;
    private Connection con;
    private Statement stmt;
    public Service() throws SQLException {
        vacabulary=new ArrayList<>();
        con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/vacabulary","root","qweasd12");
        stmt=con.createStatement();
    }
    public void fillVacabulary(int type) throws SQLException {
        String slct="select * from words";
        ResultSet rs=stmt.executeQuery(slct);
        while(rs.next()){
            String word=rs.getString(2);
            String translate=rs.getString(3);
            if(type==1){
                addToVacabulary(word,translate);
            }
            else{
                addToVacabulary(translate,word);
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
    public void addToDB(String word, String translate) throws SQLException {
        String insrt="insert into words (word,translate,date) values(\""+word+
                "\",\""+translate+"\",NOW());";
        Boolean rs=stmt.execute(insrt);
        System.out.println(rs);
    }
    public Word getRandomWord(){
        return getVacabulary().get((int)Math.floor(Math.random()*this.getVacabulary().size()));
    }
}
