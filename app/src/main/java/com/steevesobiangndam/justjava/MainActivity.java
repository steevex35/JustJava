package com.steevesobiangndam.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    /**
     * This methode is called for increment value of quantity
     */
    public void increment(View view){
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This methode is called for decrease value of quantity
     * @param view
     */
    public void decrement(View view){
        if (quantity>0) {
            quantity--;
            displayQuantity(quantity);
        }
        else {
            quantity = 0;
            displayQuantity(quantity);
        }
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price=calculatePrice(quantity);

        CheckBox whippedCreamChekBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolat = (CheckBox) findViewById(R.id.chocolat_checkbox);
        EditText name = (EditText) findViewById(R.id.name_EditText);

        String nameString= name.getText().toString();
        boolean haschocolat= chocolat.isChecked();
        boolean hasWhippedcream = whippedCreamChekBox.isChecked();
        //Log.v("MainActivity","check -->"+hasWhippedcream);
        String priceMessage=createOrderSummary(quantity, nameString,price,hasWhippedcream,haschocolat);
        displayMessage(priceMessage);
    }

    /**
     * Calculate price methode
     */
   private int calculatePrice(int quantity){
        int price=quantity*5;
        return price;
    }
    private String createOrderSummary(int number,String name,int price, boolean addWhipperCream, boolean addChocolat){

        String Message="Name: "+name;
               Message+= "\nAdd whipped cream? "+addWhipperCream;
                Message+="\nAdd chocolat? "+addChocolat;
               Message+="\nQuantity: "+number;
                Message+="\nTotal: £"+price;
                Message+="\nThank You !";
        return Message;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);


        orderSummaryTextView.setText(message);
    }
}