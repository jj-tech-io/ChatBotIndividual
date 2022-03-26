import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;

public class ParseNLP {
    CoreDocument coreDocument;
    StanfordCoreNLP stanfordCoreNLP;
    List<CoreSentence> sentenceList;
    List<CoreLabel> coreLabelList;
    public ParseNLP(String input) {
        stanfordCoreNLP = Pipeline.getPipeline();
        coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        coreLabelList = coreDocument.tokens();
    }
    public List<CoreSentence> getSentences(){
        sentenceList = coreDocument.sentences();
        return sentenceList;
    }
    public List<CoreLabel> getCoreLabelList() {
        return this.coreLabelList;
    }
    public ArrayList<String> getWords() {
        ArrayList<String> words = new ArrayList<>();
        String ot = " ";
        for(CoreLabel coreLabel: coreLabelList) {
            ot = coreLabel.originalText();
            words.add(ot);
        }
        return words;
    }
    public ArrayList<String> getNames() {
        ArrayList<String> nameList = new ArrayList<>();
        String ner = " ";
        for(CoreLabel coreLabel: coreLabelList) {
            ner = coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class);
            if (!ner.startsWith("O")) {
                nameList.add(coreLabel.originalText()+" = "+ner);
            }
        }
        return nameList;
    }
    public ArrayList<String> getCoreLemmaList() {
        ArrayList<String> lemmaList = new ArrayList<>();
        for(CoreLabel coreLabel: coreLabelList) {
            lemmaList.add(coreLabel.lemma());
        }
        return lemmaList;
    }
    public ArrayList<String> getStringList() {
        ArrayList<String> stringList = new ArrayList<>();
        for(CoreLabel coreLabel: coreLabelList) {
            stringList.add(coreLabel.originalText());
        }
        return stringList;
    }
    //pos is the code which represents what the word is
    //ie NN = Noun, singular or mass
    //see info_pos.txt
    public ArrayList<String> getPosList() {
        ArrayList<String> posList = new ArrayList<>();
        String pos = "";
        for(CoreLabel coreLabel: coreLabelList) {
            pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            posList.add(pos);
        }
        return posList;
    }

    public void addCorelabel(String input){

    }

    public String getSentiment() {
        String sentiment = "Neutral";
        getSentences();
        for(CoreSentence sentence: sentenceList) {
            if(sentence.sentiment().length()>1) {
                sentiment = sentence.sentiment();
            }
            System.out.println(sentiment);
        }
        return sentiment;
    }

    public ArrayList<String> getSentimentList() {
        ArrayList<String> sentiments = new ArrayList<>();
        String sentiment = "Neutral";
        getSentences();
        for(CoreSentence sentence: sentenceList) {
            sentiment = sentence.sentiment();
            sentiments.add(sentiment);
        }
        return sentiments;
    }

}