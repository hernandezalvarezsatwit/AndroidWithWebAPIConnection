package edu.wit.mobileapp.webapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/**
 * This application allows the user to view his or her favorite stock data connecting the app
 * The application gets stock information using Alpha Vantage's stock API. It displays the
 * open and close values for the most recent period of 60 minutes available on the API.
 * It reads the JSON provided by the API and displays the results in a RecyclerView using CardView
 * Notice Alpha non-premium API allows only for 5 requests per minute. That is the reason for
 * "Data not available" message. Could be solved by paying for premium version.
 * @author Samuel Hernandez
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        showCards();
    }

    //Method creates and shows the cards
    private void showCards(){
        Bitmap def = getImage(R.drawable.generic);          //Default image

        //Create list of cards
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(getImage(R.drawable.tesla), "TSLA"));
        cards.add(new Card(getImage(R.drawable.apple), "AAPL"));
        cards.add(new Card(getImage(R.drawable.fb), "FB"));
        cards.add(new Card(getImage(R.drawable.ebay), "EBAY"));
        cards.add(new Card(getImage(R.drawable.msft), "MSFT"));
        cards.add(new Card(getImage(R.drawable.amzn), "AMZN"));
        cards.add(new Card(getImage(R.drawable.tesla), "TSLA"));
        cards.add(new Card(getImage(R.drawable.apple), "AAPL"));
        cards.add(new Card(getImage(R.drawable.fb), "FB"));
        cards.add(new Card(getImage(R.drawable.ebay), "EBAY"));
        cards.add(new Card(getImage(R.drawable.msft), "MSFT"));
        cards.add(new Card(getImage(R.drawable.amzn), "AMZN"));

        //Create manager, adapter, pass the list of cards
        LinearLayoutManager manager = new LinearLayoutManager(this);
        CardAdapter adapter = new CardAdapter(cards, this);

        //Create recycler view and set manager and adapter
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    //Simple method, just to get an image easily
    private Bitmap getImage(int id){ return BitmapFactory.decodeResource(getResources(), id); }
}