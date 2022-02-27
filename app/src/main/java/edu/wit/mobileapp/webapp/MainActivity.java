package edu.wit.mobileapp.webapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bitmap defaultImage = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        TextView teslaCard = findViewById(R.id.teslaStockView);

        //Create list and populate it
        String description = "This is just a placeholder for an actual description";
        List<Card> cards = new ArrayList<>();
//        String res = fetch.result;
//        Log.v("myApp", "Data: "+res);

        Card card = new Card(defaultImage, "TSLA", "res");
        //FetchStock fetch = new FetchStock(teslaCard,"TSLA", card);

        //fetch.execute();
        //String r = fetch.getResult();
        //Log.v("myApp", "Data: "+r);


        cards.add(card);
//        List<Card> cards = IntStream.range(1, 101).mapToObj(i ->
//                new Card(defaultImage, "Title "+i, description)).collect(Collectors.toList());

        //Create manager, adapter, pass the list of cards
        LinearLayoutManager manager = new LinearLayoutManager(this);
        CardAdapter adapter = new CardAdapter(cards, this);
        //Create recycler view and set manager and adapter
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        //getStockCards();
    }

    private void getStockCards() {
        TextView teslaCard = findViewById(R.id.teslaStockView);
        TextView appleCard = findViewById(R.id.appleStockView);

        //FetchStock fetch = new FetchStock(teslaCard, "TSLA");
        //fetch.execute();

//        fetch = new FetchStock(appleCard,"AAPL");
//        fetch.execute();

    }
}