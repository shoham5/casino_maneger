package com.example.shoham.loginscreen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
private RecyclerView mRecyclerView;
private RecyclerView.Adapter mAdapter;
private RecyclerView.LayoutManager mLayoutManager;

    private static final String TAG = "PostDetailActivity";

    //private RecyclerView mbloglist;
FirebaseDatabase database;
DatabaseReference myRef;


//    private ImageView imgPicture;
  //  private DatabaseReference mPostReference;
    //private DatabaseReference mCommentsReference;
  //  private ValueEventListener mPostListener;
   // private String mPostKey;
   // FirebaseAuth mAuth;
   // String userId;
   // ByteArrayInputStream bais;
    //String email = "";
    //String pass = "";

    //Bitmap image;

    // Create a storage reference from our app
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ArrayList<ExampItem> exampleList = new ArrayList<>();

       // mRecyclerView = findViewById(R.id.recyclerView);
     //   mRecyclerView.setHasFixedSize(true);
     //   mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
      //  database = FirebaseDatabase.getInstance();
      //  myRef = database.getReference("login");


        /*

        mAuth = FirebaseAuth.getInstance();
        //FirebaseUser user = mAuth.getCurrentUser();
//        userId = mAuth.getCurrentUser().getUid();

        // Initialize Database
   //     mPostReference = FirebaseDatabase.getInstance().getReference()
     //           .child("login").child(userId);

        imgPicture = findViewById(R.id.imgVpic);


        StorageReference islandRef = storageRef.child("userProfile/" + userId + ".jpg");
        final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                bais = new ByteArrayInputStream(bytes);

                image = BitmapFactory.decodeStream(bais);
                imgPicture.setImageBitmap(image);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });


*/
        exampleList.add(new ExampItem(R.drawable.ic_android,"Line 1" , "Line 2"));
        exampleList.add(new ExampItem(R.drawable.ic_audio,"Line 3" , "Line 4"));
        exampleList.add(new ExampItem(R.drawable.ic_sun,"Line 5" , "Line 6"));
        exampleList.add(new ExampItem(R.drawable.ic_android,"Line 7" , "Line 8"));
        exampleList.add(new ExampItem(R.drawable.ic_audio,"Line 9" , "Line 10"));
        exampleList.add(new ExampItem(R.drawable.ic_sun,"Line 11" , "Line 12"));
        exampleList.add(new ExampItem(R.drawable.ic_android,"Line 13" , "Line 14"));
        exampleList.add(new ExampItem(R.drawable.ic_audio,"Line 15" , "Line 16"));
        exampleList.add(new ExampItem(R.drawable.ic_sun,"Line 17" , "Line 18"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);
       mRecyclerView.setLayoutManager(mLayoutManager);
       mRecyclerView.setAdapter(mAdapter);
    }

    /*
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecy
    }




        @Override
        public void onStart() {
            super.onStart();

            // Add value event listener to the post
            // [START post_value_event_listener]
            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Get Post object and use the values to update the UI
                    Login login = dataSnapshot.getValue(Login.class);
                    // [START_EXCLUDE]

                    email = (login.email).trim();
                    pass = (login.password).trim();

                    //mAuthorView.setText((user.firstName).trim());
                   /// mTitleView.setText((user.lastName).trim());
                    //mBodyView.setText((user.email).trim());
                   // mAddressView.setText((user.address).trim());
                   // mphoneView.setText((user.phone).trim());


                    // [END_EXCLUDE]
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                    // [START_EXCLUDE]
                    Toast.makeText(Main2Activity.this, "Failed to load data.",
                            Toast.LENGTH_SHORT).show();
                    // [END_EXCLUDE]
                }
            };

           // mPostReference.addValueEventListener(postListener);
            // [END post_value_event_listener]



    }
    */
}
