package com.example.asus_x454l.quickjob;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText email_add;
    private EditText pass;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseuser3;
    private String userID3;

    private FirebaseAuth mAuth;
    //sensor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email_add=(EditText)findViewById(R.id.email_add);
        pass=(EditText)findViewById(R.id.pass);
        firebaseAuth=FirebaseAuth.getInstance();







        //apply code

        databaseuser3 = FirebaseDatabase.getInstance().getReference("flag");








    }

    public void sign_up(View v)
    {
        Intent ii=new Intent(MainActivity.this,reg_activity.class);
        startActivity(ii);
    }
    public void about(View v)
    {
        Intent ii=new Intent(MainActivity.this,About.class);
        startActivity(ii);
    }






    public void sign_in(View v)
    {


        final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "please wait....", "processing...", true);
        (firebaseAuth.signInWithEmailAndPassword(email_add.getText().toString().trim(), pass.getText().toString().trim()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Log in Successful", Toast.LENGTH_LONG).show();
                            mAuth = FirebaseAuth.getInstance();
                            FirebaseUser user3 = mAuth.getCurrentUser();
                            userID3 = user3.getUid();


                            databaseuser3.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot jobSnapshot : dataSnapshot.getChildren()) {
                                        flagcount u1 = jobSnapshot.getValue(flagcount.class);


                                        if (userID3.toString().equals(u1.getId1().toString())) {

                                            if (u1.getFlag().toString().contains("1")) {

                                                new_class.sendNotification(MainActivity.this, "job notificaation from "+u1.getEmail());
                                                u1.setFlag("0");
                                                ///get current user id..=flagk

                                                databaseuser3.child("fkk").setValue(u1);
                                                // Toast.makeText(MainActivity.this, u1.getFlag(), Toast.LENGTH_LONG).show();


                                            }

                                        }


                                    }


                                    //Joblist adapter = new Joblist(profile.this, jobListnew);
                                    // listViewjobnew.setAdapter(adapter);


                                }


                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });



















                            Intent mani2 = new Intent(MainActivity.this,navigation.class);
                            //String message =  email_add.getText().toString();
                            //   mani2.putExtra("nameID1", message);


                            String  m1="ppp";
                            mani2.putExtra("nameID2", m1);



                            startActivity( mani2);
                            //Intent i = new Intent(MainActivity.this, Demo.class);

                            //startActivity(i);










                        }
                        else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }
    public void googlef(View v)
    {
        Intent ii=new Intent(MainActivity.this,Google_sign_in.class);
        startActivity(ii);
    }


}
