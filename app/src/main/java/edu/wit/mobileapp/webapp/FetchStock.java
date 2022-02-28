package edu.wit.mobileapp.webapp;

import android.os.AsyncTask;
import android.widget.TextView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class extends AsyncTask to fetch resources from the web using an API
 * It fetches information about a stock and puts it into a textView object.
 * @author Samuel Hernandez
 */
public class FetchStock extends AsyncTask<Void, Void, String> {

    private TextView textStockView;
    private String stockName;

    public FetchStock(TextView textStockView, String stockName) {
        this.textStockView = textStockView;
        this.stockName = stockName;
    }

    //Service: In background get te data
    @Override
    protected String doInBackground(Void... voids) {
        try { return getStockData(stockName);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error. Verify internet connection";
        }
    }

    //After getting data set the textView with the result
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textStockView.setText(s);
    }

    /**
     * This method gets stock information for a given company. It first creates the URL with the
     * company name, and then calls {@link #getJsonFrom(String)} to get data in JSON form from url
     * Lastly, it extracts the desired information in the JSON and creates a formatted
     * string that will be used to display in the app.
     * @param company the name of the company to get the stock information
     * @return the formatted string to set in the textView
     */
    private static String getStockData(String company) throws IOException, ParseException {
        //Create url
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol" +
                "="+company+"&interval=60min&outputsize=full&apikey=X5N8KPH4CUJ9C5YP";
        //Parse json retrieved from url
        JSONObject json = (JSONObject) new JSONParser().parse(getJsonFrom(url));

        //Get desired data for string
        Map stockHeader = (HashMap) json.get("Meta Data");
        if(stockHeader == null) return "Data not available";
        String last = stockHeader.get("3. Last Refreshed").toString();
        Map stockPrices = (HashMap) json.get("Time Series (60min)");
        String lastData = stockPrices.get(last).toString();
        json = (JSONObject) new JSONParser().parse(lastData);
        String open = json.get("1. open").toString();
        String close = json.get("4. close").toString();
        return "Open: " + open + "\n" + "Close: " + close;
    }

    //Method connects to internet,gets input stream, and puts it into a string
    private static String getJsonFrom(String link) throws IOException {
        URL url = new URL(link);                                                 //Retrieve url
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //HTTP Connect
        InputStream inputStream = connection.getInputStream();                   //Get Input stream

        //Get information into string
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String data = reader.lines().collect(Collectors.joining());

        //Close resources
        reader.close();
        inputStream.close();
        connection.disconnect();
        return data;
    }
}
