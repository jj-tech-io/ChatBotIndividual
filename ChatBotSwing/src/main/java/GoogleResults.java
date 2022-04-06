import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class GoogleResults {

    static ArrayList<String> listResult;

    public ArrayList<String> getJson(String query) throws MalformedURLException {
        String key="AIzaSyBYsfIpZXyvVHvaavpH5_0XxdCaueWUcDw";
        URL url = new URL("https://www.googleapis.com/customsearch/v1?key="+key);
        String cx="37e43e2d07140f5c7";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(
                URI.create(
                "https://www.googleapis.com/customsearch/v1?key="
                +key+"&cx="+cx+"&q="+query)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(GoogleResults::parse)
                .join();
        return listResult;
    }
    public static ArrayList<String> parse(String responseBody) {

        JSONObject object;
        object = new JSONObject(responseBody);
        //System.out.println(object);
        JSONArray objects = object.getJSONArray("items");
        //System.out.println(objects.toString());
        listResult = new ArrayList<>();
        System.out.println(listResult);
        for (int i = 0; i < objects.length(); i++) {
            JSONObject inner_object = objects.getJSONObject(i);
            String title = inner_object.getString("title");
            String link = inner_object.getString("link");
            String snippet = inner_object.getString("snippet");
            String line = "";
            listResult.add(
                            title + '\n'+
                            link + '\n'+
                            snippet + '\n'+" "
            );
            //System.out.println(listResult);
        }
        return listResult;

    }
}
