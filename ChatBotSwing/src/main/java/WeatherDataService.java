import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static edu.stanford.nlp.pipeline.StanfordCoreNLP.OutputFormat.JSON;

public class WeatherDataService {
    public static final String QUERY_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";
    public static final String QUERY_WEATHER_ID = "https://www.metaweather.com/api/location/";
    public static ArrayList<String> weatherLookAhead;
    String cityId;
    String vanId = "9807";

    public ArrayList<String> getJson(String urlString) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlString)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(WeatherDataService::parse)
                .join();
        //System.out.println("------------------");
        //System.out.println(weatherLookAhead);
        return weatherLookAhead;
    }
    private String weather_state_abbr;
    private String wind_direction_compass;
    private String created;
    private String applicable_date;
    private float min_temp;
    private float max_temp;
    private float the_temp;
    private float wind_speed;
    private float wind_direction;
    private int air_pressure;
    private int humidity;
    private float visibility;
    private int predictability;
    public static ArrayList<String> parse(String responseBody) {

        JSONObject object;
        object = new JSONObject(responseBody);
        //System.out.println(object);
        JSONArray objects = object.getJSONArray("consolidated_weather");
        weatherLookAhead = new ArrayList<>();

        for (int i = 0; i < objects.length(); i++) {
            JSONObject inner_object = objects.getJSONObject(i);
            int id = inner_object.getInt("id");
            String weather_state = inner_object.getString("weather_state_name");
            Float max_temp = inner_object.getFloat("max_temp");
            Float min_temp = inner_object.getFloat("min_temp");
            String line = "";
            weatherLookAhead.add(weather_state + ", High of " + max_temp + ", Low of " + min_temp);
            //System.out.println(weather_state + " " + max_temp + " " + min_temp);
        }
        return weatherLookAhead;

    }


}
//    public static HttpURLConnection connection;
//
//    public void get(String urlString) {
//
//        BufferedReader reader;
//        String line;
//        StringBuffer response = new StringBuffer();
//        try {
//            URL url = new URL(urlString);
//            connection = (HttpURLConnection) url.openConnection();
//            //request setup
//            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(5000);
//            connection.setReadTimeout(5000);
//            int status = connection.getResponseCode();
//            //System.out.println(status);
//            if(status>200) {
//                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//                while(reader.readLine() != null) {
//                    line = reader.readLine();
//                    response.append(line);
//                }
//                reader.close();
//            }
//            else {
//                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                while(reader.readLine() != null) {
//                    line = reader.readLine();
//                    response.append(line);
//                }
//                reader.close();
//            }
//            System.out.println(response);
//            parse(response.toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            connection.disconnect();
//        }










