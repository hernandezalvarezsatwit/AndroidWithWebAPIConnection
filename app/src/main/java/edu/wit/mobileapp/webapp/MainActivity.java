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
        Bitmap defaultImage = BitmapFactory.decodeResource(getResources(), R.drawable.cat);

        //Create list and populate it
        List<Card> cards = new ArrayList<>();
        Card card = new Card(defaultImage, "TSLA");
        Card card2 = new Card(defaultImage, "AAPL");

        cards.add(card);
        cards.add(card2);

        //Create manager, adapter, pass the list of cards
        LinearLayoutManager manager = new LinearLayoutManager(this);
        CardAdapter adapter = new CardAdapter(cards, this);

        //Create recycler view and set manager and adapter
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}