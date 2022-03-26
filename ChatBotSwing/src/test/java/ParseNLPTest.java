import edu.stanford.nlp.pipeline.CoreSentence;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

public class ParseNLPTest extends TestCase {

    public void testGetSentimentList() {
        ParseNLP parseNLP = new ParseNLP(
                "I love robots. " +
                "Books are stupid! " +
                "I am 24 years old.");
        ArrayList<String> sentList = parseNLP.getSentimentList();
        assertEquals(sentList.get(0).toString(),"Positive");
        assertEquals(sentList.get(1).toString(),"Negative");
        assertEquals(sentList.get(2).toString(),"Neutral");
    }

    public void testGetStringList() {
        ParseNLP parseNLP = new ParseNLP("I want Dune");
        ArrayList<String> words = parseNLP.getWords();
        assertEquals(words.size(),3);
        assertEquals(words.get(2),"Dune");
        System.out.println(words);
    }

    public void testGetSentences() {
        ParseNLP parseNLP = new ParseNLP("I want Dune. If you don't have any good sci-fi, i'll go to amazon, i am not kidding! rawr.");
        List<CoreSentence> sentences = parseNLP.getSentences();
        assertEquals(sentences.size(),3);
        assertEquals(sentences.get(0).toString(),"I want Dune.");
    }

    public void testGetCoreLabelList() {
    }

    public void testGetWords() {
    }

    public void testGetNames() {
    }

    public void testGetCoreLemmaList() {
    }

    public void testTestGetStringList() {
    }

    public void testGetPosList() {
    }

    public void testAddCorelabel() {
    }

}