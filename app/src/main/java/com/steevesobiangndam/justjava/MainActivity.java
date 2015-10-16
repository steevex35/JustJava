package com.steevesobiangndam.justjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayQuantity(quantity);


    }
    /**
     * This methode is called for increment value of quantity
     */
    public void increment(View view){
        if (quantity<100) {
            quantity++;
            displayQuantity(quantity);
        }
        else {
            quantity = 100;
            displayQuantity(quantity);
            Toast toast = Toast.makeText(this, "Impossible de faire plus de café", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * This methode is called for decrease value of quantity
     * @param view
     */
    public void decrement(View view){
        if (quantity>1) {
            quantity--;
            displayQuantity(quantity);
        }
        else {
            quantity =1;
            displayQuantity(quantity);
            Toast toast = Toast.makeText(this,"Impossible de commander moins de café", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        CheckBox whippedCreamChekBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolat = (CheckBox) findViewById(R.id.chocolat_checkbox);
        EditText name = (EditText) findViewById(R.id.name_EditText);

        String nameString= name.getText().toString();
        boolean haschocolat= chocolat.isChecked();
        boolean hasWhippedcream = whippedCreamChekBox.isChecked();
        //Log.v("MainActivity","check -->"+hasWhippedcream);
        int price=calculatePrice(quantity, hasWhippedcream, haschocolat);
        String priceMessage=createOrderSummary(quantity, nameString,price,hasWhippedcream,haschocolat);
        displayMessage(priceMessage);
        //pour envoyer la facture en email
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_SUBJECT,"JustJava order for "+nameString);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent.createChooser(intent, "Send Email"));
        }
    }

    /**
     *
     * @param quantity numbero of coffee
     * @param addCream state checkBox for add or not a whippedCream
     * @param addChocolat state checkBox for add or not a chocolat
     * @return final price
     */
   private int calculatePrice(int quantity, boolean addCream, boolean addChocolat){
       int basePrice=5;
       if(addCream) // add 1$ if addCream is true (isChecked)
           basePrice=basePrice+1;
       if(addChocolat)// add 2$ if addChocolate is true (isChecked)
          basePrice=basePrice+2;
        return basePrice*quantity;
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