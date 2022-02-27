package edu.wit.mobileapp.webapp;

import android.os.AsyncTask;
import android.widget.TextView;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class FetchStock extends AsyncTask<Void, Void, String> {

    private TextView textStockView;
    private String stockName;

    public FetchStock(TextView textStockView, String stockName) {
        this.textStockView = textStockView;
        this.stockName = stockName;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return getStockData(stockName);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textStockView.setText(s);
    }

    private static String getStockData(String company) {
        String stockData = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+company+"&interval=5min&outputsize=full&apikey=X5N8KPH4CUJ9C5YP";
        Object obj = null;
        try {
            obj = new JSONParser().parse(getJsonFrom(stockData));
        } catch (ParseException | IOException e) {
            System.out.println("---> Problem parsing JSON of stockData or Buffer Reader error");
            //Log.v("myApp", "Problem parsing JSON of stockData or Buffer Reader error");
            e.printStackTrace();
        }
        org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;
        Map stockHeader = (HashMap) jo.get("Meta Data");
        if(stockHeader == null)
            return "Data not available";
        String last = stockHeader.get("3. Last Refreshed").toString();
        Map stockPrices = (HashMap) jo.get("Time Series (5min)");
        String lastData = stockPrices.get(last).toString();
        Object obj2 = null;
        try {
            obj2 = new JSONParser().parse(lastData);
        } catch (ParseException e) {
            System.out.println("---> Problem parsing JSON of last prices of stock");
            //Log.v("myApp", "Problem parsing JSON of last prices of stock");
            e.printStackTrace();
        }
        org.json.simple.JSONObject refreshedData = (org.json.simple.JSONObject) obj2;
        String open = "Not Available";
        String temp = refreshedData.get("1. open").toString();
        if(!temp.equals(""))
            open = temp;

        String close = "Not Available";
        String temp2 = refreshedData.get("4. close").toString();
        if(!temp2.equals(""))
            close = temp2;

        String result = "Open: " + open + "\n" + "Close: " + close;
        return result;
    }

    private static String getJsonFrom(String link) throws IOException {
        //Retrieve info from URL
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            System.out.println("---> URL not retrieved correctly. URL: "+link);
            //Log.v("myApp", "URL not retrieved correctly. URL: "+link);
            e.printStackTrace();
        }

        //HTTP Connect
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (Exception e) {
            System.out.println("---> HTTP Connection Problem");
            //Log.v("myApp", "HTTP Connection Problem");
            e.printStackTrace();
        }

        //Get Input stream
        InputStream inputStream = null;
        try {
            inputStream = connection.getInputStream();
        } catch (Exception e) {
            System.out.println("---> Input Stream Problem");
            //Log.v("myApp", "Input Stream Problem");
            e.printStackTrace();
        }
        if(inputStream == null) {
            System.out.println("---> Data was not retrieved");
            //Log.v("myApp", "Data was not retrieved"");
            return "Data was not retrieved";
        }

        //Get information into string
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null)
            sb.append(line);

        br.close();
        inputStream.close();
        return sb.toString();
    }
}
