package edu.wit.mobileapp.webapp;
import android.graphics.Bitmap;

/**
 * This object represents each individual card
 * @author Samuel Hernandez
 */
public class Card {

    private Bitmap image;
    private String stockName;
    private String stockInfo;

    public Card(Bitmap image, String stockName) {
        this.image = image;
        this.stockName = stockName;
    }

    //Getters and setters
    public Bitmap getImage() { return image; }
    public String getStockName() { return stockName; }
    public String getStockInfo() { return stockInfo; }
    public void setImage(Bitmap image) { this.image = image; }
    public void setStockName(String stockName) { this.stockName = stockName; }
    public void setStockInfo(String stockInfo) { this.stockInfo = stockInfo; }
}