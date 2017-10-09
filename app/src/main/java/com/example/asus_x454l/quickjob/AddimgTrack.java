package com.example.asus_x454l.quickjob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddimgTrack extends AppCompatActivity {

    TextView TextViewJobName;
    TextView TextViewSalary;
    TextView TextViewTime;
    TextView TextViewExp;
    TextView TextViewEdu;
    TextView TextViewReq;
    String idt2;
    private DatabaseReference databaseReference1  ;
    TextView TextViewCon;
    TextView TextViewAdd;

    DatabaseReference databaseuser2;

    String val;
    private FirebaseAuth mAuth;

    private String userID3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addimg_track);

        databaseReference1 = FirebaseDatabase.getInstance().getReference("flag");
        TextViewJobName=(TextView)findViewById(R.id.jobnames);
        TextViewSalary=(TextView)findViewById(R.id.salarys);
        TextViewTime=(TextView)findViewById(R.id.times);
        TextViewExp=(TextView)findViewById(R.id.exps);
        TextViewEdu=(TextView)findViewById(R.id.edus);
        TextViewReq=(TextView)findViewById(R.id.reqs);
        TextViewCon=(TextView)findViewById(R.id.contacts);
        TextViewAdd=(TextView)findViewById(R.id.adds);



        databaseuser2 = FirebaseDatabase.getInstance().getReference("user1");






        Intent intent=getIntent();


        String idt=intent.getStringExtra(ImageListActivity.JOB_ID);
        idt2=intent.getStringExtra(ImageListActivity.JOB_ID1);
        //  String idt=intent.getStringExtra(profile.JOB_ID);
        String namet=intent.getStringExtra(ImageListActivity.JOB_NAME1);
        String salaryt=intent.getStringExtra(ImageListActivity.SALARY2);

        String timet=intent.getStringExtra(ImageListActivity.TIME);

        String expt=intent.getStringExtra(ImageListActivity.EXP);

        String edut=intent.getStringExtra(ImageListActivity.EDU);
        String reqt=intent.getStringExtra(ImageListActivity.REQ);
        String contt=intent.getStringExtra(ImageListActivity.CON);
        String addt=intent.getStringExtra(ImageListActivity.ADDR);


        TextViewJobName.setText(namet);
        TextViewSalary.setText(salaryt);
        TextViewTime.setText(timet);
        TextViewExp.setText(expt);
        TextViewEdu.setText(edut);
        TextViewReq.setText(reqt);
        TextViewCon.setText(contt);
        TextViewAdd.setText(addt);

    }
    public void apply_m(View view)
    {


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user3 = mAuth.getCurrentUser();
        userID3 = user3.getUid();



        databaseuser2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // jobListnew.clear();
                for (DataSnapshot jobSnapshot : dataSnapshot.getChildren()) {
                    UserInformation   u = jobSnapshot.getValue(UserInformation.class);

                    if (userID3.toString().equals(u.getId2().toString())) {



                        val=u.getEmail();

                        //    Toast.makeText(AddimgTrack.this, val, Toast.LENGTH_LONG).show();

                        flagcount f=new flagcount("1", val,idt2);
                        Toast.makeText(AddimgTrack.this,"notificatn sent ",Toast.LENGTH_SHORT).show();
                        databaseReference1.child("fkk").setValue(f);






                    }

                    // jobListnew.add(jobnew);
                }


                //Joblist adapter = new Joblist(profile.this, jobListnew);
                // listViewjobnew.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
