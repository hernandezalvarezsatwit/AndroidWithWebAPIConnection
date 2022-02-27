package edu.wit.mobileapp.webapp;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
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
            //Stock: c8ddqliad3icuhjadat0
            URL url = new URL("https://www.boredapi.com/api/activity");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            if(inputStream == null)
                return "Data was not retrieved";
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
                String activity = root.getString("activity");
                activities.setText(activity);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
