import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document.Type;

import java.io.IOException;
import java.util.ArrayList;


public class GoogleNLP {
    Document doc;
    public GoogleNLP(String text) throws IOException {
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            // set content to the text string
            this.doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Document.Type.PLAIN_TEXT)
                    .build();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Float> getSentimentList() throws IOException {
        ArrayList<Float> sentiments = new ArrayList<>();
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
            Sentiment sentiment = response.getDocumentSentiment();
            if (sentiment == null) {
                //do nothing
            } else {
                sentiments.add(sentiment.getScore());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sentiments;
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
