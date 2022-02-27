package edu.wit.mobileapp.webapp;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


public class FetchAnActivity extends AsyncTask<Void, Void, String> {

    TextView activities;
    ProgressBar progressBar;

    public FetchAnActivity(TextView activities, ProgressBar progressBar) {
        this.activities = activities;
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            //Weather: https://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=458514581d29fd64d5ba0ececf114335
            //Stock: X5N8KPH4CUJ9C5YP
            //weather 2: 5f0c4bbeb2294551b9431534222702

            String weather = "http://api.openweathermap.org/data/2.5/find?q=Boston&units=imperial&appid=458514581d29fd64d5ba0ececf114335";
            String w = "http://api.weatherapi.com/v1/current.json?key=5f0c4bbeb2294551b9431534222702&q=Boston&aqi=no";
            //https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=TSLA&interval=5min&outputsize=full&apikey=X5N8KPH4CUJ9C5YP
            //https://www.boredapi.com/api/activity
            String stock = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=TSLA&interval=5min&outputsize=full&apikey=X5N8KPH4CUJ9C5YP";
            URL url = new URL(stock);

            //HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();

            if(inputStream == null) {
                Log.v("myApp", "Not retrieved");
                return "Data was not retrieved";
            }
            Log.v("myApp", "retrieved");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder sb = new StringBuilder();
            while((line=br.readLine()) != null)
                sb.append(line);
            return sb.toString();
        } catch (Exception e) {
            System.out.println("Problem loading the URL or establishing connection. Review method doInBackground");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressBar.setVisibility(View.INVISIBLE);
        if(s.equals("Data was not retrieved"))
            activities.setText("Data is not fetched for some reason");
        else{
            try {
                JSONObject root = new JSONObject(s);
                String activity = root.getString("Meta Data");
                activities.setText(activity);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
