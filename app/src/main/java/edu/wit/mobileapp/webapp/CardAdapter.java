package edu.wit.mobileapp.webapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * This class creates an adapter for the CardView to use to display all the cards
 * @author Samuel Hernandez
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<Card> list;
    private LayoutInflater inflater;

    //Constructor. Constructs object and sets inflater and list
    public CardAdapter(List<Card> itemsList, Context context){
        inflater = LayoutInflater.from(context);
        list = itemsList;
    }

    //View Holder class to create custom object to return with RecyclerView's onCreateViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView titleTextV, descriptionTextV;

        //Constructor of ViewHolder receives View
        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.imageView);
            titleTextV = view.findViewById(R.id.titleView);
            descriptionTextV = view.findViewById(R.id.descriptionView);
        }
        //Fetch data, execute, and set data
        void bindData(Card item){
            FetchStock fetch = new FetchStock(descriptionTextV,item.getStockName());
            fetch.execute();
            imageView.setImageBitmap(item.getImage());
            titleTextV.setText(item.getStockName());
        }
    }

    //Inflates the card layout
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.card_layout, null);
        return new ViewHolder(view);
    }
    //Binds a card given by position
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position){
        holder.bindData(list.get(position));
    }
    //Get size of list
    public int getItemCount(){
        return list.size();
    }
}
