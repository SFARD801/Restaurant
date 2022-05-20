package com.example.selectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView Order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Order = (TextView) findViewById(R.id.textViewOutput);
        Order.setText("");
        //گرفتن اطلاعات و اعمال ان به تکست ویو
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //تکی تکی میگیریم استرینگ ها رو
        String OrderedFoods = bundle.getString("foodList");
        String OrderedFoodsType = bundle.getString("dietMessage");
        Order.setText( OrderedFoodsType + OrderedFoods);
    }
}