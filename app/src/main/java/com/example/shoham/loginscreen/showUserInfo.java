package com.example.shoham.loginscreen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class showUserInfo extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "PostDetailActivity";

    public static final String EXTRA_POST_KEY = "post_key";

    private DatabaseReference mPostReference;
    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;
    private String mPostKey;
    FirebaseAuth mAuth;
    String userId;
    private TextView mAuthorView;
    private TextView mTitleView;
    private TextView mBodyView;
    private TextView mphoneView;
    private TextView mAddressView;
    private ImageView imgPicture;

    ByteArrayInputStream bais;



    // Create a storage reference from our app
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_info);


        mAuth = FirebaseAuth.getInstance();
        //FirebaseUser user = mAuth.getCurrentUser();
        userId= mAuth.getCurrentUser().getUid();

        // Initialize Database
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("users").child(userId);


        // Initialize Views by id
        mAuthorView = findViewById(R.id.tvFIRST);
        mTitleView = findViewById(R.id.tvLLast);
        mBodyView = findViewById(R.id.tvEEmail);
        mphoneView = findViewById(R.id.tvPhone);
        mAddressView = findViewById(R.id.tvAAdress);
        imgPicture = findViewById(R.id.imgVpic);


        //////




        StorageReference islandRef = storageRef.child("userProfile/"+userId+".jpg");
        final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                bais = new ByteArrayInputStream(bytes);

                Bitmap image = BitmapFactory.decodeStream(bais);
                imgPicture.setImageBitmap(image);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

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
                    User user = dataSnapshot.getValue(User.class);
                    // [START_EXCLUDE]
                    mAuthorView.setText((user.firstName).trim());
                    mTitleView.setText((user.lastName).trim());
                    mBodyView.setText((user.email).trim());
                    mAddressView.setText((user.address).trim());
                    mphoneView.setText((user.phone).trim());


                    // [END_EXCLUDE]
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                    // [START_EXCLUDE]
                    Toast.makeText(showUserInfo.this, "Failed to load data.",
                            Toast.LENGTH_SHORT).show();
                    // [END_EXCLUDE]
                }
            };

            mPostReference.addValueEventListener(postListener);
            // [END post_value_event_listener]

            // Keep copy of post listener so we can remove it when app stops
            mPostListener = postListener;

            }






    @Override
    public void onClick(View view) {

    }



}