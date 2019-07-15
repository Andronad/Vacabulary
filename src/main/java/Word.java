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
}
