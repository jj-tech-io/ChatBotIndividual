import junit.framework.TestCase;

import java.io.IOException;

public class ChatBotTest extends TestCase {

    public void testTestReaction() throws IOException {
        String reaction = "No way, I hate automata";
        ChatBot chatBot = new ChatBot();
        Float sent = chatBot.testReaction(reaction);
        boolean t_f = sent<0f;
        assertEquals(t_f, false);
        reaction = "Yes, I love robots!";
        t_f = sent>0f;
        sent = chatBot.testReaction(reaction);
        assertEquals(sent, true);
    }

    public void testGetConsolation() {
        String reaction = "No way, I hate automata";
        ChatBot chatBot = new ChatBot();
        String str = chatBot.getConsolation(3);
        assertEquals(str, "Ok, lets try, Searching...");
    }

    public void testGetStatement() {
    }

    public void testTestGetStatement() {
    }

    public void testAskByName() {
    }

    public void testGetPeople() {
    }

    public void testLoopGeneraTitle() {
    }

    public void testLoopGeneraTitleMovie() {
    }

    public void testGetAutonomous() {

        ChatBot chatBot = new ChatBot();
        String autonomous = chatBot.getAutonomous("I am not sure what i want to do here, i am bored");
        System.out.println(autonomous);
    }
}