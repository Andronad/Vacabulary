import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Word {
    private String word;
    private ArrayList<String> translate;
    public void addTranslate(String s){
        translate.add(s);
    }
    public String getAllTranslates(){
        String all=new String();
        for (String s: translate){
            all=all.concat(s+",");
        }
        all=all.substring(0,all.length()-1);
        return all;
    }
    public String getRandomTranslate(){
        return translate.get((int)Math.random()*translate.size());
    }
}
