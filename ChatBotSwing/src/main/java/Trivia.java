import java.util.*;

import static java.lang.Integer.*;

public class Trivia {
    public ArrayList<TriviaQuestion> TriviaQuestionsSet;
    public Trivia(){

        TriviaQuestionsSet = new ArrayList<>();
        String q = " What is the second Harry Potter book called? Harry Potter &";
        String [] a = {"The Goblet of Wizzards" , "The Cursed Child" , "The Chamber of Secrets" , "Half-Blood Prince"};
        TriviaQuestionsSet.add(new TriviaQuestion(q, a, "The Chamber of Secrets"));
        q = "In The Hobbit, what sort of creature is Smaug?";
        a = new String[]{"Dragon", "Wyvern", "Snake", "Rabbit"};
        TriviaQuestionsSet.add(new TriviaQuestion(q, a, "Dragon"));
        q = "The most sold book in the world.?";
        a = new String[] {"Bible", "Dictionary", "Travel guides", "Coding for dummies"};
        TriviaQuestionsSet.add(new TriviaQuestion(q, a, "Bible"));
        q = "Who was the author of the famous storybook ‘Alice’s Adventures in Wonderland'?";
        a  = new String[]{"Rudyard Kipling", "John Keats", "Lewis Carroll", "H G Wells"};
        TriviaQuestionsSet.add(new TriviaQuestion(q, a, "Lewis Carroll"));
        q = "Name the book which opens with the line ‘All children, except one grew up’?";
        a  = new String[]{"The Railway Children", "Winnie the Poo", "Jungle book", "Peter Pan"};
        TriviaQuestionsSet.add(new TriviaQuestion(q, a, "Peter Pan"));
        q = "Who wrote the famous 1855 poem ‘The Charge of the Light Brigade’?";
        a  = new String[]{"Lord Alfred Tennyson", "Christopher Marlowe", "Johannes Gutenberg","René Descartes"};
        TriviaQuestionsSet.add(new TriviaQuestion(q, a, "Lord Alfred Tennyson"));
        q = "Which is the first Harry Potter book?";
        a  = new String[]{"HP and the Goblet of Fire", "HP and the Philosopher’s Stone", "HP and the Chamber of Secrets", "HP and the God of small Things"};
        TriviaQuestionsSet.add(new TriviaQuestion(q, a, "HP and the Philosopher’s Stone"));
        q = "What was the nationality of Robert Louis Stevenson, writer of ‘Treasure Island'? ";
        a = new String[]{"Scottish", "Welsh", "Irish", "French"};
        TriviaQuestionsSet.add(new TriviaQuestion(q, a, "Scottish"));
        q = "What is the book ‘Lord of the Flies’ about";
        a = new String[]{"A round trip around the USA","A swarm of killer flies", "Schoolboys on the desert island", "None of the above"};
        TriviaQuestionsSet.add(new TriviaQuestion(q, a, "Schoolboys on the desert island"));
        q = "Which book won the first Man Booker Prize?";
        a = new String[]{"Troubles", "Something to Answer For", "The Conservationist","Heat and Dust"};
        TriviaQuestionsSet.add(new TriviaQuestion(q, a, "Something to Answer For"));
        q = "Who wrote the fantasy novel ‘The Lord of the Rings'?";
        a = new String[]{"J. R. R. Tolkien", "Peter Jackson", "S. Lewis", "J. K. Rowling "};
        TriviaQuestionsSet.add(new TriviaQuestion(q, a, "J. R. R. Tolkien"));
        Collections.shuffle(TriviaQuestionsSet, new Random());
    }

    public void play() throws InterruptedException {
        int score = 0;
        //Question generation
        for (int question =0; question<5; question++){
            GUI.cbMsg = TriviaQuestionsSet.get(question).getTriviaQuestion();
            GUI.getCBM(GUI.cbMsg);
            int numOptions = TriviaQuestionsSet.get(question).getOptions().size();

            //Show options available for each question
            for(int option= 0; option < numOptions; option++){
                GUI.cbMsg = (option + 1)+": "+ TriviaQuestionsSet.get(question).getOptions().get(option);
                GUI.getCBM(GUI.cbMsg);
            }
            GUI.getUserIN();
            String reply = GUI.userMsg;
            ArrayList<String> optionSet = TriviaQuestionsSet.get(question).getOptions();
            String correctOption = TriviaQuestionsSet.get(question).getAnswer();
            int correctOptionIndex = optionSet.indexOf(correctOption);
            if (String.valueOf(correctOptionIndex + 1).contains(reply.trim())){
                score++;
            }
        }
        GUI.cbMsg = "You scored: " + score + "/5\n";
        GUI.getCBM(GUI.cbMsg);

    }

}