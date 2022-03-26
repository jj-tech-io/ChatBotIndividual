import junit.framework.TestCase;

import java.io.IOException;
import java.util.ArrayList;

public class GoogleNLPTest extends TestCase {

    public void testGetSentimentList() throws IOException {
        GoogleNLP googleNLP = new GoogleNLP("yes");
        ArrayList<Float> sentList = googleNLP.getSentimentList();
        System.out.println(sentList.get(0));
        //assertFalse(sentList.size() == 0);
        boolean result = sentList.get(0) >0f;
        assertEquals(true,result);
        System.out.println(sentList);
    }

    public void testGetNames() throws IOException {
        GoogleNLP googleNLP = new GoogleNLP("my name is Joel");
        ArrayList<String> nameList = googleNLP.getNames();
        System.out.println(nameList);
        assertEquals("Joel", nameList.get(0));
    }
}