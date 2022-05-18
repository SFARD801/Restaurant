package com.example.selectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    CheckBox checkBoxSoup, checkBoxSushi, checkBoxPizza, checkBoxWater;
    Button buttonOrder, buttonReset;
    Switch switchDiet;
    String list = "", dietmsg = "Your Order will be served containing :";
    int i = 0;
    String marked = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxSoup = (CheckBox) findViewById(R.id.checkBoxSoup);
        checkBoxSoup.setOnCheckedChangeListener(this);
        checkBoxSushi = (CheckBox) findViewById(R.id.checkBoxSushi);
        checkBoxSushi.setOnCheckedChangeListener(this);
        checkBoxPizza = (CheckBox) findViewById(R.id.checkBoxPizza);
        checkBoxPizza.setOnCheckedChangeListener(this);
        checkBoxWater = (CheckBox) findViewById(R.id.checkBoxWater);
        checkBoxWater.setOnCheckedChangeListener(this);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        buttonOrder = (Button) findViewById(R.id.buttonOrder);
        switchDiet = (Switch) findViewById(R.id.switchDiet);

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //زیست کردن لیست سفارشات و بازنشانی کنترل ها به حالت اولیه
                i = 0;
                marked = "";
                list = "";
                dietmsg = "Your Order will be served containing :";
                if (checkBoxSoup.isChecked()) {
                    checkBoxSoup.setChecked(false);
                }
                if (checkBoxSushi.isChecked()) {
                    checkBoxSushi.setChecked(false);
                }
                if (checkBoxPizza.isChecked()) {
                    checkBoxPizza.setChecked(false);
                }
                if (checkBoxWater.isChecked()) {
                    checkBoxWater.setChecked(false);
                }
                if (switchDiet.isChecked()) {
                    switchDiet.setChecked(false);
                }

            }
        });

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //مطمئن بشیم که سفارش خالی نیست و درصورت خالی نبودن لیست سفارشات رو برامون نشون بده
                //دلیل تعیین کردن یک توست کاستوم، میخواستم تکست داخلش وسط قرار بگیره ;(setGravity.CENTER) با تشکر از https://stackoverflow.com/a/13492794/17593754

                Toast toast;
                if(list.isEmpty()){
                    //Toast.makeText(MainActivity.this, "Please make sure you have selected at least one item from the options above!", Toast.LENGTH_LONG).show();
                    toast = Toast.makeText(MainActivity.this, "Please make sure you have selected at least one item from the options above!", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if (v != null) v.setGravity(Gravity.CENTER);
                }
                else {

                    //Toast.makeText(MainActivity.this, dietmsg + list, Toast.LENGTH_SHORT).show();
                    toast = Toast.makeText(MainActivity.this, dietmsg + list, Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if (v != null) v.setGravity(Gravity.CENTER);
                }
                toast.show();
            }
        });
        switchDiet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean a) {
                //هم میشه توست داد هم یه استرینگ اماده کنیم تا بعدا نشونش بدیم
                if (a)
                    dietmsg = dietmsg.replace(dietmsg, "Your Order will be served lowFat containing :");
                    //Toast.makeText(MainActivity.this, "Your Order will be served in Low calories.", Toast.LENGTH_LONG).show();
                else
                    dietmsg = dietmsg.replace(dietmsg, "Your Order will be served containing :");
                    //Toast.makeText(MainActivity.this, "Your Order will be served in High calories.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String first = compoundButton.getText().toString();
        String second = ", " + compoundButton.getText().toString();

        if (b) {
            i++;
            //اگر چک باکس انتخابی اولین انتخاب باشد
            if (list.isEmpty()) {
                //list = list + compoundButton.getText();
                list = list + first;
                marked = first;
            }
            else {
                //list = list + ", " + compoundButton.getText();
                list = list + second;
            }
        }
        else {
            if (i>1) {
                if (compoundButton.getText().toString() == marked)
                    list = list.replace(first, "");
                else
                    list = list.replace(second, "");
            }
            else
                //list = list.replace(compoundButton.getText(), "");
                list = list.replace(first, "");
            i--;
        }
    }
}