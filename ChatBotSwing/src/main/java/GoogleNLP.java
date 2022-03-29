import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document.Type;
import java.io.IOException;
import java.util.ArrayList;

public class GoogleNLP {
    Document doc;
    String text;
    public GoogleNLP(String text) throws IOException {
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            // set content to the text string
            this.doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Document.Type.PLAIN_TEXT)
                    .build();
            this.text = text;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> getWords() {
        ArrayList<String> words = new ArrayList<>();
        OpenNLP openNLP = new OpenNLP(this.text);
        String[] tokens  = openNLP.tokens;
        for (String s : tokens){
            if(s.length()>1) {
                words.add(s);
            }
        }
        return words;
    }
    public ArrayList<Float> getSentimentList() throws IOException {
        ArrayList<Float> sentiments = new ArrayList<>();
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
            Sentiment sentiment = response.getDocumentSentiment();
            if (sentiment == null) {
                //System.out.println("No sentiment found");
            } else {
                sentiments.add(sentiment.getScore());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sentiments;
    }

    public Float getSentiment() throws IOException {
        ArrayList<Float> sentiments = new ArrayList<>();
        Float sentimentFloat = 0f;
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
            Sentiment sentiment = response.getDocumentSentiment();
            if (sentiment == null) {
                //System.out.println("No sentimentFloat found");
            } else {
                sentiments.add(sentiment.getScore());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sentiments.size()>0){
            sentimentFloat = sentiments.get(0);
        }
        return sentimentFloat;
    }
    public ArrayList<String> getNames() throws IOException {
        ArrayList<String> names = new ArrayList<>();
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            AnalyzeEntitiesRequest request =
                    AnalyzeEntitiesRequest.newBuilder()
                            .setDocument(doc)
                            .setEncodingType(EncodingType.UTF16)
                            .build();
            AnalyzeEntitiesResponse response = language.analyzeEntities(request);
            // Print the response
            for (Entity entity : response.getEntitiesList()) {
                for (EntityMention mention : entity.getMentionsList()) {
                    if(mention.getType().toString()=="PROPER") {
                        if(!names.contains(entity.getName()))
                            names.add(entity.getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }


}