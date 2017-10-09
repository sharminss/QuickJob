package com.example.asus_x454l.quickjob;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class New_searcch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_searcch);
    }

    public void searchf(View view) {
        //Intent i = new Intent(Main2Activity.this,Advanced_search.class);
        //startActivity(i);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_layout, null);
        dialogBuilder.setView(dialogView);

        final Button location1 = (Button) dialogView.findViewById(R.id.location);
        final Button salary1 = (Button) dialogView.findViewById(R.id.salary);

        final Button  name1 = (Button) dialogView.findViewById(R.id.name);

        dialogBuilder.setTitle("choice");
        final AlertDialog b = dialogBuilder.create();
        b.show();


        location1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(New_searcch.this, Data_Retrieve_2.class);
                startActivity(i);
            }
        });


        salary1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(New_searcch.this, Data_Retrieve_3.class);
                startActivity(i);
            }
        });


        name1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(New_searcch.this,Data_Retrieve_1.class);
                startActivity(i);
            }
        });








    }
}
