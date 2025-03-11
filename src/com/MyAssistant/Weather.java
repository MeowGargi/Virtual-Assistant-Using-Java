package com.MyAssistant;
import java.net.URLEncoder;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Weather {
/*  private static  double temp;
    private static int humidity;
    private static String description;
    private static  double windSpeed;         // not a good practice!!!!
    private static  String cityName;

*/
    private static final String API_KEY = "YOUR API KEY";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";


    public static String getWeather(String city){

    String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
    String urlString = BASE_URL + encodedCity + "&appid=" + API_KEY + "&units=metric";
    try {
       // URL url = new URL(urlString);    // deprecated!!

        URI uri =  new URI(urlString);
        URL url = uri.toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader =  new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String lineByLine;

        while((lineByLine = reader.readLine())!= null){
            response.append(lineByLine);
        }

        JSONObject object = new JSONObject(response.toString());
        JSONObject main = object.getJSONObject("main");
        Double temp = main.getDouble("temp");
        int humidity = main.getInt("humidity");

       String description = object.getJSONArray("weather").getJSONObject(0).getString("description");

        JSONObject wind = object.getJSONObject("wind");
        double windSpeed = wind.getDouble("speed");
        String cityName = object.getString("name");


        return  String.format(
                "Weather in %s: \n" +
                        "Temperature: %.2f°C \n"+
                        "Humidity: %d%%\n"+
                        "Wind Speed: %.2f m/s\n" +
                        "Condition: %s",
                cityName,temp,humidity,windSpeed,description

        );

    } catch (Exception e){
        return "Exception occurred!";
    }



    }
}
