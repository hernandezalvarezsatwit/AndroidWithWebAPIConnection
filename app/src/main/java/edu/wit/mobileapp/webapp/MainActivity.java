package edu.wit.mobileapp.webapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        showCards();
    }

    private void showCards(){
        Bitmap def = getImage(R.drawable.generic);

        //Create list and populate it
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(getImage(R.drawable.tesla), "TSLA"));
        cards.add(new Card(getImage(R.drawable.apple), "AAPL"));
        cards.add(new Card(def, "FB"));
        cards.add(new Card(def, "EBAY"));
        cards.add(new Card(def, "MSFT"));
        cards.add(new Card(def, "AMZN"));
        cards.add(new Card(def, "MELI"));
        cards.add(new Card(def, "MELI"));
        cards.add(new Card(def, "MELI"));
        cards.add(new Card(def, "MELI"));
        cards.add(new Card(def, "MELI"));
        cards.add(new Card(def, "MELI"));
        cards.add(new Card(def, "MELI"));
        cards.add(new Card(def, "MELI"));

        //Create manager, adapter, pass the list of cards
        LinearLayoutManager manager = new LinearLayoutManager(this);
        CardAdapter adapter = new CardAdapter(cards, this);

        //Create recycler view and set manager and adapter
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private Bitmap getImage(int id){ return BitmapFactory.decodeResource(getResources(), id); }
}