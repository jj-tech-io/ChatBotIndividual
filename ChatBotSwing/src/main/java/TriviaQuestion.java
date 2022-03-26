import java.lang.String;
import java.util.*;


public class TriviaQuestion {
    private String prompt;
    private ArrayList<String> options;
    private String answer;

    public TriviaQuestion(String prompt, String[] options, String answer) {
        this.prompt = prompt;
        this.options = new ArrayList<String>();
        this.answer = answer;
        for (int i = 0; i < options.length; i++){
            this.options.add(options[i]);
        }
        //Shuffling questions
        //Collections.shuffle(this.options);
        this.answer = answer;
    }
    public String getTriviaQuestion(){
        return prompt;
    }
    public ArrayList<String> getOptions(){
        return options;
    }
    public String getAnswer(){
        return answer;
    }
}