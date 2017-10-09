package com.example.asus_x454l.quickjob;

/**
 * Created by ASUS_X454L on 9/23/2017.
 */
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import static android.app.Activity.RESULT_OK;

public class Tab1Fragment   extends Fragment{


    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private ImageView imageView;
    private EditText editText;
    private EditText editText1;

    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;
    private EditText editText7;

    private String userID;

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private FirebaseAuth mauthh;




    private Uri imguri;


    public static final String FB_Storage_Path = "image/";
    public static final String FB_Database_Path = "image";
    public static final int Request_Code = 1234;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container,false);

        //  databasejob = FirebaseDatabase.getInstance().getReference("jobs");
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(FB_Database_Path);


        imageView = (ImageView) view.findViewById(R.id.imageView);
        editText = (EditText) view.findViewById(R.id.txtImageName);

        editText1 = (EditText) view.findViewById(R.id.txtImageName1);
        editText2= (EditText) view.findViewById(R.id.txtImageName2);

        editText3 = (EditText) view.findViewById(R.id.txtImageName3);
        editText4 = (EditText) view.findViewById(R.id.txtImageName4);
        editText5 = (EditText) view.findViewById(R.id.txtImageName5);
        editText6 = (EditText) view.findViewById(R.id.txtImageName6);

        editText7 = (EditText) view.findViewById(R.id.txtImageName7);


        final Button button1 = (Button) view.findViewById(R.id.bu3);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnBrowse_Click(v);// Perform action on click
            }
        });




        final Button button3 = (Button) view.findViewById(R.id.bu);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnUpload_Click(v);
            }
        });
        return view;





    }


    public void btnBrowse_Click(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select image"), Request_Code);
    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imguri);
                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getImageExt(Uri uri) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @SuppressWarnings("VisibleForTests")
    public void btnUpload_Click(View v) {
        if (imguri != null) {
            final ProgressDialog dialog = new ProgressDialog(getActivity());
            dialog.setTitle("Uploading Info");
            dialog.show();

            //Get the storage reference
            StorageReference ref = storageReference.child(FB_Storage_Path + System.currentTimeMillis() + "." + getImageExt(imguri));

            //Add file to reference

            ref.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    //Dimiss dialog when success
                    dialog.dismiss();
                    //Display success toast msg
                    Toast.makeText(getActivity().getApplicationContext(), "Info uploaded", Toast.LENGTH_SHORT).show();


                    String id1 =      databaseReference.push().getKey();
                    String id22 =   userID.toString();
                    ImageUpload imageUpload = new ImageUpload(editText.getText().toString(),id1,id22,taskSnapshot.getDownloadUrl().toString(),editText1.getText().toString(),
                            editText2.getText().toString(), editText3.getText().toString(),editText4.getText().toString(),editText5.getText().toString(),
                            editText6.getText().toString(),editText7.getText().toString());

                    //Save image info in to firebase database
                    // String uploadId = databaseReference.push().getKey();
                    databaseReference.child(id1).setValue(imageUpload);





                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            //Dimiss dialog when error
                            dialog.dismiss();
                            //Display err toast msg
                            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            //Show upload progress

                            double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            dialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });














        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Please select image", Toast.LENGTH_SHORT).show();
        }
    }

   /* public void btnShowListImage_Click(View v) {
        Intent i = new Intent(Strorage.this, ImageListActivity.class);
        startActivity(i);
    }*/


}