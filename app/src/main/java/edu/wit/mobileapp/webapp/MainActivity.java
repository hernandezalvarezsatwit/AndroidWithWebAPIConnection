package edu.wit.mobileapp.webapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getStockCards();
    }

    private void getStockCards() {
        TextView teslaCard = findViewById(R.id.teslaStockView);
        TextView appleCard = findViewById(R.id.appleStockView);

        FetchStock fetch = new FetchStock(teslaCard, "TSLA");
        fetch.execute();

        fetch = new FetchStock(appleCard,"AAPL");
        fetch.execute();

    }
}