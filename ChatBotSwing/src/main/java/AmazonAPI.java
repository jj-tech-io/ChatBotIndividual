import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;


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

public class AmazonAPI {
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
        return weatherLookAhead;
    }

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
            weatherLookAhead.add(weather_state + ", High of " + max_temp + ", Low of " + min_temp+'\n');
            //System.out.println(weather_state + " " + max_temp + " " + min_temp);
        }
        return weatherLookAhead;

    }


}